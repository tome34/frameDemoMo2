package com.example.welfare.module_welfare.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tome.projectCore.helper.ImageLoaderHelper;
import com.example.welfare.module_welfare.R;
import com.example.welfare.module_welfare.bean.PhotoGirlBean;

import java.util.List;

/**
 * @Created by TOME .
 * @时间 2018/6/6 10:35
 * @描述 ${TODO}
 */

public class WelfareV1Adapter extends BaseQuickAdapter<PhotoGirlBean.ResultsBean, BaseViewHolder> {

    public WelfareV1Adapter(int layoutResId, @Nullable List<PhotoGirlBean.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PhotoGirlBean.ResultsBean item) {
        if (item != null){
            ImageView ivPhoto = (ImageView) helper.getView(R.id.iv_photo);
            String url = item.getUrl();
            ImageLoaderHelper.getInstance().load(mContext, url, ivPhoto);
        }

    }

}
