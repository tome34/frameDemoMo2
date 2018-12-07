package com.example.tome.projectCore.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.tome.core.base.BaseEventbusBean;
import com.example.tome.core.base.mvp.BaseVpActivity;
import com.example.tome.core.base.mvp.inter.IView;
import com.example.tome.core.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.RefWatcher;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.example.tome.core.constants.BaseApplication.getRefWatcher;

/**
 * @author tome
 * @date 2018/7/10  15:38
 * @describe ${DialogFragment}
 */
public abstract class BaseDialogFragment extends DialogFragment implements IView {

    private Unbinder unBinder;
    public View mRootView;
    protected Context mContext;
    protected boolean regEvent;
    private CompositeDisposable compositeDisposable;

    public IView mView = this;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        mRootView =  inflater.inflate(getLayout(), container, false);
        unBinder = ButterKnife.bind(this, mRootView);
        compositeDisposable = new CompositeDisposable();
        initView();

        if (regEvent){
            EventBus.getDefault().register(this);
        }
        return mRootView;
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
    public void onDestroyView() {
        super.onDestroyView();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        if (unBinder != null) {
            unBinder.unbind();
        }
        if (regEvent) {
            EventBus.getDefault().unregister(this);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //leakCanary 监控
        RefWatcher refWatcher = getRefWatcher(mContext);
        refWatcher.watch(this);
    }

    /**
     * 空界面显示
     */
    @Override
    public void showNormal() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void showError() {

    }

    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract int getLayout();

    /**
     * 初始化数据
     */
    protected abstract void initView();
}
