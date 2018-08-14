package com.example.tome.module_shop_cart.net;

import com.example.tome.module_shop_cart.base.BaseObjs;
import com.example.tome.module_shop_cart.base.INetResult;
import com.example.tome.module_shop_cart.bean.GetCartInfoBean;
import com.example.tome.module_shop_cart.bean.GetProductDetailsBean;
import com.example.tome.module_shop_cart.bean.LoginBean;
import com.example.tome.module_shop_cart.params.AddToCartParams;
import com.example.tome.module_shop_cart.params.DeleteProductParams;
import com.example.tome.module_shop_cart.params.GetProductDetailsParams;
import com.example.tome.module_shop_cart.params.LoginParams;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 * @author tome
 * @date 2018/8/10  11:23
 * @describe ${TODO}
 */
public class ShopDataManager {

    /**
     * 商品详情
     *
     * @param iNetResult
     */
    public static void getProductDetails(INetResult iNetResult, GetProductDetailsParams params) {
        Type type = new TypeToken<BaseObjs<GetProductDetailsBean>>() {
        }.getType();
        OKHttpHelper.execute(
            URLS.URL_PRODUCT_GETPRODUCTDETAIL,
            params,
            type,
            OKHttpHelper.GET,
            iNetResult,
            IFlag.FLAG_PRODUCT_GETPRODUCTDETAIL);
    }

    /**
     * 登录
     *
     * @param iNetResult
     * @param params
     */
    public static void login(INetResult iNetResult, LoginParams params) {
        Type type = new TypeToken<BaseObjs<LoginBean>>() {
        }.getType();
        OKHttpHelper.execute(
            URLS.URL_LOGIN,
            params,
            type,
            OKHttpHelper.GET,
            iNetResult,
            IFlag.FLAG_LOGIN);
    }

    /**
     * 购物车列表
     *
     * @param iNetResult
     */
    public static void getCartInfo(INetResult iNetResult) {
        Type type = new TypeToken<BaseObjs<GetCartInfoBean>>() {
        }.getType();
        OKHttpHelper.execute(
            URLS.URL_GETCARTINFO,
            new Object(),
            type,
            OKHttpHelper.GET,
            iNetResult,
            IFlag.FLAG_GETCARTINFO);
    }

    /**
     * 删除购物车商品
     *
     * @param iNetResult
     */
    public static void deleteProduct(INetResult iNetResult, DeleteProductParams params) {
        Type type = new TypeToken<BaseObjs<GetCartInfoBean>>() {
        }.getType();
        OKHttpHelper.execute(
            URLS.URL_DEL_PRODUCT_BY_CARTID,
            params,
            type,
            OKHttpHelper.GET,
            iNetResult,
            IFlag.FLAG_URL_DEL_PRODUCT_BY_CARTID);
    }


    /**
     * 添加到购物车
     * productIds	添加购物车信息	String	√	在“_”前面是商品id，后面是商品数量，多个商品的时候用“，”进行拼接
     *
     * @param iNetResult
     */
    public static void addToCart(INetResult iNetResult, AddToCartParams params) {
        Type type = new TypeToken<BaseObjs<GetProductDetailsBean>>() {
        }.getType();
       OKHttpHelper.execute(
            URLS.URL_ADD_PRODUCT_TOB2B_CART,
            params,
            type,
            OKHttpHelper.GET,
            iNetResult,
            IFlag.FLAG_ADD_PRODUCT_TOB2B_CART2);
    }
}
