package com.example.tome.component_base.base;

import android.app.Activity;

import com.example.tome.component_base.helper.HUDFactory;
import com.example.tome.component_base.util.ActivityUtil;
import com.example.tome.component_base.util.L;
import com.example.tome.component_base.util.T;
import com.kaopiz.kprogresshud.KProgressHUD;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @Created by TOME .
 * @时间 2018/5/3 12:40
 * @描述 ${管理事件流订阅的生命周期CompositeDisposable}
 */

public class BasePresenter<V extends BaseView> implements AbstractPresenter<V> {

    protected V mView;
    private CompositeDisposable compositeDisposable;

    protected void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }


}
