package com.example.tome.module_shop_mall.contract;

import com.example.tome.component_base.base.inter.AbstractPresenter;
import com.example.tome.component_base.base.inter.BaseView;
import com.example.tome.module_shop_mall.bean.KnowledgeSystemBean;

import java.util.List;

/**
 * @Created by TOME .
 * @时间 2018/6/15 17:36
 * @描述 ${TODO}
 */

public interface KnowledgeSystemContract {
    interface View extends BaseView{
        void showKnowledgeSystem(List<KnowledgeSystemBean> result);
    }

    interface Presenter extends AbstractPresenter<View>{
        void getKnowledgeSystemData();
    }
}
