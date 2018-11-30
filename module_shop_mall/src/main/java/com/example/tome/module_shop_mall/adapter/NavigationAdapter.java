package com.example.tome.module_shop_mall.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tome.core.util.OtherUtils;
import com.fec.core.router.arouter.IntentKV;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.activity.ArticleDetailActivity;
import com.example.tome.module_shop_mall.bean.NavigationBean;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.List;

/**
 * @author tome
 * @date 2018/7/18  16:16
 * @describe ${导航adapter}
 */
public class NavigationAdapter extends BaseQuickAdapter<NavigationBean,BaseViewHolder>{

    public NavigationAdapter(int layoutResId,@Nullable List<NavigationBean> data) {
        super(layoutResId,data);
    }

    @Override
    protected void convert(BaseViewHolder helper,NavigationBean item) {
        if (!TextUtils.isEmpty(item.getName())) {
            helper.setText(R.id.item_navigation_tv, item.getName());
        }
        TagFlowLayout mTagFlowLayout = helper.getView(R.id.item_navigation_flow_layout);
        List<NavigationBean.ArticlesBean> mArticles = item.getArticles();

        mTagFlowLayout.setAdapter(new TagAdapter<NavigationBean.ArticlesBean>(mArticles) {
            @Override
            public View getView(FlowLayout parent,int position,NavigationBean.ArticlesBean feedArticleData) {
                TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.mall_tab_layout_tv,
                    mTagFlowLayout, false);
                if (feedArticleData == null) {
                    return null;
                }
                String name = feedArticleData.getTitle();
                tv.setPadding(DensityUtil.dp2px(10), DensityUtil.dp2px(10),
                    DensityUtil.dp2px(10), DensityUtil.dp2px(10));
                tv.setText(name);
                tv.setTextColor(OtherUtils.randomColor());
                mTagFlowLayout.setOnTagClickListener((view, position1, parent1) -> {
                    startNavigationPager(view, position1, parent, mArticles);
                    return true;
                });
                return tv;
            }
        });
    }

    private void startNavigationPager(View view, int position, FlowLayout parent2, List<NavigationBean.ArticlesBean> mArticles) {
        //跳转到web详情页
        Intent intent = new Intent(parent2.getContext(), ArticleDetailActivity.class);
        intent.putExtra(IntentKV.K_ARTICLE_LINK, mArticles.get(position).getLink());
        intent.putExtra(IntentKV.K_ARTICLE_TITLE, mArticles.get(position).getTitle());
        parent2.getContext().startActivity(intent);

    }
}
