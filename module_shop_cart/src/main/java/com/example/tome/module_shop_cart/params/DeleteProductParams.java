package com.example.tome.module_shop_cart.params;

/**
 * 从购物车删除
 * Created by Administrator on 2017/12/24.
 */

public class DeleteProductParams {
    private String cartIds;//	购物车id	String	√	如果删除多个就需要用“，”进行购物车id拼接

    public String getCartIds() {
        return cartIds;
    }

    public void setCartIds(String cartIds) {
        this.cartIds = cartIds;
    }
}
