package com.example.welfare.module_welfare.contract;

import com.example.tome.core.base.mvp.inter.IPresenter;
import com.example.tome.core.base.mvp.inter.IView;

import okhttp3.ResponseBody;

/**
 * @Created by TOME .
 * @时间 2018/6/12 16:41
 * @描述 ${TODO}
 */

public interface SaveImageContract {
    interface View extends IView{
        void showSaveSuccess(ResponseBody result);
    }

    interface Presenter extends IPresenter<View> {
        void downloadImage(String url);
    }
}
