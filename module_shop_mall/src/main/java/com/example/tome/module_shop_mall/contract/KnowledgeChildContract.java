package com.example.tome.module_shop_mall.contract;

import com.example.tome.core.base.mvp.inter.IModel;
import com.example.tome.core.base.mvp.inter.IPresenter;
import com.example.tome.core.base.mvp.inter.IView;
import com.example.tome.module_shop_mall.bean.KnowledgeChildBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * @Created by TOME .
 * @时间 2018/6/19 20:29
 * @描述 ${TODO}
 */

public interface KnowledgeChildContract  {
    interface View extends IView{
        void showKnowledgeChild(KnowledgeChildBean result);
    }

    interface Presenter extends IPresenter<View> {
        void getKnowledgeChild(int page, int cid, SmartRefreshLayout mRefreshLayout);
    }

    interface Model extends IModel {

    }
}
