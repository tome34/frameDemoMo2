package com.example.tome.module_shop_mall.contract;

import android.support.v7.widget.RecyclerView;

import com.example.tome.component_base.base.mvc.inter.BaseView;
import com.example.tome.component_base.base.mvp.inter.IModel;
import com.example.tome.component_base.base.mvp.inter.IPresenter;
import com.example.tome.component_base.base.mvp.inter.IView;
import com.example.tome.component_data.bean.BaseObj;
import com.example.tome.module_shop_mall.bean.BannerData;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;
import com.example.tome.module_shop_mall.bean.LoginBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import io.reactivex.Observable;

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
       // void showArticleList(FeedArticleListData feedArticleListData);

        /**
         * 显示轮播图
         */
       // void showBannerData(List<BannerData> bannerDataList);


    }

    interface Presenter extends IPresenter<View> {
        /**
         * 加载轮播图
         */
        void  BannerData();

        /**
         * 获取文章数据
         * @param isRefresh
         * @param rlRefreshLayout
         * @param page
         */
        void  FeedArticleList(boolean isRefresh, SmartRefreshLayout rlRefreshLayout, int page);

        /**
         * 轮播图自动播放
         */
        void startBannerPlay();

        /**
         * 轮播图停止播放
         */
        void stopBannerPlay();
    }

    interface Model extends IModel{
        /**
         * 获取条目数据
         * @param page
         */
        Observable<BaseObj<FeedArticleListData>> getFeedArticleList(int page);

        /**
         * 获取轮播图数据
         */
        Observable<BaseObj<List<BannerData>>> getBannerData();
    }



}

