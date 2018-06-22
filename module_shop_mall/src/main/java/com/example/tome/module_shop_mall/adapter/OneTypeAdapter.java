package com.example.tome.module_shop_mall.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.bean.NavigationBean;

import java.util.List;

/**
 * @Created by TOME .
 * @时间 2018/6/20 15:08
 * @描述 ${一级分类}
 */

public class OneTypeAdapter extends BaseQuickAdapter<NavigationBean, BaseViewHolder> {

    public OneTypeAdapter(int layoutResId, @Nullable List<NavigationBean> data) {
        super(layoutResId, data);
    }

    public OneTypeAdapter(@Nullable List<NavigationBean> data) {
        super(R.layout.mall_item_one_type, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationBean item) {
        if (item != null) {

            CheckBox tvText = helper.getView(R.id.tv_txt);
            tvText.setChecked(item.getIscheck());
            tvText.setText(item.getName());
            if (item.getIscheck()){
                helper.setVisible(R.id.v_check, item.getIscheck());
                tvText.setTextColor(Color.argb(255,244, 148, 35));
            }else {
                helper.setVisible(R.id.v_check, item.getIscheck());
                tvText.setTextColor(Color.argb(255,205, 205, 205));
            }

            helper.addOnClickListener(R.id.tv_txt);
        }

    }
}
