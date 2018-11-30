package com.example.tome.module_shop_mall.contract;

import com.example.tome.core.base.mvp.inter.IModel;
import com.example.tome.core.base.mvp.inter.IPresenter;
import com.example.tome.core.base.mvp.inter.IView;
import com.example.tome.module_shop_mall.bean.KnowledgeSystemBean;

import java.util.List;

/**
 * @Created by TOME .
 * @时间 2018/6/15 17:36
 * @描述 ${TODO}
 */

public interface KnowledgeSystemContract {
    interface View extends IView{
        void showKnowledgeSystem(List<KnowledgeSystemBean> result);
    }

    interface Presenter extends IPresenter<View> {
        void getKnowledgeSystemData();
    }

    interface Model extends IModel {

    }
}
