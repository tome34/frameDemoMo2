package com.example.tome.module_shop_mall.contract;

import com.example.tome.component_base.base.mvc.inter.BaseView;
import com.example.tome.component_base.base.mvp.inter.IModel;
import com.example.tome.component_base.base.mvp.inter.IPresenter;
import com.example.tome.component_base.base.mvp.inter.IView;
import com.example.tome.module_shop_mall.bean.BannerData;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

/**
 * @Created by TOME .
 * @时间 2018/5/31 14:35
 * @描述 ${TODO}
 */

public interface HomeContract {
    interface View extends IView {

        /**
         * 显示条目数据
         * @param feedArticleListData
         */
        void showArticleList(FeedArticleListData feedArticleListData);

        /**
         * 显示轮播图
         * @param bannerDataList
         */
        void showBannerData(List<BannerData> bannerDataList);

    }

    interface Presenter extends IPresenter<View> {

        void onRefresh();
        void  BannerData();
        void  FeedArticleList(SmartRefreshLayout rlRefreshLayout, int page);
        void ArticleList(FeedArticleListData feedArticleListData);
    }

    interface Model extends IModel{
        /**
         * 获取条目数据
         * @param page
         */
        void getFeedArticleList(int page);

        void getFeedArticleListV2(SmartRefreshLayout rlRefreshLayout, int page);

        /**
         * 获取轮播图数据
         */
        void getBannerData();
    }



}

