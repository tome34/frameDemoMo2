package com.example.tome.module_shop_mall.activity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tome.component_base.base.mvc.BaseVcPermissionActivity;
import com.example.tome.component_base.base.mvp.BaseVpPermissionActivity;
import com.example.tome.component_base.net.common_callback.INetCallback;
import com.example.tome.component_base.net.file_upload.FileRequestMapParams;
import com.example.tome.component_base.net.params.RequestMapParams;
import com.example.tome.component_base.util.L;
import com.example.tome.component_data.bean.BaseObj;
import com.example.tome.component_data.d_arouter.RouterURLS;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.api.ApiService;
import com.example.tome.module_shop_mall.api.ModelVcService;
import com.example.tome.module_shop_mall.arouter.RouterCenter;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;
import com.example.tome.module_shop_mall.contract.MainContract;
import com.example.tome.module_shop_mall.presenter.MainPresenter;

import butterknife.BindView;
import io.reactivex.Observable;
import okhttp3.MultipartBody;


@Route(path = RouterURLS.BASE_MAIN)
//public class MainActivity extends BaseVpPermissionActivity<MainPresenter> implements MainContract.View, View.OnClickListener {
public class MainActivity extends BaseVcPermissionActivity implements  View.OnClickListener {

    @BindView(R2.id.layout_main)
    LinearLayout mLayoutMain;
    @BindView(R2.id.tv_test)
    Button mTvTest;
    @BindView(R2.id.tv_get_data)
    Button mTvGetData;
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
        mLayoutMain.setBackgroundColor(getResources().getColor(R.color.windowBg));
        vTitleContainer.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
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
            FileRequestMapParams fileParam = new FileRequestMapParams(); //文件上传
            fileParam.put("file", "path");
            MultipartBody build = fileParam.build();
            RequestMapParams params = new RequestMapParams();
            params.put("key","");
            loadData(0,params);
            //mPresenter.getFeedArticleList(0,params);
        } else if (v.getId() == R.id.tv_get_data_mvc){
            //测试网络请求mvc模式
            RouterCenter.toMVCTest();
        } else if (v.getId() == R.id.tv_goto_home) {
            RouterCenter.toHome();
        } else if (v.getId() == R.id.tv_list){
            RouterCenter.toCustomControl();
        }
    }


    protected void loadData(int page, RequestMapParams params) {
        addDisposable(ModelVcService.getRemoteData(true, mView, new ModelVcService.MethodSelect<FeedArticleListData>() {
            @Override
            public Observable<BaseObj<FeedArticleListData>> selectM(ApiService service) {
                return service.getFeedArticleList(page, params.build());
            }
        }, new INetCallback<FeedArticleListData>() {
            @Override
            public void onSuccess(FeedArticleListData result) {
                L.d("成功返回数据"+result.getCurPage());
                showTestData(result);
            }
        }));
    }

}
