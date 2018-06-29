package com.example.tome.component_base.base.mvc;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;


import com.example.tome.component_base.base.mvc.inter.BaseView;
import com.example.tome.component_base.base.mvc.inter.ILoadingDialogView;
import com.example.tome.component_base.util.ActivityUtils;
import com.example.tome.component_base.util.L;
import com.example.tome.component_base.util.NetUtils;
import com.example.tome.component_data.ServerException.ServerException;
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

    private BaseView mView;
    private String mErrorMsg;
    protected ILoadingDialogView mDialogView = null;
    protected String msg = "正在加载中...";
    private boolean isShowError = true;
    private SmartRefreshLayout rlRefreshLayout = null;



    protected BaseObserver(BaseView view){
        this.mView = view;
        this.mDialogView = view;
    }

    protected BaseObserver(BaseView view,boolean isShowHUD){
        this.mView = view;
        if (isShowHUD){
            this.mDialogView = view;
        }
    }

    protected BaseObserver(BaseView view, SmartRefreshLayout rlRefresh ,boolean isShowHUD){
        this.mView = view;
        this.rlRefreshLayout = rlRefresh ;
        if (isShowHUD){
            this.mDialogView = view;
        }

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

    protected BaseObserver(BaseView view, String msg1){
        mView = view;
        mDialogView = view;
        msg = msg1;
    }

    protected BaseObserver(BaseView view,ILoadingDialogView dialogView , String msg1){
        mView = view;
        mDialogView = dialogView;
        msg = msg1;
    }

    protected BaseObserver(BaseView view, String errorMsg, boolean isShowError){
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowError = isShowError;
    }

    /**
     *  执行开始（可选）
     *  它会在subscribe(订阅)刚开始，而事件还未发送之前被调用，可以用于做一些准备工作
     *  它总是在subscribe(订阅)所发生的线程被调用(不合适在主线程加载进度条)
     */
    @Override
    protected void onStart() {
        super.onStart();

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
        }else if (mDialogView == null && rlRefreshLayout != null){
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
