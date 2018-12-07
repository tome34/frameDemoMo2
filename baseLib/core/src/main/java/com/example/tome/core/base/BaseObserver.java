package com.example.tome.core.base;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;
import com.example.tome.core.base.mvp.inter.IView;
import com.example.tome.core.net.ServerException;
import com.example.tome.core.util.ActivityUtils;
import com.example.tome.core.util.L;
import com.example.tome.core.util.NetUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

/**
 * @author quchao
 * @date 2017/11/27
 *观察者base
 * @param <T>
 */

//public abstract class BaseObserver<R,ToastUtils extends BaseObj<R>> extends ResourceObserver<ToastUtils> {
public abstract class BaseObserver<T> extends ResourceObserver<T> {
//public abstract class BaseObserver<ToastUtils> extends DisposableObserver<ToastUtils> {
//public abstract class BaseObserver<ToastUtils> implements Observer<ToastUtils> {

    private IView mView;
    private String mErrorMsg;
    protected IView mDialogView = null;
    protected String msg = "正在加载中...";
    private boolean isShowError = true;
    private SmartRefreshLayout rlRefreshLayout = null;

    protected BaseObserver(){

    }

    protected BaseObserver(IView view){
        this.mView = view;
    }

    protected BaseObserver(IView view, boolean isShowHUD){
        this.mView = view;
        if (isShowHUD){
            this.mDialogView = view;
        }
    }

    protected BaseObserver(IView view, SmartRefreshLayout rlRefresh){
        this.mView = view;
        this.rlRefreshLayout = rlRefresh ;
    }

    protected BaseObserver(IView view, String msg1){
        mView = view;
        mDialogView = view;
        msg = msg1;
    }


   /* @Override
    public void onNext(ToastUtils t) {
        if (t.getCode().equals("0")){
            onNextSuccess(t.getData());
        }else {
            onError(new RuntimeException(t.getMessage()));
        }
    }

    public abstract void onNextSuccess(R r);*/

    /**
     *  执行开始（可选）
     *  它会在subscribe(订阅)刚开始，而事件还未发送之前被调用，可以用于做一些准备工作
     *  它总是在subscribe(订阅)所发生的线程被调用(不合适在主线程加载进度条)
     */
    @Override
    protected void onStart() {
        super.onStart();
        //显示正在加载中的页面,由子页面实现
        if (mView != null && rlRefreshLayout == null){
            mView.showLoading();
            L.d("空布局调用正在加载中");
        }


        if (mDialogView != null){
            mDialogView.showHUD(msg);
        }

        Activity currentActivity = ActivityUtils.getInstance().currentActivity();
        if (currentActivity != null && !NetUtils.isNetConnected(currentActivity)) {
            Toast.makeText(currentActivity, "当前无网络", Toast.LENGTH_SHORT).show();
            onComplete();
        }

    }

    /**
     * 执行结果
     */
    @Override
    public void onComplete() {
        L.d("执行结果");
        if (mDialogView != null) {
            mDialogView.dismissHUD();
        }else if (rlRefreshLayout != null){
            rlRefreshLayout.finishRefresh();
            rlRefreshLayout.finishLoadMore();
        }
    }

    /**
     * 执行错误
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        L.d("网络异常");
        if (mView == null) {
            return;
        }
        //是否显示错误页面,由子类去实现
        mView.showError();

        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showError(mErrorMsg ,"-1");
        } else if (e instanceof ServerException) {
            mView.showError(e.toString(),"-1");
        } else if (e instanceof HttpException) {
            mView.showError("网络异常","-1");
        } else {
            mView.showError("未知错误","-1");

        }
       // if (isShowError) {
       //     mView.showError("","-1");
       // }
        onComplete();
    }
}
