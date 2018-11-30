package com.example.tome.module_shop_mall.contract;

import com.example.tome.core.base.mvp.inter.IModel;
import com.example.tome.core.base.mvp.inter.IPresenter;
import com.example.tome.core.base.mvp.inter.IView;
import com.example.tome.core.net.params.RequestMapParams;
import com.example.tome.projectCore.bean.BaseObj;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;
import io.reactivex.Observable;


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

       void initFeedArticleList();
       void initFeedArticleList2();
    }

    interface Model extends IModel {

        Observable<BaseObj<FeedArticleListData>> getFeedArticleList(int page, RequestMapParams params);
    }
}
