package com.example.welfare.module_welfare.contract;

import com.example.tome.core.base.mvp.inter.IPresenter;
import com.example.tome.core.base.mvp.inter.IView;
import com.example.welfare.module_welfare.bean.PhotoGirlBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * @Created by TOME .
 * @时间 2018/6/5 16:33
 * @描述 ${TODO}
 */

public interface WelfareContract {
    interface View extends IView{
       void showPhotosListData(PhotoGirlBean photoGirls);
    }

    interface Presenter extends IPresenter<View> {
        void getPhotosListData(SmartRefreshLayout rlRefreshLayout, int size, int page);
    }
}
