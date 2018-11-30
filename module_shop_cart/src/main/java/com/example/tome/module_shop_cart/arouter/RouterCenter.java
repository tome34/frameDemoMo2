package com.example.tome.module_shop_cart.arouter;

import android.support.v4.app.Fragment;

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
     * goods 商品列表页
     */
    public static Fragment toShopGoods() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouterURLS.PRODUCT_GOODS).navigation();
        if (fragment == null){
            fragment = new Fragment();
        }
        return fragment ;
    }


    /**
     * shopCart 购物车
     */
    public static Fragment toShopCart() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouterURLS.PRODUCT_CART).navigation();
        if (fragment == null){
            fragment = new Fragment();
        }
        return fragment ;
    }




}
