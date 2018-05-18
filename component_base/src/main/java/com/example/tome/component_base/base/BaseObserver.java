package com.example.tome.component_base.base;

import android.text.TextUtils;


import com.example.tome.component_base.util.L;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

/**
 * @author quchao
 * @date 2017/11/27
 *观察者base
 * @param <T>
 */

public abstract class BaseObserver<T> extends ResourceObserver<T> {
//public abstract class BaseObserver<T> extends DisposableObserver<T> {
//public abstract class BaseObserver<T> implements Observer<T> {

    private BaseView mView;
    private String mErrorMsg;
    private boolean isShowError = true;



    protected BaseObserver(BaseView view){
        this.mView = view;
    }

    protected BaseObserver(BaseView view, String errorMsg){
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected BaseObserver(BaseView view, boolean isShowError){
        this.mView = view;
        this.isShowError = isShowError;
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

    }

    /**
     * 执行结果
     */
    @Override
    public void onComplete() {

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
            mView.showErrorMsg(mErrorMsg);
        //} else if (e instanceof ServerException) {
         //   mView.showErrorMsg(e.toString());
        } else if (e instanceof HttpException) {
            mView.showErrorMsg("网络异常");
            //BaseApplication.getInstance().getString(R.string.http_error);
        } else {
            mView.showErrorMsg("未知错误");

        }
        if (isShowError) {
            mView.showError("","-1");
        }
    }
}
