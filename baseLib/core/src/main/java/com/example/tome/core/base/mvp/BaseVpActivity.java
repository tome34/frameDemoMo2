package com.example.tome.core.base.mvp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.tome.core.R;
import com.example.tome.core.base.BaseEventbusBean;
import com.example.tome.core.base.mvp.inter.IPresenter;
import com.example.tome.core.base.mvp.inter.IView;
import com.example.tome.core.base.mvp.inter.MvpCallback;
import com.example.tome.core.constants.BaseApplication;
import com.example.tome.core.helper.HUDFactory;
import com.example.tome.core.util.StatuBarCompat;
import com.example.tome.core.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.orhanobut.logger.Logger;
import io.reactivex.disposables.CompositeDisposable;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Created by TOME .
 * @时间 2018/5/2 17:40
 * @描述 ${MVP模式的Base Activity}
 */

public abstract class BaseVpActivity<V extends IView, P extends IPresenter<V>> extends AppCompatActivity implements MvpCallback<V, P>, IView {

    protected P mPresenter ;
    protected V mView;
    private Unbinder unBinder;
    protected boolean regEvent;
    public BaseVpActivity mActivity ;
    public KProgressHUD kProgressHUD;
    public ImmersionBar mImmersionBar;
    protected boolean isDestory = false;

    //管理事件流订阅的生命周期CompositeDisposable
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unBinder = ButterKnife.bind(this);
        //加入activity管理
        BaseApplication.getAppContext().getActivityControl().addActivity(this);
        //沉浸式状态栏
        initImmersionBar();
        //setImmeriveStatuBar();
        mActivity = this ;
        onViewCreated();
        initTitle();
        initView();
        if (regEvent){
            EventBus.getDefault().register(this);
        }
        initListener();
    }

    private void initImmersionBar() {
            mImmersionBar = ImmersionBar.with(this);
            //所有子类都将继承这些相同的属性
           // mImmersionBar.init();
    }

    /**
     * 初始化presenter
     */
    public void onViewCreated() {
        mView = createView();
        if (getPresenter()==null) {
            mPresenter = createPresenter();
            //注册lifecycle生命周期
            getLifecycle().addObserver(mPresenter);
        }
        mPresenter = getPresenter();
        mPresenter.attachView(getMvpView());
    }

    @CallSuper
    protected void initListener() {
        mPresenter.attachView(getMvpView());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEventbusBean event) {
        onEvent(event);
    }

    protected void onEvent(BaseEventbusBean event) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showHUD(String msg) {
        if (isDestory){
            return;
        }
        if (kProgressHUD == null){
            kProgressHUD = HUDFactory.getInstance().creatHUD(this);
        }
        kProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
               // .setLabel(getString(R.string.loading))
                .setLabel(TextUtils.isEmpty(msg) ? getString(R.string.loading) : msg)
               // .setLabel(null)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.3f).show();
    }

    @Override
    public void dismissHUD() {
        if (null != kProgressHUD && kProgressHUD.isShowing()) {
            kProgressHUD.dismiss();
        }
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
            ToastUtils.showShort(mActivity, msg);
        }

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

    @Override
    protected void onResume() {
        super.onResume();
        Logger.i("当前运行的activity:" + getClass().getName());
    }

    public void back(View v) {
        finish();
    }



    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
        }
        setPresenter(null);
        setMvpView(null);
        if (regEvent) {
            EventBus.getDefault().unregister(this);
        }

        //必须调用该方法，防止内存泄漏
        if (mImmersionBar != null){
            mImmersionBar.destroy();
        }
        isDestory = true;
        dismissHUD();
        //移除类
        BaseApplication.getAppContext().getActivityControl().removeActivity(this);

    }

    /**
     * 沉浸式状态栏
     */
    protected void setImmeriveStatuBar() {
        StatuBarCompat.setImmersiveStatusBar(true, Color.WHITE, this);

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
    protected abstract int getLayoutId();

    /**
     * 初始化标题
     */
    protected abstract void initTitle();

    /**
     * 初始化数据
     */
    protected abstract void initView();

    /**
     * 加载数据
     */
    protected  void loadData(){};

}
