package com.example.tome.module_shop_mall.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import com.example.tome.component_base.base.BaseMVPActivity;
import com.example.tome.component_base.util.L;

import com.example.tome.component_data.d_arouter.RouterURLS;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.arouter.RouterCenter;
import com.example.tome.module_shop_mall.bean.FeedArticleListResponse;
import com.example.tome.module_shop_mall.bean.LoginResponse;
import com.example.tome.module_shop_mall.contract.MainContract;
import com.example.tome.module_shop_mall.presenter.MainPresenter;

import butterknife.BindView;


@Route(path = RouterURLS.BASE_MAIN)
public class MainActivity extends BaseMVPActivity<MainPresenter> implements MainContract.View, View.OnClickListener {

    @BindView(R2.id.tv_test)
    Button mTvTest;
    @BindView(R2.id.tv_login)
    Button mTvLogin;
    @BindView(R2.id.tv_login_test)
    Button mTvLoginTest;
    @BindView(R2.id.tv_data)
    TextView mTvData;



    /**
     * 初始化布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.mall_activity_main;
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    /**
     * 初始化事件和数据
     */
    @Override
    protected void initEventAndData() {
        mTvTest.setOnClickListener(this);
        mTvLogin.setOnClickListener(this);
        mTvLoginTest.setOnClickListener(this);
    }

    /**
     * 展示数据
     * @param loginResponse
     */
    @Override
    public void showLoginData(LoginResponse loginResponse) {
        mTvData.setText(loginResponse.toString());
    }

    /**
     *
     * @param feedArticleListResponse
     */
    @Override
    public void showArticleList(FeedArticleListResponse feedArticleListResponse) {
        L.d("获取数据","成功");
        mTvData.setText(feedArticleListResponse.getData().getCurPage() +"");
    }

    @Override
    public void showArticleListFail() {
        L.d("获取数据","失败");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_test){
            L.d("点击了");
            RouterCenter.toShopCart(this);
        }else if (v.getId() == R.id.tv_login){
            //网络请求
            mPresenter.getFeedArticleList(0);
        }else if (v.getId() == R.id.tv_login_test){
            RouterCenter.toHome();
        }
    }
}
