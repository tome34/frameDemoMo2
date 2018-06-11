package com.example.tome.module_shop_mall.contract;

import com.example.tome.component_base.base.BasePresenter;
import com.example.tome.component_base.base.inter.AbstractPresenter;
import com.example.tome.component_base.base.inter.BaseView;


/**
 * @Created by TOME .
 * @时间 2018/6/4 11:55
 * @描述 ${TODO}
 */

public interface ArticleDetailContract  {
    interface View extends BaseView{
        void showArticleData();
    }

    interface Presenter extends AbstractPresenter<View>{

        void getArticleData();
    }
}
