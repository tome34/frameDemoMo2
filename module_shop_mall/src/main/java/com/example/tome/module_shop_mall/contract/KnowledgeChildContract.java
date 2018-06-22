package com.example.tome.module_shop_mall.contract;

import com.example.tome.component_base.base.inter.AbstractPresenter;
import com.example.tome.component_base.base.inter.BaseView;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;
import com.example.tome.module_shop_mall.bean.KnowledgeChildBean;
import com.example.tome.module_shop_mall.bean.KnowledgeSystemBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

/**
 * @Created by TOME .
 * @时间 2018/6/19 20:29
 * @描述 ${TODO}
 */

public interface KnowledgeChildContract  {
    interface View extends BaseView{
        void showKnowledgeChild(KnowledgeChildBean result);
    }

    interface Presenter extends AbstractPresenter<View>{
        void getKnowledgeChild(int page, int cid, SmartRefreshLayout mRefreshLayout);
    }
}
