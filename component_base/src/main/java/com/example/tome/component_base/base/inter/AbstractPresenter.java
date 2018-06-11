package com.example.tome.component_base.base.inter;

/**
 * @Created by TOME .
 * @时间 2018/5/2 17:56
 * @描述 ${Presenter 基类}
 */

public interface AbstractPresenter<V extends BaseView> {

    /**
     * 注入View
     *
     * @param view view
     */
    void attachView(V view);

    /**
     * 回收View
     */
    void detachView();
}
