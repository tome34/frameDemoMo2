package com.example.tome.module_shop_mall.contract;

import com.example.tome.core.base.mvp.inter.IModel;
import com.example.tome.core.base.mvp.inter.IPresenter;
import com.example.tome.core.base.mvp.inter.IView;
import com.example.tome.module_shop_mall.bean.NavigationBean;

import java.util.List;

/**
 * @Created by TOME .
 * @时间 2018/6/20 12:01
 * @描述 ${TODO}
 */

public interface NavigationContract {
    interface View extends IView{
        void showNavigation(List<NavigationBean> result);
    }

    interface Presenter extends IPresenter<View> {
        void getNavigation();
    }

    interface Model extends IModel {
        void getNavigationData();
    }
}
