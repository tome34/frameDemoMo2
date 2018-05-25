package com.example.tome.module_shop_cart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tome.component_data.d_arouter.RouterURLS;


import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = RouterURLS.SHOP_CART_MAIN)
public class ShopCartActivity extends AppCompatActivity {

    @BindView(R2.id.tv_cart_test)
    TextView mTvCartTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity_shop_cart);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mTvCartTest.setText("我来了");
    }
}
