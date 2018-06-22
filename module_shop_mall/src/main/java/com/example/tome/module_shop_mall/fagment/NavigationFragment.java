package com.example.tome.module_shop_mall.fagment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tome.component_base.base.BaseMVPActivity;
import com.example.tome.component_base.base.BaseMVPFragment;
import com.example.tome.component_base.util.L;
import com.example.tome.component_base.widget.MyRecyclerView;
import com.example.tome.component_data.d_arouter.IntentKV;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.activity.ArticleDetailActivity;
import com.example.tome.module_shop_mall.adapter.OneTypeAdapter;
import com.example.tome.module_shop_mall.adapter.TwoTypeAdapter;
import com.example.tome.module_shop_mall.bean.NavigationBean;
import com.example.tome.module_shop_mall.contract.NavigationContract;
import com.example.tome.module_shop_mall.presenter.NavigationPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Created by TOME .
 * @时间 2018/6/20 11:58
 * @描述 ${导航页面}
 */

public class NavigationFragment extends BaseMVPFragment<NavigationPresenter> implements NavigationContract.View, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R2.id.title_back)
    TextView mTitleBack;
    @BindView(R2.id.title_content_text)
    TextView mTitleContentText;
    @BindView(R2.id.title_right_text)
    TextView mTitleRightText;
    @BindView(R2.id.title_right_icon)
    ImageView mTitleRightIcon;
    @BindView(R2.id.v_title_container)
    LinearLayout mVTitleContainer;
    @BindView(R2.id.rl_title_bar_content)
    RelativeLayout mRlTitleBarContent;
    @BindView(R2.id.rv_one_title)
    RecyclerView mRvOneTitle;
    @BindView(R2.id.rv_two_sub)
    RecyclerView mRvTwoSub;

    List<NavigationBean> mNavigationList ;
    List<NavigationBean.ArticlesBean> mNavigationitemList ;
    OneTypeAdapter mOneTypeAdapter ;
    TwoTypeAdapter mTwoTypeAdapter ;

    private static final int MAX = 9;
    private List<NavigationBean.ArticlesBean> mArticles;


    @Override
    protected NavigationPresenter getPresenter() {
        return new NavigationPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.mall_fragment_navigation;
    }


    @Override
    protected void initTitle() {
        mTitleContentText.setText("导航");

    }

    @Override
    protected void initView() {
        mPresenter.getNavigationData();
        mNavigationList = new ArrayList<>();
        mNavigationitemList = new ArrayList<>();
        mArticles = new ArrayList<>();

        mRvOneTitle.setLayoutManager(new LinearLayoutManager(mContext));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        mRvTwoSub.setLayoutManager(gridLayoutManager);

    }


    @Override
    public void showNavigation(List<NavigationBean> result) {
        L.d("请求成功:"+result.size());
        if (result != null){
            mNavigationList = result ;

            //一级
            mNavigationList.get(0).setIscheck(true);
            mOneTypeAdapter = new OneTypeAdapter(mNavigationList);
            mOneTypeAdapter.setOnItemChildClickListener(this);
            //一行代码开启动画 默认CUSTOM动画
            mOneTypeAdapter.openLoadAnimation(BaseQuickAdapter.FOOTER_VIEW);
            mRvOneTitle.setAdapter(mOneTypeAdapter);

            //二级
            mNavigationitemList = mNavigationList.get(0).getArticles();
            mTwoTypeAdapter = new TwoTypeAdapter(mNavigationitemList);
            mTwoTypeAdapter.setOnItemChildClickListener(this);
            mRvTwoSub.setAdapter(mTwoTypeAdapter);
        }

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (view.getId() == R.id.tv_txt){
            L.d("点击了"+position);
            for (NavigationBean data : mNavigationList){
                data.setIscheck(false);
            }
            mNavigationList.get(position).setIscheck(true);
            mOneTypeAdapter.notifyDataSetChanged();

//            L.d("点击了数据"+mNavigationList.size());
            mNavigationitemList = mNavigationList.get(position).getArticles();
//            L.d("点击了+"+mNavigationitemList.size());
//            mTwoTypeAdapter.notifyDataSetChanged();
            mTwoTypeAdapter = new TwoTypeAdapter(mNavigationitemList);
            mTwoTypeAdapter.setOnItemChildClickListener(this);
            mRvTwoSub.setAdapter(mTwoTypeAdapter);
            //mTwoTypeAdapter.replaceData(mNavigationitemList);



        }else if (view.getId() == R.id.commonItemTitle){
            //跳转到web详情页
            Intent intent = new Intent(mContext, ArticleDetailActivity.class);
            intent.putExtra(IntentKV.K_ARTICLE_LINK, mNavigationitemList.get(position).getLink());
            intent.putExtra(IntentKV.K_ARTICLE_TITLE, mNavigationitemList.get(position).getTitle());

            mContext.startActivity(intent);
        }
    }
}
