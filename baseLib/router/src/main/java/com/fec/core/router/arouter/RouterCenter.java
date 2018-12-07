//package com.example.tome.component_base.arouter;
//
//import android.app.Activity;
//import android.content.Context;
//
//import com.alibaba.android.arouter.launcher.ARouter;
//import com.example.tome.component_data.d_arouter.RouterURLS;
//
///**
// * @Created by TOME .
// * @时间 2018/4/26 10:19
// * @描述 ${路由中心}
// */
////ARouter 提供了大量的参数类型 跳转携带 https://blog.csdn.net/zhaoyanjun6/article/details/76165252
//public class RouterCenter {
//    /**
//     * 测试首页
//     */
//    public static void toMain() {
//        ARouter.getInstance().build(RouterURLS.BASE_MAIN).navigation();
//    }
//
//    /**
//     * shopCart 模块页面
//     */
//    public static void toShopCart() {
//        ARouter.getInstance().build(RouterURLS.MODULE_SHOP_CART_MAIN).navigation();
//    }
//
//    public static void toShopCart(Activity context) {
//        ARouter.getInstance().build(RouterURLS.MODULE_SHOP_CART_MAIN).navigation(context);
//    }
//
//    /**
//     * 主页
//     */
//    public static void toHome() {
//        ARouter.getInstance().build(RouterURLS.MODULE_SHOP_MALL_HOME).navigation();
//    }
//
//  /*  public static void toMainOder() {
//        ARouter.getInstance().build(RouterURLS.BASE_MAIN)
//                .withInt(IntentKV.K_TO_MAIN_ORDER, IntentKV.V_TO_MAIN_ORDER)
//                .navigation();
//    }*/
//}
