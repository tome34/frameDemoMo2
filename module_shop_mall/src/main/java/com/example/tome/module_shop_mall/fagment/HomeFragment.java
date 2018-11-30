package com.example.tome.module_shop_mall.fagment;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.example.tome.projectCore.base.mvp.BaseVpListFragment;
import com.example.tome.core.util.L;
import com.example.tome.projectCore.bean.EventBusBean;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.contract.HomeContract;
import com.example.tome.module_shop_mall.presenter.HomePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import org.greenrobot.eventbus.EventBus;

/**
 * @Created by TOME .
 * @时间 2018/5/30 16:51
 * @描述 ${首页}
 */

public class HomeFragment extends BaseVpListFragment<HomeContract.View, HomeContract.Presenter> implements HomeContract.View, View.OnClickListener {
//public class HomeFragment extends BaseVcListFragment implements HomeContract.View{

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

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(getActivity(),mRecyclerView);
    }

    @Override
    public HomeContract.View createView() {
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.mall_fragment_home;
    }

    @Override
    protected void initTitle() {
        L.d("状态栏"+"homeFragment");
       // mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.white).statusBarDarkFont(true, 0.1f).init();
        mIvLeft.setVisibility(View.VISIBLE);
        mEtCommentSearch.setHint("请输入搜索内容");
        mEtCommentSearch.setEnabled(true);
        mEtCommentSearch.setFocusable(false);
        mEtCommentSearch.setOnClickListener(this);
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
    }



    public void loadData() {
        //加载banner图数据
        mPresenter.BannerData();
//        addDisposable(ModelVcService.getRemoteData(false ,mView, new ModelVcService.MethodSelect<List<BannerData>>() {
//            @Override
//            public Observable<BaseObj<List<BannerData>>> selectM(ApiService service) {
//                return service.getBannerData();
//            }
//        }, new INetCallback<List<BannerData>>() {
//            @Override
//            public void onSuccess(List<BannerData> result) {
//                showBannerData(result);
//            }
//        }));
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.startBannerPlay();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null){
            mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.white)
                         .statusBarDarkFont(true, 0.1f).init();

        }else if (hidden && mImmersionBar != null){
            mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.colorPrimary).init();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.stopBannerPlay();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }

    @Override
    public void loadListData(boolean isRefresh, SmartRefreshLayout rlRefreshLayout, int page, int pageSize) {
       L.d("数据page:"+page +","+isRefresh);
        mPresenter.FeedArticleList(isRefresh, rlRefreshLayout, page);
    //    mPresenter.onRefresh();
//        addDisposable(ModelVcService.getRemoteListData(mView, rlRefreshLayout, new ModelVcService.MethodSelect<FeedArticleListData>() {
//            @Override
//            public Observable<BaseObj<FeedArticleListData>> selectM(ApiService service) {
//                return service.getFeedArticleList(page);
//            }
//        }, new INetCallback<FeedArticleListData>() {
//            @Override
//            public void onSuccess(FeedArticleListData result) {
//                showArticleList(result);
//            }
//        }));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.et_comment_search){
            L.d("点击了搜索");
            SearchDialogFragment dialogFragment = new SearchDialogFragment();
            dialogFragment.show(getFragmentManager(), "SearchDialogFragment");
        }
    }
}
