package com.example.tome.module_shop_mall.fagment;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tome.component_base.base.mvc.BaseObserver;
import com.example.tome.component_base.base.mvc.BaseVcListFragment;
import com.example.tome.component_base.base.mvp.BaseVpListFragment;
import com.example.tome.component_base.net.common_callback.INetCallback;
import com.example.tome.component_base.util.L;
import com.example.tome.component_data.bean.BaseObj;
import com.example.tome.component_data.bean.EventBusBean;
import com.example.tome.component_data.d_arouter.IntentKV;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.activity.ArticleDetailActivity;
import com.example.tome.module_shop_mall.adapter.HomeListAdapter;
import com.example.tome.module_shop_mall.api.ApiService;
import com.example.tome.module_shop_mall.api.ModelVcService;
import com.example.tome.module_shop_mall.bean.BannerData;
import com.example.tome.module_shop_mall.bean.FeedArticleData;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;
import com.example.tome.module_shop_mall.contract.HomeContract;
import com.example.tome.module_shop_mall.helper.GlideImageLoader;
import com.example.tome.module_shop_mall.presenter.HomePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * @Created by TOME .
 * @时间 2018/5/30 16:51
 * @描述 ${首页}
 */

//public class HomeFragment extends BaseVpListFragment<HomePresenter> implements HomeContract.View {
public class HomeFragment extends BaseVcListFragment implements HomeContract.View{

    @BindView(R2.id.iv_left)
    ImageView mIvLeft;
    @BindView(R2.id.et_comment_search)
    EditText mEtCommentSearch;
    @BindView(R2.id.lin_title_search)
    LinearLayout mLinTitleSearch;
    @BindView(R2.id.iv_right)
    ImageView mIvRight;
    @BindView(R2.id.iv_right2)
    ImageView mIvRight2;
    @BindView(R2.id.tv_cart_count)
    TextView mTvCartCount;
    @BindView(R2.id.rl_shop_cart)
    RelativeLayout mRlShopCart;
    @BindView(R2.id.tv_right_filter)
    TextView mTvRightFilter;
    @BindView(R2.id.v_title_container)
    LinearLayout mVTitleContainer;

    @BindView(R2.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R2.id.srl_layout)
    SmartRefreshLayout mSrlLayout;
    @BindView(R2.id.layout_home)
    LinearLayout mLayoutHome;


    private int articlePosition;
    private List<String> mBannerTitleList;
    private List<String> mBannerUrlList;
    private Banner mBanner;
    private int mCurrentPage;
    private List<FeedArticleData> mFeedArticleDataList;
    private HomeListAdapter mAdapter;
    private List<String> mBannerImageList;

//    @Override
//    public HomePresenter createPresenter() {
//        return new HomePresenter();
//    }

    @Override
    protected int getLayout() {
        return R.layout.mall_fragment_home;
    }

    @Override
    protected void initTitle() {
        mIvLeft.setVisibility(View.VISIBLE);
        mEtCommentSearch.setHint("请输入搜索内容");
        mEtCommentSearch.setEnabled(true);
        mEtCommentSearch.setFocusable(false);
        //侧滑
        mIvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//EventBus.getDefault().post(new EventbusBean(EventbusBean.ORDER_BUY_AGAIN, 1));
                EventBus.getDefault().post(new EventBusBean(EventBusBean.SHOP_MALL_HOME, 1));
            }
        });

    }

    @Override
    protected void initView() {
        //注册EventBus,在activity已经注册过
       // super.regEvent = true ;
        //刷新
        super.rlRefreshLayout = mSrlLayout;
        super.initView();
        loadData();

        mFeedArticleDataList = new ArrayList<>();
        mAdapter = new HomeListAdapter(mFeedArticleDataList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                L.d("点击了条目");
                Intent intent = new Intent(mContext, ArticleDetailActivity.class);
                intent.putExtra(IntentKV.K_ARTICLE_ID, mFeedArticleDataList.get(position).getId());
                intent.putExtra(IntentKV.K_ARTICLE_TITLE, mFeedArticleDataList.get(position).getTitle());
                intent.putExtra(IntentKV.K_ARTICLE_LINK, mFeedArticleDataList.get(position).getLink());
                intent.putExtra(IntentKV.K_IS_COLLECT, mFeedArticleDataList.get(position).isCollect());
                intent.putExtra(IntentKV.K_IS_COLLECT_PAGE, false);
                intent.putExtra(IntentKV.K_IS_COMMON_SITE, false);
                // L.d("HomeFragment", "mTitle:"+mFeedArticleDataList.get(position).getTitle() + ",articleLink:"+mFeedArticleDataList.get(position).getLink());
                mContext.startActivity(intent);
            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                L.d("点击了子条目");
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        //添加头部banner
        LinearLayout mHeader = ((LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.mall_head_banner, null));
        mBanner = ((Banner) mHeader.findViewById(R.id.head_banner));
        mHeader.removeView(mBanner);
        mAdapter.addHeaderView(mBanner);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void loadData() {
        //加载banner图数据
     //   mPresenter.BannerData();
        addDisposable(ModelVcService.getRemoteData(false ,mView, new ModelVcService.MethodSelect<List<BannerData>>() {
            @Override
            public Observable<BaseObj<List<BannerData>>> selectM(ApiService service) {
                return service.getBannerData();
            }
        }, new INetCallback<List<BannerData>>() {
            @Override
            public void onSuccess(List<BannerData> result) {
                showBannerData(result);
            }
        }));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBanner != null) {
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
    }

    @Override
    public void showArticleList(@NonNull FeedArticleListData feedArticleListData) {
        if (feedArticleListData == null) {
            return;
        }

        L.d("显示数据", "showArticleList" + feedArticleListData.getDatas().size());
        if (isRefresh) {
            mFeedArticleDataList = feedArticleListData.getDatas();
            mAdapter.replaceData(mFeedArticleDataList);
        } else {
            mFeedArticleDataList.addAll(feedArticleListData.getDatas());
            mAdapter.addData(mFeedArticleDataList);
        }
    }

    @Override
    public void showBannerData(List<BannerData> bannerDataList) {
        if (bannerDataList == null || bannerDataList.size() == 0) {
            return;
        }
        L.d("显示数据", "showBannerData" + bannerDataList.size());
        mBannerTitleList = new ArrayList<>();
        mBannerImageList = new ArrayList<>();
        mBannerUrlList = new ArrayList<>();
        for (BannerData bannerData : bannerDataList) {
            mBannerTitleList.add(bannerData.getTitle());
            mBannerImageList.add(bannerData.getImagePath());
            mBannerUrlList.add(bannerData.getUrl());
        }
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(mBannerImageList);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(mBannerTitleList);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(bannerDataList.size() * 400);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置点击事件
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                L.d("点击了轮播图" + (position + 1));
            }

        });
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }

    @Override
    public void loadListData(SmartRefreshLayout rlRefreshLayout, int page, int pageSize) {
   //     mPresenter.FeedArticleList(rlRefreshLayout, page);
       // mPresenter.onRefresh();
        addDisposable(ModelVcService.getRemoteListData(mView, rlRefreshLayout, new ModelVcService.MethodSelect<FeedArticleListData>() {
            @Override
            public Observable<BaseObj<FeedArticleListData>> selectM(ApiService service) {
                return service.getFeedArticleList(page);
            }
        }, new INetCallback<FeedArticleListData>() {
            @Override
            public void onSuccess(FeedArticleListData result) {
                showArticleList(result);
            }
        }));
    }
}
