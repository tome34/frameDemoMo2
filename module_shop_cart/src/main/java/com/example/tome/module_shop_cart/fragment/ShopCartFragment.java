package com.example.tome.module_shop_cart.fragment;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tome.component_base.base.BaseMVPFragment;
import com.example.tome.component_base.base.inter.AbstractPresenter;
import com.example.tome.component_data.d_arouter.RouterURLS;
import com.example.tome.module_shop_cart.R;

/**
 * @Created by TOME .
 * @时间 2018/6/13 20:50
 * @描述 ${TODO}
 */

@Route(path = RouterURLS.PRODUCT_CART)
public class ShopCartFragment extends BaseMVPFragment {


    public static ShopCartFragment newInstance(String info) {
        Bundle args = new Bundle();
        ShopCartFragment fragment = new ShopCartFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected AbstractPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.cart_shop_cart_fragment;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {

    }
}
