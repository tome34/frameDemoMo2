package com.example.tome.component_base.base.mvp.inter;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;

/**
 * Created by XQ Yang on 2017/8/8  11:24.
 * Description : 顶级view接口
 */

public interface IView extends LifecycleOwner {

    Context getContext();

    void showError(String msg, String code);

    /**
     * 显示Dialog
     */
    void showHUD(String msg);

    /**
     * 关闭Dialog
     */
    void dismissHUD();
}
