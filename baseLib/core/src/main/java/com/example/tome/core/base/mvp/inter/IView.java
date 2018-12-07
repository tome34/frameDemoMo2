package com.example.tome.core.base.mvp.inter;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;

/**
 * Created by XQ Yang on 2017/8/8  11:24.
 * Description : 顶级view接口
 */

public interface IView extends LifecycleOwner {

    Context getContext();

    /**
     * 网络请求错误,弹框提示
     * @param msg
     * @param code
     */
    void showError(String msg, String code);

    /**
     * 显示Dialog
     */
    void showHUD(String msg);

    /**
     * 关闭Dialog
     */
    void dismissHUD();

    //----------------------------下面用来显示空界面---------------------------//
    /**
     * showNormal 页面
     */
    void showNormal();

    /**
     * Show loading 页面
     */
    void showLoading();
    /**
     * Show EmptyView 页面
     */
    void showEmptyView();
    /**
     * Show error 页面
     */
    void showError();
}
