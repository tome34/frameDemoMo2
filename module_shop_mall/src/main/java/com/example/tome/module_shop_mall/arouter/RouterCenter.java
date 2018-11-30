package com.example.tome.module_shop_mall.arouter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fec.core.router.arouter.RouterURLS;

/**
 * @Created by TOME .
 * @时间 2018/4/26 10:19
 * @描述 ${路由中心}
 */
//ARouter 提供了大量的参数类型 跳转携带 https://blog.csdn.net/zhaoyanjun6/article/details/76165252
public class RouterCenter {
    /**
     * 测试首页
     */
    public static void toMain() {
        ARouter.getInstance().build(RouterURLS.BASE_MAIN).navigation();
    }

    /**
     * 主页
     */
    public static void toHome() {
        ARouter.getInstance().build(RouterURLS.SHOP_MALL_HOME).navigation();
    }

    /**
     * shopCart 模块页面
     */
    public static void toShopCart() {
        ARouter.getInstance().build(RouterURLS.SHOP_CART_MAIN).navigation();
    }

    /**
     * mvc 测试页面
     */
    public static void toMVCTest() {
        ARouter.getInstance().build(RouterURLS.MVC_TEST).navigation();
    }

    /**
     * mvp 测试页面
     */
    public static void toMVPTest() {
        ARouter.getInstance().build(RouterURLS.MVP_TEST).navigation();
    }

    /**
     * mvp 测试页面 省略model层
     */
    public static void toMVPTest2() {
        ARouter.getInstance().build(RouterURLS.MVP_TEST2).navigation();
    }

    /**
     * goods 模块页面
     */
    public static void toShopGoods() {
        ARouter.getInstance().build(RouterURLS.SHOP_GOODS).navigation();
    }

    /**
     * welfare 福利模块
     */
    public static void toWelfareHome(){
        ARouter.getInstance().build(RouterURLS.WELFARE_HOME).navigation();
    }

    /**
     * 自定义控件集合
     */
    public static void toCustomControl(){
        ARouter.getInstance().build(RouterURLS.CUSTOM_CONTROL).navigation();
    }

}
