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
@IntDef(value = {
        AnimType.CENTER_NORMAL,
        AnimType.CENTER_SCALE,
        AnimType.BOTTOM_2_TOP,
        AnimType.TOP_2_BOTTOM

})

public @interface AnimType {
    /**
     * 居中 无效果
     */
    public static final int CENTER_NORMAL = 0;
    /**
     * 居中缩放
     * 进入：居中，由小变大
     * 退出：居中，由大变小
     */
    public static final int CENTER_SCALE = 1;
    /**
     * 底部进入
     * 进入：从底部，到顶部
     * 退出：从顶部，到底部
     */
    public static final int BOTTOM_2_TOP = 2;
    /**
     * 顶部进入
     * 进入：从顶部，到底部
     * 退出：从底部，到顶部
     */
    public static final int TOP_2_BOTTOM = 3;

}
