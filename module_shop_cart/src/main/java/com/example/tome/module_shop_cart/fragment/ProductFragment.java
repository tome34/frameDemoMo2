package com.example.tome.module_shop_cart.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tome.core.util.SPUtil;
import com.example.tome.core.util.ToastUtils;
import com.fec.core.router.arouter.IntentKV;
import com.fec.core.router.arouter.RouterURLS;
import com.example.tome.module_shop_cart.R;
import com.example.tome.module_shop_cart.R2;
import com.example.tome.module_shop_cart.activity.LoginActivity;
import com.example.tome.module_shop_cart.activity.ProductDetailActivity;
import com.example.tome.module_shop_cart.base.CommonFragment;
import com.example.tome.module_shop_cart.base.Contract;
import com.example.tome.module_shop_cart.net.OKHttpHelper;

/**
 * @Created by TOME .
 * @时间 2018/6/13 20:49
 * @描述 ${商品列表}
 */

@Route(path = RouterURLS.PRODUCT_GOODS)
public class ProductFragment extends CommonFragment implements View.OnClickListener {

    @BindView(R2.id.title_back)
    TextView mTitleBack;
    @BindView(R2.id.btn_detail)
    Button mBtnDetail;
    @BindView(R2.id.btn_login)
    Button mBtnLogin;
    @BindView(R2.id.btn_exit_login)
    Button mBtnExitLogin;
    private Intent mIntent;

    public static ProductFragment newInstance(String info) {
        Bundle args = new Bundle();
        ProductFragment fragment = new ProductFragment();
        args.putString("info",info);
        //fragment.setArguments(args);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int setContentLayout() {
        return R.layout.cart_product_fragment;
    }

    @Override
    public void initTitle() {
    }

    @Override
    public void initView() {
        mBtnDetail.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mBtnExitLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_detail){
            mIntent = new Intent(mContext,ProductDetailActivity.class);
            mIntent.putExtra(Contract.K_PRODUCT_ID ,"93");
            startActivity(mIntent);
        }else if (v.getId() == R.id.btn_login){
            mIntent = new Intent(mContext,LoginActivity.class);
            startActivity(mIntent);
        }else if (v.getId() == R.id.btn_exit_login){
            //退出登录
            ToastUtils.showCenter("已退出登录");
            SPUtil.remove(mContext, IntentKV.K_TOKEN_VALUE);
            OKHttpHelper.token = "";
        }
    }
}
