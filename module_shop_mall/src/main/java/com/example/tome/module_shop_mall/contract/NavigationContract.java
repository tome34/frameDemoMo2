package com.example.tome.module_shop_mall.contract;

import com.example.tome.component_base.base.inter.AbstractPresenter;
import com.example.tome.component_base.base.inter.BaseView;
import com.example.tome.module_shop_mall.bean.NavigationBean;

import java.util.List;

/**
 * @Created by TOME .
 * @时间 2018/6/20 12:01
 * @描述 ${TODO}
 */

public interface NavigationContract {
    interface View extends BaseView{
        void showNavigation(List<NavigationBean> result);
    }

    interface Presenter extends AbstractPresenter<View>{
        void getNavigationData();
    }
}
