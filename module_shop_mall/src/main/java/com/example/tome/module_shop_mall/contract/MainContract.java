package com.example.tome.module_shop_mall.contract;

import com.example.tome.component_base.base.AbstractPresenter;
import com.example.tome.component_base.base.BaseView;
import com.example.tome.module_shop_mall.bean.FeedArticleListResponse;
import com.example.tome.module_shop_mall.bean.LoginResponse;
import com.example.tome.module_shop_mall.params.LoginParams;


/**
 * @Created by TOME .
 * @时间 2018/5/4 11:15
 * @描述 ${TODO}
 */

public interface MainContract {

    interface View extends BaseView {

        void showLoginData(LoginResponse loginResponse);

        void showArticleList(FeedArticleListResponse feedArticleListResponse);

        void showArticleListFail();
    }

    interface Presenter extends AbstractPresenter<View> {

        void getLoginData(LoginParams loginParams);

        void getFeedArticleList(int page);
    }
}
