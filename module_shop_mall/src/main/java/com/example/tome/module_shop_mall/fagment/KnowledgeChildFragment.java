package com.example.tome.module_shop_mall.fagment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tome.projectCore.base.mvc.BaseVcListFragment;
import com.example.tome.core.net.common_callback.INetCallback;
import com.example.tome.core.util.L;
import com.example.tome.projectCore.bean.BaseObj;
import com.fec.core.router.arouter.IntentKV;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.activity.ArticleDetailActivity;
import com.example.tome.module_shop_mall.adapter.KnowledgeChildListAdapter;
import com.example.tome.module_shop_mall.api.ApiService;
import com.example.tome.module_shop_mall.api.ModelVcService;
import com.example.tome.module_shop_mall.bean.KnowledgeChildBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Created by TOME .
 * @时间 2018/6/19 15:35
 * @描述 ${知识体系子页面的item}
 */

//public class KnowledgeChildFragment extends BaseVpListFragment<KnowledgeChildPresenter> implements KnowledgeChildContract.View {
public class KnowledgeChildFragment extends BaseVcListFragment {

    @BindView(R2.id.rv_knowledge)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private int mKnowledgeId;
    private int mCurrentPage;
    List<KnowledgeChildBean.DatasBean> mKnowledgeChildList ;
    KnowledgeChildListAdapter mAdapter ;


//    @Override
//    public KnowledgeChildPresenter createPresenter() {
//        return new KnowledgeChildPresenter();
//    }

    @Override
    protected int getLayout() {
        return R.layout.mall_knowledge_child_fragment;
    }

    @Override
    protected void initTitle() {
        if (getArguments() != null) {
            mKnowledgeId = getArguments().getInt(IntentKV.K_KNOWLEDGE_ID, 0);
            Log.d("TAG", "KnowledgeChildFragment: "+ mKnowledgeId);
        }


    }

    @Override
    protected void initView() {
        super.rlRefreshLayout = mRefreshLayout ;
        super.initView();
        mKnowledgeChildList = new ArrayList<>();
        mAdapter = new KnowledgeChildListAdapter(mKnowledgeChildList);
        //item的点击事件
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转到web详情页
                Intent intent = new Intent(mContext, ArticleDetailActivity.class);
                intent.putExtra(IntentKV.K_ARTICLE_LINK, mKnowledgeChildList.get(position).getLink());
                intent.putExtra(IntentKV.K_ARTICLE_TITLE, mKnowledgeChildList.get(position).getTitle());

                mContext.startActivity(intent);
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);

    }
    @Override
    public void loadListData(SmartRefreshLayout rlRefreshLayout, int page, int pageSize) {
        mCurrentPage = 0;
        loadData(mKnowledgeId);
        // mPresenter.getKnowledgeChild(page, mKnowledgeId, rlRefreshLayout);

    }

    protected void loadData(int currentPage) {
        addDisposable(ModelVcService.getRemoteListData(mView, mRefreshLayout, new ModelVcService.MethodSelect<KnowledgeChildBean>() {
            @Override
            public Observable<BaseObj<KnowledgeChildBean>> selectM(ApiService service) {
                return service.getKnowledgeHierarchyDetailData(page, mKnowledgeId);
            }
        }, new INetCallback<KnowledgeChildBean>() {
            @Override
            public void onSuccess(KnowledgeChildBean result) {
                L.d("请求成功:"+result.getTotal());
                showKnowledgeChild(result);
            }
        }));
    }

    public void showKnowledgeChild(KnowledgeChildBean result) {

        if (isRefresh) {
            mKnowledgeChildList = result.getDatas();
            mAdapter.replaceData(mKnowledgeChildList);
        } else {
            mKnowledgeChildList.addAll(result.getDatas());
            mAdapter.addData(mKnowledgeChildList);
        }
        //L.d("数据条数:"+mKnowledgeChildList.size());

    }


    /**
     * 显示正在加载中
     */
    @Override
    public void showLoading() {
        super.showLoading();
        if (mEmptyView != null){
            mEmptyView.showLoading();
            L.d("空布局加载中");
        }
    }

    @Override
    public void showError() {
        if (mEmptyView != null){
            mEmptyView.showError(R.mipmap.icon_error,"出错了",null,"重新加载",new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    L.d("点击了重新刷新");
                    mCurrentPage = 0;
                    loadData(mKnowledgeId);
                }
            });
        }else{
            L.d("空布局");
        }

    }
}
