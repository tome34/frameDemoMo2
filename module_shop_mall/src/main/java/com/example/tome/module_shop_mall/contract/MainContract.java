package com.example.tome.module_shop_mall.contract;

import com.example.tome.component_base.base.mvc.inter.BaseView;
import com.example.tome.component_base.base.mvp.inter.IModel;
import com.example.tome.component_base.base.mvp.inter.IPresenter;
import com.example.tome.component_base.base.mvp.inter.IView;
import com.example.tome.component_base.net.params.RequestMapParams;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;


/**
 * @Created by TOME .
 * @时间 2018/5/4 11:15
 * @描述 ${TODO}
 */

public interface MainContract {

    interface View extends IView {

        void showTestData(FeedArticleListData feedArticleListData);

    }

    interface Presenter extends IPresenter<View> {

        void getFeedArticleList(int page,RequestMapParams params);
    }

    interface Model extends IModel {

    }
}
