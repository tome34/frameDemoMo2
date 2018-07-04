package com.example.tome.component_base.base.mvc;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tome.component_base.base.mvc.inter.BaseView;
import com.example.tome.component_base.base.mvp.BaseVpActivity;
import com.example.tome.component_base.base.mvp.inter.IView;
import com.example.tome.component_base.util.ToastUtils;
import com.example.tome.component_data.constant.BaseEventbusBean;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @Created by TOME .
 * @时间 2018/5/30 17:01
 * @描述 ${MVC模式的Base fragment}
 */

public abstract class BaseVcFragment extends Fragment implements IView{

    private Unbinder unBinder;
    protected Context mContext;
    protected boolean regEvent;

    //管理事件流订阅的生命周期CompositeDisposable
    private CompositeDisposable compositeDisposable;

    public IView mView = this;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        unBinder = ButterKnife.bind(this , view);
        initTitle();
        initView();

        if (regEvent){
            EventBus.getDefault().register(this);
        }
        return view ;
    }

    /**
     * rxjava管理订阅者
     */
    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }
    @Override
    public void showHUD(String msg) {
        if (getActivity() != null) {
            BaseVpActivity baseActivity = (BaseVpActivity) getActivity();
            baseActivity.showHUD(msg);
        }
    }

    @Override
    public void dismissHUD() {
        if (getActivity() != null) {
            BaseVpActivity baseActivity = (BaseVpActivity) getActivity();
            baseActivity.dismissHUD();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.i("当前运行的fragment:" + getClass().getName());
    }

    /**
     * 提示网络请求错误信息
     * @param msg
     * @param code
     */
    @Override
    public void showError(String msg, String code) {
        String mCode ="-1";
        if (mCode.equals(code)){
            ToastUtils.showShort(mContext, msg);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEventbusBean event) {
        onEvent(event);
    }

    protected void onEvent(BaseEventbusBean event) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
        }
        if (regEvent) {
            EventBus.getDefault().unregister(this);
        }
    }


    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract int getLayout();

    /**
     * 初始化标题
     */
    protected abstract void initTitle();

    /**
     * 初始化数据
     */
    protected abstract void initView();

}
