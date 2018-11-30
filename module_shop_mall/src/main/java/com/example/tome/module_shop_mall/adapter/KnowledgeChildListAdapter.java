package com.example.tome.module_shop_mall.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tome.projectCore.helper.ImageLoaderHelper;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.bean.KnowledgeChildBean;

import java.util.List;

/**
 * @Created by TOME .
 * @时间 2018/6/1 13:49
 * @描述 ${adapter}
 */

public class KnowledgeChildListAdapter extends BaseQuickAdapter<KnowledgeChildBean.DatasBean, BaseViewHolder> {

   private boolean isSearchPage = false;
    private boolean isCollectPage = false;

    public KnowledgeChildListAdapter(@Nullable List<KnowledgeChildBean.DatasBean> data) {
        super(R.layout.mall_item_knowledge_child, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KnowledgeChildBean.DatasBean item) {
        if (!TextUtils.isEmpty(item.getTitle())) {
            helper.setText(R.id.item_knowledge_title, Html.fromHtml(item.getTitle()));
        }
        if (!TextUtils.isEmpty(item.getAuthor())) {
            helper.setText(R.id.item_knowledge_author, mContext.getString(R.string.mall_item_article_author, item.getAuthor()));
        }
        if (!TextUtils.isEmpty(item.getChapterName())) {
            helper.setText(R.id.item_knowledge_chapterName, mContext.getString(R.string.mall_item_article_classify, item.getChapterName()));
        }
        if (!TextUtils.isEmpty(item.getNiceDate())) {
            helper.setText(R.id.item_knowledge_niceDate, mContext.getString(R.string.mall_item_article_time, item.getNiceDate()));
        }
        if (!TextUtils.isEmpty(item.getEnvelopePic())) {
            String envelopePic = item.getEnvelopePic();
            ImageView pic = (ImageView)helper.getView(R.id.item_knowledge_iv);
            ImageLoaderHelper.getInstance().load(mContext, envelopePic, pic);
        }
        if (isSearchPage) {
            CardView cardView = helper.getView(R.id.item_home_card_view);
            cardView.setForeground(null);
            cardView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.mall_selector_search_item_bac));
        }


    }
}
