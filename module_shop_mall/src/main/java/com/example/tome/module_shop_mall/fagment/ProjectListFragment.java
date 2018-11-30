package com.example.tome.module_shop_mall.fagment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.fec.core.router.arouter.IntentKV;
import com.example.tome.projectCore.base.mvc.BaseVcListFragment;
import com.example.tome.projectCore.bean.BaseObj;
import com.example.tome.core.net.common_callback.INetCallback;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.activity.ArticleDetailActivity;
import com.example.tome.module_shop_mall.adapter.ProjectListAdapter;
import com.example.tome.module_shop_mall.api.ApiService;
import com.example.tome.module_shop_mall.api.ModelVcService;
import com.example.tome.module_shop_mall.bean.ProjectListBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tome
 * @date 2018/7/20  18:35
 * @describe ${TODO}
 */
public class ProjectListFragment extends BaseVcListFragment {

    @BindView(R2.id.project_list_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private ProjectListAdapter mAdapter;
    private int mCid;
    private List<ProjectListBean.DatasBean> mDatas;
    private int mCurrentPage;

    @Override
    public ViewGroup getViewGroup(View view) {
        return (ViewGroup)view.findViewById(R.id.refresh_layout);
    }

    @Override
    protected int getLayout() {
        return R.layout.mall_fragment_project_list;
    }

    @Override
    protected void initTitle() {
        mDatas = new ArrayList<>();
    }

    public static ProjectListFragment getInstance(int param1,String param2) {
        ProjectListFragment fragment = new ProjectListFragment();
        Bundle args = new Bundle();
        args.putInt("param1",param1);
        args.putString("param2",param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        super.rlRefreshLayout = mRefreshLayout;
        super.initView();
        Bundle bundle = getArguments();
        mCid = bundle.getInt("param1");
        initRecyclerView();


    }

    /*加载数据*/
    @Override
    public void loadListData(SmartRefreshLayout rlRefreshLayout,int page,int pageSize) {
        addDisposable(ModelVcService.getRemoteListData(mView,rlRefreshLayout,new ModelVcService.MethodSelect<ProjectListBean>() {
            @Override
            public Observable<BaseObj<ProjectListBean>> selectM(ApiService service) {
                return service.getProjectListData(page, mCid);
            }
        },new INetCallback<ProjectListBean>() {
            @Override
            public void onSuccess(ProjectListBean result) {
                mDatas = result.getDatas();
                mView.showNormal();
                showProjectListData();
            }
        }));
    }

    private void showProjectListData() {
        if (isRefresh){
            mAdapter.replaceData(mDatas);
        }else {
            if (mDatas.size()> 0){
                mAdapter.addData(mDatas);
            }else {
                mView.showEmptyView();
            }
        }
    }

    private void initRecyclerView() {
        mDatas = new ArrayList<>();
        mAdapter = new ProjectListAdapter(R.layout.mall_item_project_list, mDatas);
        mAdapter.setOnItemClickListener((adapter, view, position) -> startProjectPager(position));
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> clickChildEvent(view, position));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
    }

    private void startProjectPager(int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() <= position) {
            return;
        }

        //跳转到web详情页
        Intent intent = new Intent(mContext, ArticleDetailActivity.class);
        intent.putExtra(IntentKV.K_ARTICLE_LINK,  mAdapter.getData().get(position).getLink());
        intent.putExtra(IntentKV.K_ARTICLE_TITLE,  mAdapter.getData().get(position).getTitle());

        mContext.startActivity(intent);
    }

    private void clickChildEvent(View view, int position) {
        if (view.getId() == R.id.item_project_list_install_tv){
            startInstallPager(position);
        }
    }

    /**
     * 安装应用
     * @param position
     */
    private void startInstallPager(int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() <= position) {
            return;
        }
        if (TextUtils.isEmpty(mAdapter.getData().get(position).getApkLink())) {
            return;
        }
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mAdapter.getData().get(position).getApkLink())));
    }

}
