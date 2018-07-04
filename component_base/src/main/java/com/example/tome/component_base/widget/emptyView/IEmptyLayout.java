/*
 * Copyright (c) 18-2-1 下午1:58. XQ Yang
 */

package com.example.tome.component_base.widget.emptyView;

import android.support.annotation.DrawableRes;
import android.view.View;

/**
 * @author XQ Yang
 * @date 2017/11/15  11:08
 */
public interface IEmptyLayout {
    void showEmpty();

    void showSuccess();

    void showError();

    void showLoading();

    void bindView(View view);

    void setOnStateChangeListener(OnStateChangeListener listener);

    void setVisibility(int gone);

    void setEmptyMsgAndIcon(String msg, @DrawableRes int icon);

    int getLoadingState();

    void setCustomView(int type, View view);

    @FunctionalInterface
    interface OnStateChangeListener {
         int TYPE_EMPTY = 0;
         int TYPE_LOADING = 1;
         int TYPE_ERROR = 2;
         int TYPE_SUCCESS = 3;

        void onChange(int type);
     }
}
