package com.example.tome.module_shop_mall.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tome.core.base.BaseObserver;
import com.example.tome.core.base.mvp.BasePresenter;
import com.example.tome.core.util.L;
import com.example.tome.projectCore.bean.BaseObj;
import com.fec.core.router.arouter.IntentKV;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.activity.ArticleDetailActivity;
import com.example.tome.module_shop_mall.adapter.HomeListAdapter;
import com.example.tome.module_shop_mall.bean.BannerData;
import com.example.tome.module_shop_mall.bean.FeedArticleData;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;
import com.example.tome.module_shop_mall.contract.HomeContract;
import com.example.tome.module_shop_mall.helper.GlideImageLoader;
import com.example.tome.module_shop_mall.model.HomeModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

/**
 * @Created by TOME .
 * @时间 2018/5/31 14:01
 * @描述 ${TODO}
 */

public class HomePresenter extends BasePresenter<HomeContract.View, HomeContract.Model> implements HomeContract.Presenter {

    private List<FeedArticleData> mFeedArticleDataList;
    private FeedArticleListData mFeedArticleListData;
    private HomeListAdapter mAdapter;
    private Banner mBanner;
    private Context mActivity;
    private List<String> mBannerImageList;
    private List<String> mBannerTitleList;
    private List<String> mBannerUrlList;
    private RecyclerView mRecyclerView;
    private boolean mIsRefresh;


    //接收有参构造函数
    public HomePresenter(String type) {
    }

    public HomePresenter(Context activity, RecyclerView recyclerView) {
        this.mActivity = activity;
        mRecyclerView = recyclerView;
        //初始化recyclerView
        initRecyclerView(mFeedArticleListData);

    }

    @Override
    public void attachView(HomeContract.View view) {
        super.attachView(view);

    }


    @Override
    protected HomeContract.Model createModel() {
        return new HomeModel(this);
    }


    @Override
    public void startBannerPlay() {
        if (mBanner != null) {
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void stopBannerPlay() {
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
    }

    @Override
    public void BannerData() {
        addDisposable(
                mModel.getBannerData()
                        //  .subscribeOn(Schedulers.io()) //指定网络请求在IO线程
                        //  .retryWhen(new RetryWithDelay)////遇到错误时重试
                        .doOnSubscribe(disposable -> {
                            // mView.showHUD("");//显示进度条
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())//显示进度条在主线程
                        // .observeOn(AndroidSchedulers.mainThread())     //显示数据在主线程
                        .doFinally(() -> {
                            //  mView.dismissHUD();//隐藏进度条

                        })
                        // .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                        .subscribeWith(new BaseObserver<BaseObj<List<BannerData>>>() {
                            @Override
                            public void onNext(BaseObj<List<BannerData>> listBaseObj) {
                                List<BannerData> data = listBaseObj.getData();
                                // mView.showBannerData(data);
                                initBannerData(data);
                            }
                        }));


    }

    private void initBannerData(List<BannerData> bannerDataList) {
        if (bannerDataList == null || bannerDataList.size() == 0) {
            return;
        }
        mBannerTitleList = new ArrayList<>();
        mBannerImageList = new ArrayList<>();
        mBannerUrlList = new ArrayList<>();
        L.d("显示数据", "showBannerData" + bannerDataList.size());

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
        mBanner.setDelayTime(10 * 200);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置点击事件
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                L.d("点击了轮播图" + (position + 1));
            }

        });

        //更新图片集和标题
        // mBanner.update(mBannerImageList, mBannerTitleList);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    public void FeedArticleList(boolean isRefresh, SmartRefreshLayout rlRefreshLayout, int page) {
        mIsRefresh = isRefresh;
        addDisposable(
                mModel.getFeedArticleList(page)
                        .subscribeOn(Schedulers.io()) //指定网络请求在IO线程
                        //  .retryWhen(new RetryWithDelay)////遇到错误时重试
                        .doOnSubscribe(disposable -> {
                            if (mIsRefresh) {
                                // mView.showLoading();//显示下拉刷新的进度条
                                // mView
                            } else {
                                // mView.startLoadMore();//显示上拉加载更多的进度条
                            }
                        }).subscribeOn(AndroidSchedulers.mainThread())//显示进度条在主线程
                        .observeOn(AndroidSchedulers.mainThread())     //显示数据在主线程
                        .doFinally(() -> {
                            if (mIsRefresh) {
                                // mView.hideLoading();//隐藏下拉刷新的进度条
                            } else {
                                // mView.endLoadMore();//隐藏上拉加载更多的进度条
                            }
                        })
                        // .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                        .subscribeWith(new BaseObserver<BaseObj<FeedArticleListData>>(mView,rlRefreshLayout) {
                            @Override
                            public void onNext(BaseObj<FeedArticleListData> listBaseObj) {
                                FeedArticleListData data = listBaseObj.getData();
                                showArticleList(data);

                            }
                        }));

    }

    private void showArticleList(FeedArticleListData feedArticleListData) {
        if (feedArticleListData == null) {
            return;
        }
        L.d("显示数据", mIsRefresh + ",showArticleList" + feedArticleListData.getDatas().get(0).getTitle());
        if (mIsRefresh) {
            mFeedArticleDataList = feedArticleListData.getDatas();
            mAdapter.replaceData(mFeedArticleDataList);
        } else {
            mFeedArticleDataList.addAll(feedArticleListData.getDatas());
            mAdapter.addData(feedArticleListData.getDatas());
        }
    }

    private void initRecyclerView(FeedArticleListData feedArticleListData) {
        mFeedArticleDataList = new ArrayList<>();
        mAdapter = new HomeListAdapter(mFeedArticleDataList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                L.d("点击了条目");
                Intent intent = new Intent(mActivity, ArticleDetailActivity.class);
                intent.putExtra(IntentKV.K_ARTICLE_ID, mFeedArticleDataList.get(position).getId());
                intent.putExtra(IntentKV.K_ARTICLE_TITLE, mFeedArticleDataList.get(position).getTitle());
                intent.putExtra(IntentKV.K_ARTICLE_LINK, mFeedArticleDataList.get(position).getLink());
                intent.putExtra(IntentKV.K_IS_COLLECT, mFeedArticleDataList.get(position).isCollect());
                intent.putExtra(IntentKV.K_IS_COLLECT_PAGE, false);
                intent.putExtra(IntentKV.K_IS_COMMON_SITE, false);
                // L.d("HomeFragment", "mTitle:"+mFeedArticleDataList.get(position).getTitle() + ",articleLink:"+mFeedArticleDataList.get(position).getLink());
                mActivity.startActivity(intent);
            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                L.d("点击了子条目");
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        //添加头部banner
        LinearLayout mHeader = ((LinearLayout) LayoutInflater.from(mActivity).inflate(R.layout.mall_head_banner, null));
        mBanner = ((Banner) mHeader.findViewById(R.id.head_banner));
        mHeader.removeView(mBanner);
        mAdapter.addHeaderView(mBanner);
        mRecyclerView.setAdapter(mAdapter);
    }


}
