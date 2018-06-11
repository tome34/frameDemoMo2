package com.example.welfare.module_welfare.contract;

import com.example.tome.component_base.base.inter.AbstractPresenter;
import com.example.tome.component_base.base.inter.BaseView;
import com.example.welfare.module_welfare.bean.PhotoGirlBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

/**
 * @Created by TOME .
 * @时间 2018/6/5 16:33
 * @描述 ${TODO}
 */

public interface WelfareContract {
    interface View extends BaseView{
       void showPhotosListData(PhotoGirlBean photoGirls);
    }

    interface Presenter extends AbstractPresenter<View>{
        void getPhotosListData(SmartRefreshLayout rlRefreshLayout, int size, int page);
    }
}
