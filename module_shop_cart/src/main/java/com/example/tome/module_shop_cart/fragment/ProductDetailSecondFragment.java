package com.example.tome.module_shop_cart.fragment;

import com.example.tome.module_shop_cart.R;
import com.example.tome.module_shop_cart.base.CommonFragment;
import com.example.tome.module_shop_cart.bean.GetProductDetailsBean;
import com.example.tome.module_shop_cart.listener.OnDimensionSelectListener;

/**
 * @author tome
 * @date 2018/8/10  16:11
 * @describe ${商品详情-商品Fragment中第二个Fragment}
 */
public class ProductDetailSecondFragment extends CommonFragment implements OnDimensionSelectListener {
    @Override
    public int setContentLayout() {
        return R.layout.cart_fragment_product_second;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void onSelectDemension(String dimensions,String selectCout) {

    }

    @Override
    public void onProductIntroShow(GetProductDetailsBean.DataBean bean) {

    }

    @Override
    public void onPriceChange(String price) {

    }
}
