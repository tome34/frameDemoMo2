package com.example.tome.module_shop_cart;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tome.component_base.base.mvp.BaseMVPActivity;
import com.example.tome.component_base.base.inter.AbstractPresenter;
import com.example.tome.component_data.d_arouter.RouterURLS;
import com.example.tome.module_shop_cart.arouter.RouterCenter;

import butterknife.BindView;


@Route(path = RouterURLS.SHOP_CART_MAIN)
public class ShopCartActivity extends BaseMVPActivity implements RadioGroup.OnCheckedChangeListener {


    @BindView(R2.id.fl_content)
    FrameLayout mFlContent;
    @BindView(R2.id.rb_product)
    RadioButton mRbProduct;
    @BindView(R2.id.rb_cart)
    RadioButton mRbCart;
    @BindView(R2.id.rg_tab)
    RadioGroup mRgTab;
    @BindView(R2.id.layout_cart_frag)
    LinearLayout mLayoutCartFrag;
    public Fragment mShopCartFrament;
    public Fragment mProductFragment;

       @Override
    protected AbstractPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.cart_activity_shop_cart;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        //radiobutton监听
        mRgTab.setOnCheckedChangeListener(this);
        //选择商品tab
        mRgTab.check(R.id.rb_product);

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //商品列表
        if (mProductFragment == null) {
            mProductFragment = RouterCenter.toShopGoods();
            transaction.add(R.id.fl_content, mProductFragment);
        }

        if (checkedId == R.id.rb_product) {
            transaction.show(mProductFragment);
        }else {
            transaction.hide(mProductFragment);
        }

        //购物车列表
        if (mShopCartFrament == null) {
            mShopCartFrament = RouterCenter.toShopCart();
            transaction.add(R.id.fl_content, mShopCartFrament);
        }

        if (checkedId == R.id.rb_cart) {
            transaction.show(mShopCartFrament);
        }else {
            transaction.hide(mShopCartFrament);
        }

        transaction.commitAllowingStateLoss();
    }


}
