package com.example.tome.module_shop_mall.activity;

import android.Manifest;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tome.projectCore.base.mvc.BaseVcPermissionActivity;
import com.example.tome.core.util.L;
import com.example.tome.core.util.ToastUtils;
import com.fec.core.router.arouter.RouterURLS;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.arouter.RouterCenter;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;

@Route(path = RouterURLS.BASE_MAIN)
//public class MainActivity extends BaseVpPermissionActivity<MainPresenter> implements MainContract.View, View.OnClickListener {
public class MainActivity extends BaseVcPermissionActivity implements  View.OnClickListener {

    @BindView(R2.id.layout_main)
    LinearLayout mLayoutMain;
    @BindView(R2.id.tv_test)
    Button mTvTest;
    @BindView(R2.id.tv_get_data)
    Button mTvGetData;
    @BindView(R2.id.tv_get_data2)
    Button mTvGetData2;
    @BindView(R2.id.tv_get_data_mvc)
    Button mTvGetDataMVC;
    @BindView(R2.id.tv_goto_home)
    Button mTvGotoHome;
    @BindView(R2.id.tv_list)
    Button mTvList;
    @BindView(R2.id.tv_data)
    TextView mTvData;
    @BindView(R2.id.v_title_container)
    LinearLayout vTitleContainer;
    @BindView(R2.id.title_back)
    TextView mBack;
    @BindView(R2.id.title_content_text)
    TextView mTitle;

    private long exitTime = 0;

    /**
     * 初始化布局
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.mall_activity_main;
    }

    /**
     * 初始化标题
     */
    @Override
    protected void initTitle() {
        mImmersionBar.titleBar(R.id.rl_title_bar_content).init();
      //  mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.colorPrimary).init();
      //  mLayoutMain.setBackgroundColor(getResources().getColor(R.color.windowBg));
       // vTitleContainer.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mBack.setVisibility(View.GONE);
        mTitle.setText("欢迎您的到来!");
    }

    /**
     * 初始化事件和数据
     */
    @Override
    protected void initView() {
        mTvTest.setOnClickListener(this);
        mTvGetData.setOnClickListener(this);
        mTvGetData2.setOnClickListener(this);
        mTvGetDataMVC.setOnClickListener(this);
        mTvGotoHome.setOnClickListener(this);
        mTvList.setOnClickListener(this);
    }

    /**
     * 显示测试数据
     * @param feedArticleListData
     */
    public void showTestData(FeedArticleListData feedArticleListData) {
        mTvData.setText("成功获取"+feedArticleListData.getDatas().size()+"条数据");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_test) {
            L.d("点击了");
            RouterCenter.toShopCart();
        } else if (v.getId() == R.id.tv_get_data) {
            //测试网络请求mvp模式
            RouterCenter.toMVPTest();

        }else if (v.getId() == R.id.tv_get_data2) {
            //测试网络请求mvp模式(没有model层)
            RouterCenter.toMVPTest2();

        } else if (v.getId() == R.id.tv_get_data_mvc){
            if (! getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, PERMISSION_STORAGE)){
                return;
            }

            if (! getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, PERMISSION_STORAGE)){
                return;
            }
            if (! getPermission(Manifest.permission.CAMERA, PERMISSION_CAMERA)){
                return;
            }
            //测试网络请求mvc模式
            RouterCenter.toMVCTest();
        } else if (v.getId() == R.id.tv_goto_home) {
            RouterCenter.toHome();
        } else if (v.getId() == R.id.tv_list){
            RouterCenter.toCustomControl();
        }
    }

    @Override
    public void onBackPressed() {
        //退出提示
        if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtils.showShort(this, getString(R.string.exit));
            exitTime = System.currentTimeMillis();
        } else {
            exitApp();
            super.onBackPressed();
        }
    }
}
