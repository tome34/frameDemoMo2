package com.example.tome.module_shop_cart.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@SuppressWarnings("all")
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
@Inherited
@IntDef(value = {OnKeyType.DISMISS_KILL_ACTIVITY, OnKeyType.DISMISS_NOT_KILL_ACTIVITY, OnKeyType.NOT_DISMISS_NOT_KILL_ACTIVITY})

public @interface OnKeyType {
    /**
     * 隐藏对话框 - 结束Activity
     */
    public static final int DISMISS_KILL_ACTIVITY = 0;
    /**
     * 隐藏对话框 - 不结束Activity
     */
    public static final int DISMISS_NOT_KILL_ACTIVITY = 1;
    /**
     * 不隐藏对话框 - 不结束Activity
     */
    public static final int NOT_DISMISS_NOT_KILL_ACTIVITY = 2;

}
