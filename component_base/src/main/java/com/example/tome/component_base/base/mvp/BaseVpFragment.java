package com.example.tome.component_base.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tome.component_base.base.mvp.inter.IPresenter;
import com.example.tome.component_base.base.mvp.inter.IView;
import com.example.tome.component_base.base.mvp.inter.MvpCallback;
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
 * @描述 ${MVP模式的Base fragment}
 */

public abstract class BaseVpFragment<V extends IView,P extends IPresenter<V>> extends Fragment implements MvpCallback<V, P>, IView {

    private Unbinder unBinder;
    protected Context mContext;
    protected boolean regEvent;
    protected P mPresenter;
    protected V mView;
    private BaseVpActivity mBaseActivity;

    //管理事件流订阅的生命周期CompositeDisposable
    private CompositeDisposable compositeDisposable;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        unBinder = ButterKnife.bind(this , view);
        onViewCreated();
        initTitle();
        initView();

        if (regEvent){
            EventBus.getDefault().register(this);
        }
        return view ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    /**
     * 初始化presenter
     */
    public void onViewCreated() {
        mView = createView();
        if (getPresenter()==null) {
            mPresenter = createPresenter();
            getLifecycle().addObserver(mPresenter);
        }
        mPresenter = getPresenter();
        //在这个时候才attach view是因为这个时候view的初始化已经基本完成,在Presenter中调用view的域也不会为空
        mPresenter.attachView(getMvpView());
    }


    @Override
    public void showHUD(String msg) {
        if (getActivity() != null ) {
            if (mBaseActivity == null){
                mBaseActivity = (BaseVpActivity) getActivity();
            }
            mBaseActivity.showHUD(msg);
        }

    }

    @Override
    public void dismissHUD() {
        if (getActivity() != null && mBaseActivity != null) {
            mBaseActivity.dismissHUD();
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
        setPresenter(null);
        setMvpView(null);
        if (regEvent) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setMvpView(V view) {
        this.mView = view;
    }

    @Override
    public V getMvpView() {
        return this.mView;
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

    /**
     * view填充之前 过去Intent数据  绑定Presenter等
     * 注意:获取intent的数据需要在super之前,否则如果创建Presenter使用到这些数据的话,这些数据在使用时还未被赋值
     * @param savedInstanceState
     */
    protected void init(Bundle savedInstanceState){};

}
