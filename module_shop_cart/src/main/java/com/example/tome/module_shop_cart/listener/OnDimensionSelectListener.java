package com.example.tome.module_shop_cart.listener;

import com.example.tome.module_shop_cart.bean.GetProductDetailsBean;

/**
 * @desc 商品规格选择监听
 */
public interface OnDimensionSelectListener {
    //規格選擇
    void onSelectDemension(String dimensions,String selectCout);

    //商品簡介
    void onProductIntroShow(GetProductDetailsBean.DataBean bean);

    //展示評價
    //void onEvalutionShow(EvaluteDetailBean bean);

    //價格更改
    void onPriceChange(String price);
}
