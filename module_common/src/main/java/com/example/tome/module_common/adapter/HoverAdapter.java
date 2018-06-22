package com.example.tome.module_common.adapter;

import android.support.annotation.Nullable;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tome.module_common.R;
import com.example.tome.module_common.bean.HoverItemBean;

import java.util.List;

/**
 * 吸顶效果 adapter
 */

public class HoverAdapter extends BaseQuickAdapter<HoverItemBean,BaseViewHolder> {

    public HoverAdapter(@Nullable List<HoverItemBean> data) {
        super(R.layout.adapter_item_hover_user, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HoverItemBean item) {
        helper.setText(R.id.user_name_tv,item.getUserName());
    }

}
