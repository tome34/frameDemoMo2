package com.example.tome.module_shop_mall.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tome.core.base.mvp.BaseVpActivity;
import com.example.tome.core.net.params.RequestMapParams;
import com.example.tome.core.util.L;
import com.fec.core.router.arouter.RouterURLS;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;
import com.example.tome.module_shop_mall.contract.MainContract;
import com.example.tome.module_shop_mall.presenter.MainPresenter;

@Route(path = RouterURLS.MVP_TEST2)
public class MvpNoModelTestActivity extends BaseVpActivity<MainContract.View, MainContract.Presenter> implements MainContract.View, View.OnClickListener {

    @BindView(R2.id.tv_test_mvp)
    Button mTvTestMvp;
    @BindView(R2.id.tv_data)
    TextView mTvData;
    @BindView(R2.id.tv_error)
    TextView mTvError;

    @Override
    public MainContract.Presenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public MainContract.View createView() {
        return this;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.mall_activity_mvp_test;
    }

    @Override
    protected void initTitle() {
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.colorPrimary).init();
    }

    @Override
    protected void initView() {
        mTvTestMvp.setOnClickListener(this);
    }

    @Override
    public void showTestData(FeedArticleListData feedArticleListData) {
        L.d("成功返回数据" + feedArticleListData.getCurPage());
        mTvData.setText("成功获取"+feedArticleListData.getDatas().size()+"条数据");
    }

    protected void loadData(int page, RequestMapParams params) {
        //        addDisposable(ModelVcService.getRemoteData(true, mView, new ModelVcService.MethodSelect<FeedArticleListData>() {
        //            @Override
        //            public Observable<BaseObj<FeedArticleListData>> selectM(ApiService service) {
        //                return service.getFeedArticleList(page, params.build());
        //            }
        //        }, new INetCallback<FeedArticleListData>() {
        //            @Override
        //            public void onSuccess(FeedArticleListData result) {
        //                L.d("成功返回数据" + result.getCurPage());
        //                showTestData(result);
        //
        //            }
        //        }));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_test_mvp){
            mPresenter.initFeedArticleList2();
            // loadData(0,params);
        }
    }
}
