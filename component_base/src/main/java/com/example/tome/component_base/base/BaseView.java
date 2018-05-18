package com.example.tome.component_base.base;

/**
 * @Created by TOME .
 * @时间 2018/5/2 17:58
 * @描述 ${v接口}
 */

public interface BaseView {

    void onSuccess();

    void showError(String msg, String code);

    void showErrorMsg(String errorMsg);

    void showLoading();

    /**
     * 显示Dialog
     */
    void showHUD();

    /**
     * 关闭Dialog
     */
    void dismissHUD();
}
