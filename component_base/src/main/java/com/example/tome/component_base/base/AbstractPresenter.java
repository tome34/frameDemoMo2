package com.example.tome.component_base.base;

/**
 * @Created by TOME .
 * @时间 2018/5/2 17:56
 * @描述 ${Presenter 基类}
 */

public interface AbstractPresenter<T extends BaseView> {

    /**
     * 注入View
     *
     * @param view view
     */
    void attachView(T view);

    /**
     * 回收View
     */
    void detachView();
}
