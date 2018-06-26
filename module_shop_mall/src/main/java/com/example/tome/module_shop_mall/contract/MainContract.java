package com.example.tome.module_shop_mall.contract;

import com.example.tome.component_base.base.inter.AbstractPresenter;
import com.example.tome.component_base.base.inter.BaseView;
import com.example.tome.component_base.net.params.RequestMapParams;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;
import com.example.tome.module_shop_mall.bean.FeedArticleListResponse;
import com.example.tome.module_shop_mall.bean.LoginResponse;
import com.example.tome.module_shop_mall.params.LoginParams;
import com.example.tome.module_shop_mall.params.TestParams;


/**
 * @Created by TOME .
 * @时间 2018/5/4 11:15
 * @描述 ${TODO}
 */

public interface MainContract {

    interface View extends BaseView {

        void showTestData(FeedArticleListData feedArticleListData);

    }

    interface Presenter extends AbstractPresenter<View> {

        void getFeedArticleList(int page,RequestMapParams params);
    }
}
