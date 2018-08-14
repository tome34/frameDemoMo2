package com.example.tome.module_shop_cart.base;

/**
 * @author tome
 * @date 2018/8/10  10:39
 * @describe ${基本UI}
 */
public interface IUIBase {
    int setContentLayout();

    void initTitle();

    void initView();

    /**
     * 显示Dialog
     */
    void showHUD();

    /**
     * 关闭Dialog
     */
    void dismissHUD();
}
