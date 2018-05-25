package com.example.tome.module_shop_goods;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tome.component_data.d_arouter.RouterURLS;

import butterknife.BindView;
import butterknife.ButterKnife;


@Route(path = RouterURLS.SHOP_GOODS)
public class ShopGoodsActivity extends AppCompatActivity {

    @BindView(R2.id.tv_goods_test)
    TextView mTvGoodsTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_activity_shop_goods);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mTvGoodsTest.setText("商品页面");
    }
}
