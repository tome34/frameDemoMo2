package com.example.tome.module_shop_mall.presenter;

import com.example.tome.component_base.base.mvp.BasePresenter;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;
import com.example.tome.module_shop_mall.contract.HomeContract;
import com.example.tome.module_shop_mall.model.HomeModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * @Created by TOME .
 * @时间 2018/5/31 14:01
 * @描述 ${TODO}
 */

public class HomePresenter extends BasePresenter<HomeContract.View, HomeContract.Model> implements HomeContract.Presenter {

    //接收有参构造函数
    public HomePresenter(String type) {
    }

    public HomePresenter() {
    }

    @Override
    protected HomeContract.Model createModel() {
        return new HomeModel(this);
    }

    @Override
    public void attachView(HomeContract.View view) {
        super.attachView(view);
    }


    @Override
    public void onRefresh() {
        mModel.getBannerData();
       // mModel.getFeedArticleListV2();
     //   mView.showArticleList();
    }

    @Override
    public void BannerData() {
        mModel.getBannerData();
    }

    @Override
    public void FeedArticleList(SmartRefreshLayout rlRefreshLayout, int page) {

    }

    @Override
    public void ArticleList(FeedArticleListData feedArticleListData) {

    }


}
