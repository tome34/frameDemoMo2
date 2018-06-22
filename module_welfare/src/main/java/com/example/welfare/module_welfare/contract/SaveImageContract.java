package com.example.welfare.module_welfare.contract;

import com.example.tome.component_base.base.inter.AbstractPresenter;
import com.example.tome.component_base.base.inter.BaseView;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * @Created by TOME .
 * @时间 2018/6/12 16:41
 * @描述 ${TODO}
 */

public interface SaveImageContract {
    interface View extends BaseView{
        void showSaveSuccess(ResponseBody result);
    }

    interface Presenter extends AbstractPresenter<View>{
        void downloadImage(String url);
    }
}
