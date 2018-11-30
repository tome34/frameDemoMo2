package com.example.tome.module_shop_mall.contract;

import com.example.tome.core.base.mvp.inter.IModel;
import com.example.tome.core.base.mvp.inter.IPresenter;
import com.example.tome.core.base.mvp.inter.IView;


/**
 * @Created by TOME .
 * @时间 2018/6/4 11:55
 * @描述 ${TODO}
 */

public interface ArticleDetailContract  {
    interface View extends IView{
        void showArticleData();
    }

    interface Presenter extends IPresenter<View> {

        void getArticleData();
    }

    interface Model extends IModel {

    }
}
