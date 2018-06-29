package com.example.tome.component_base.base.mvp;

import android.text.TextUtils;

import io.reactivex.observers.ResourceObserver;

/**
 * @author by TOME .
 * @data on      2018/6/29 11:34
 * @describe ${TODO}
 */

public abstract class BaseVpObserver<T> extends ResourceObserver<T> {
    protected String msg = "读取";
    protected boolean showMsg = true;
    public BaseVpObserver(String operatingStr) {
        if (operatingStr != null) {
            this.msg = operatingStr;
        } else {
            showMsg = false;
        }
    }

    public BaseVpObserver() {
    }

    @Override
    public void onNext(T t) {
        onNextSuccess(t);
//        if (t.isOk()) {
//            onNextSuccess(t);
//        } else {
//            if (!TextUtils.isEmpty(t.getMessage())) {
//                onError(new ResultException(t.getState(),t.getMessage()));
//            } else {
//                onError(new ResultException(t.getState(),msg+"失败,请检查网络或稍后重试"));
//            }
 //       }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
//        if (e instanceof ResultException) {
//            ToastUtil.showShort(ActivityStack.getInstance().currentActivity(),e.getMessage());
//        }
    }

    protected abstract void onNextSuccess(T t) ;

}
