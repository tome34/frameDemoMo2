package com.example.tome.core.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.tome.core.base.BaseEventbusBean;
import com.example.tome.core.base.mvp.inter.IPresenter;
import com.example.tome.core.base.mvp.inter.IView;
import com.example.tome.core.base.mvp.inter.MvpCallback;
import com.example.tome.core.constants.BaseApplication;
import com.example.tome.core.util.L;
import com.example.tome.core.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.RefWatcher;
import io.reactivex.disposables.CompositeDisposable;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    public ImmersionBar mImmersionBar;

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
        //沉浸式状态栏
        L.d("状态栏"+"initImmersionBar1");
        initImmersionBar();
        onViewCreated();
        L.d("状态栏"+"initTitle");
        initTitle();

        if (regEvent){
            EventBus.getDefault().register(this);
        }
        return view ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        initView();
    }

    /**
     * 回传view
     * @param view
     */
    protected void init(View view){};

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

//------------------------显示进度圈strat-----------------------------------------//

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

    //------------------------显示进度圈end-----------------------------------------//


    private void initImmersionBar() {

        if (mImmersionBar == null){
            L.d("状态栏"+"initImmersionBar2");
            mImmersionBar = ImmersionBar.with(this);
            mImmersionBar.init();
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

    //------------------空界面显示START-------------------------//

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

    //------------------空界面显示END-------------------------//

    /**
     * eventBut
     * @param event
     */
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
        if (mImmersionBar != null){
            mImmersionBar.destroy();
        }
        setPresenter(null);
        setMvpView(null);

        if (regEvent) {
            EventBus.getDefault().unregister(this);
        }

        //leakCanary 监控
        RefWatcher refWatcher = BaseApplication.getRefWatcher(mContext);
        refWatcher.watch(this);
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
