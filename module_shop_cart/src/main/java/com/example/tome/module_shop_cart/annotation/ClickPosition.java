package com.example.tome.module_shop_cart.annotation;

import android.support.annotation.StringDef;

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
@StringDef(value = {
        ClickPosition.LEFT,
        ClickPosition.TOP,
        ClickPosition.RIGHT,
        ClickPosition.BOTTOM,
        ClickPosition.CENTER,
        ClickPosition.SUBMIT,
        ClickPosition.CANCEL,
        ClickPosition.OPEN,
        ClickPosition.CLOSE,
        ClickPosition.AGREE,
        ClickPosition.REFUSE,
        ClickPosition.ACCEPT,
        ClickPosition.UPDATE,
        ClickPosition.DELETE,
        ClickPosition.ALTER,
        ClickPosition.FORGET,
        ClickPosition.IGNORE,
        ClickPosition.STOP,
        ClickPosition.SEND
})

public @interface ClickPosition {
    /**
     * 左按钮
     */
    public static final String LEFT = "LEFT";

    /**
     * 顶部按钮
     */
    public static final String TOP = "TOP";
    /**
     * 右按钮
     */
    public static final String RIGHT = "RIGHT";
    /**
     * 底部按钮
     */
    public static final String BOTTOM = "BOTTOM";
    /**
     * 中间按钮
     */
    public static final String CENTER = "CENTER";
    /**
     * 确定按钮
     */
    public static final String SUBMIT = "SUBMIT";
    /**
     * 取消按钮
     */
    public static final String CANCEL = "CANCEL";

    /**
     * 关闭按钮
     */
    public static final String CLOSE = "CLOSE";
    /**
     * 打开按钮
     */
    public static final String OPEN = "OPEN";
    /**
     * 同意按钮
     */
    public static final String AGREE = "AGREE";
    /**
     * 拒绝按钮
     */
    public static final String REFUSE = "REFUSE";
    /**
     * 接受按钮
     */
    public static final String ACCEPT = "ACCEPT";
    /**
     * 升级按钮
     */
    public static final String UPDATE = "UPDATE";
    /**
     * 删除按钮
     */
    public static final String DELETE = "DELETE";
    /**
     * 修改按钮
     */
    public static final String ALTER = "ALTER";
    /**
     * 忘记按钮
     */
    public static final String FORGET = "FORGET";
    /**
     * 忽略按钮
     */
    public static final String IGNORE = "IGNORE";
    /**
     * 停止按钮
     */
    public static final String STOP = "STOP";
    /**
     * 发送按钮
     */
    public static final String SEND = "SEND";
}
