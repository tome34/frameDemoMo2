package com.example.tome.module_shop_cart.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.BindView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tome.core.util.SPUtil;
import com.fec.core.router.arouter.IntentKV;
import com.fec.core.router.arouter.RouterURLS;
import com.example.tome.module_shop_cart.R;
import com.example.tome.module_shop_cart.R2;
import com.example.tome.module_shop_cart.arouter.RouterCenter;
import com.example.tome.module_shop_cart.base.CommonActivity;
import com.example.tome.module_shop_cart.base.Contract;
import com.example.tome.module_shop_cart.net.OKHttpHelper;
import com.example.tome.module_shop_cart.utils.BasicTool;

@Route(path = RouterURLS.SHOP_CART_MAIN)
public class ShopCartActivity extends CommonActivity implements RadioGroup.OnCheckedChangeListener {


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

    private String mToken;
    //token是否存在
    private boolean isToken = false;

    @Override
    public int setContentLayout() {
        return R.layout.cart_activity_shop_cart;
    }


    @Override
    public void initTitle() {
        mToken = (String)SPUtil.get(this,IntentKV.K_TOKEN_VALUE,"");
        OKHttpHelper.token = mToken;

        if (BasicTool.isEmpty(OKHttpHelper.token)) {
            isToken = false;
        } else {
            isToken = true;
        }
    }

    @Override
    public void initView() {
        //radiobutton监听
        mRgTab.setOnCheckedChangeListener(this);
        //选择商品tab
        mRgTab.check(R.id.rb_product);

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        String flagStr = getIntent().getStringExtra(Contract.K_FRAGMENT);
        if ("1".equals(flagStr)){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(mProductFragment);
            transaction.show(mShopCartFrament);
            mRgTab.check(R.id.rb_cart);
        }
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
