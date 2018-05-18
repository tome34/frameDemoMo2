package com.example.tome.module_shop_mall.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tome.component_base.base.AbstractPresenter;
import com.example.tome.component_base.base.BaseMVPActivity;
import com.example.tome.component_data.d_arouter.RouterURLS;
import com.example.tome.module_shop_mall.R;

/**
 * @Created by TOME .
 * @时间 2018/5/17 18:20
 * @描述 ${TODO}
 */

@Route(path = RouterURLS.MODULE_SHOP_MALL_HOME)
public class HomeActivity extends BaseMVPActivity{
    @Override
    protected AbstractPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mall_activity_home;
    }

    @Override
    protected void initEventAndData() {

    }
}
