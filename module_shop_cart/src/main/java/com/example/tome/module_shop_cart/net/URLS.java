package com.example.tome.module_shop_cart.net;

/**
 * @author tome
 * @date 2018/8/10  11:34
 * @describe ${接口地址}
 */
public interface URLS {
    String SERVER_ADDRESS = "http://testopen.d2shop.com/api/";//测试

    String URL_PRODUCT_GETPRODUCTDETAIL = SERVER_ADDRESS + "product/getProductDetail";//商品详情
    String URL_LOGIN = SERVER_ADDRESS + "register/login";//登录

    String URL_GETCARTINFO = SERVER_ADDRESS + "cart/getCartInfo";//购物车列表
    String URL_DEL_PRODUCT_BY_CARTID = SERVER_ADDRESS + "cart/delProductByCartId";//从购物车删除
    String URL_ADD_PRODUCT_TOB2B_CART = SERVER_ADDRESS + "cart/addProductToB2BCart";//添加到购物车
}
