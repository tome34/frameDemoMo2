package com.example.tome.module_shop_mall.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tome.core.util.OtherUtils;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.bean.SearchHistoryBean;
import java.util.List;

/**
 * @author quchao
 * @date 2018/3/23
 */

public class HistorySearchAdapter extends BaseQuickAdapter<SearchHistoryBean,BaseViewHolder> {

    public HistorySearchAdapter(int layoutResId, @Nullable List<SearchHistoryBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,SearchHistoryBean item) {
        helper.setText(R.id.item_search_history_tv, item.getKeyword());
        helper.setTextColor(R.id.item_search_history_tv, OtherUtils.randomColor());

        helper.addOnClickListener(R.id.item_search_history_tv);
    }
}
