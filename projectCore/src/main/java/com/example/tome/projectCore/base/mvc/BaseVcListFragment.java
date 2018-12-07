package com.example.tome.projectCore.base.mvc;

import android.view.View;
import android.view.ViewGroup;
import com.example.tome.core.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * @Created by TOME .
 * @时间 2018/6/4 17:19
 * @描述 ${列表基类,封装刷新和加载更多}
 */

public abstract class BaseVcListFragment extends BaseEmptyVcFragment implements OnRefreshListener, OnLoadMoreListener {

    protected int page = 0;
    protected int pageSize = 20;
    protected boolean isRefresh = true;
    protected SmartRefreshLayout rlRefreshLayout;

    @Override
    protected void initView() {
        if (rlRefreshLayout != null) {
            rlRefreshLayout.setOnRefreshListener(this);
            rlRefreshLayout.setOnLoadMoreListener(this);
            rlRefreshLayout.autoRefresh();
        }
    }

    /**
     *  空布局
     * @param view
     * @return
     */
    @Override
    public ViewGroup getViewGroup(View view) {
        return (ViewGroup) view.findViewById(R.id.refresh_layout);
    }

    /**
     * 刷新
     * @param refreshLayout
     */
    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        this.page = 0;
        isRefresh = true ;
        loadListData(rlRefreshLayout ,page, pageSize);
    }

    /**
     * 加载更多
     * @param refreshLayout
     */
    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        page++;
        isRefresh = false ;
        loadListData(rlRefreshLayout ,page, pageSize);
    }

    public abstract void loadListData(SmartRefreshLayout rlRefreshLayout , int page, int pageSize);

}
