package com.example.tome.component_base.base;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tome.component_base.base.inter.AbstractPresenter;
import com.example.tome.component_base.base.inter.BaseView;
import com.example.tome.component_base.constants.BaseApplication;
import com.example.tome.component_base.helper.HUDFactory;
import com.example.tome.component_base.util.L;
import com.example.tome.component_base.util.StatuBarCompat;
import com.example.tome.component_base.util.T;
import com.example.tome.component_data.constant.BaseEventbusBean;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Created by TOME .
 * @时间 2018/5/2 17:40
 * @描述 ${MVP模式的Base Activity}
 */

public abstract class BaseMVPActivity<P extends AbstractPresenter> extends AppCompatActivity implements BaseView {

    protected P mPresenter ;
    private Unbinder unBinder;
    protected boolean regEvent;
    public BaseMVPActivity mActivity ;
    public KProgressHUD kProgressHUD;
    protected boolean isDestory = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unBinder = ButterKnife.bind(this);
        //加入activity管理
        BaseApplication.getAppContext().getActivityControl().addActivity(this);
        //沉浸式状态栏
        //setImmeriveStatuBar();
        mActivity = this ;
        onViewCreated();
        initTitle();
        initView();
    }

    /**
     * 初始化presenter
     */
    public void onViewCreated() {
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        if (regEvent){
            EventBus.getDefault().register(this);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEventbusBean event) {
        onEvent(event);
    }

    protected void onEvent(BaseEventbusBean event) {

    }



    @Override
    public void showHUD(String msg) {
        if (isDestory){
            return;
        }
        kProgressHUD = HUDFactory.getInstance().creatHUD(this);
        kProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
               // .setLabel(getString(R.string.loading))
                .setLabel(msg)
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
            T.showShort(mActivity, msg);
        }

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
        if (regEvent) {
            EventBus.getDefault().unregister(this);
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

    /**
     * 获取当前的persenter
     * @return
     */
    protected abstract P getPresenter();

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



}
