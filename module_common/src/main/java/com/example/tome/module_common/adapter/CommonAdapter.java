package com.example.tome.module_common.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tome.module_common.R;
import com.example.tome.module_common.bean.CommonBean;

import java.util.List;

/**
 * @Created by TOME .
 * @时间 2018/6/22 12:01
 * @描述 ${自定义控件页面的adapter}
 */

public class CommonAdapter  extends BaseQuickAdapter<CommonBean, BaseViewHolder>{

    public CommonAdapter(int layoutResId, @Nullable List<CommonBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommonBean item) {
        if (item != null){
            helper.setText(R.id.item_common_title, item.getTitle());
        }

    }
}
