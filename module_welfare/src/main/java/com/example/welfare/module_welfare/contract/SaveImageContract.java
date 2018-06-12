package com.example.welfare.module_welfare.contract;

import com.example.tome.component_base.base.inter.AbstractPresenter;
import com.example.tome.component_base.base.inter.BaseView;

/**
 * @Created by TOME .
 * @时间 2018/6/12 16:41
 * @描述 ${TODO}
 */

public interface SaveImageContract {
    interface View extends BaseView{
        void showSaveSuccess();
    }

    interface Presenter extends AbstractPresenter<View>{
        void downloadImage();
    }
}
