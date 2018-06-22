package com.example.tome.module_shop_mall.adapter;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tome.component_base.widget.FlowLayout;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.bean.NavigationBean;

import java.util.List;
import java.util.zip.Inflater;

/**
 * @Created by TOME .
 * @时间 2018/6/20 15:08
 * @描述 ${二级分类}
 */

public class TwoTypeAdapter extends BaseQuickAdapter<NavigationBean.ArticlesBean, BaseViewHolder>{

    public TwoTypeAdapter(@Nullable List<NavigationBean.ArticlesBean> data) {
        super(R.layout.mall_item_knowledge_tv, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationBean.ArticlesBean item) {
        if (item != null){
            helper.setText(R.id.commonItemTitle, item.getTitle());

            helper.addOnClickListener(R.id.commonItemTitle);
        }

    }
}
