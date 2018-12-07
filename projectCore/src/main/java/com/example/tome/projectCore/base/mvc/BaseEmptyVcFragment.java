package com.example.tome.projectCore.base.mvc;

import android.view.View;
import android.view.ViewGroup;
import com.example.tome.core.R;
import com.example.tome.core.base.mvc.BaseVcFragment;
import com.example.tome.core.util.L;
import com.example.tome.projectCore.widget.emptyViews.EmptyView;

/**
 * @author tome
 * @date 2018/7/9  16:30
 * @describe ${处理空页面显示}
 */
public abstract class BaseEmptyVcFragment extends BaseVcFragment {
    private ViewGroup mNormalView;
    private ViewGroup mParent;
    public EmptyView mEmptyView;
    public View mEmptyGroup;

//用getview获取不到
    @Override
    protected void init(View view) {
        if (view == null) {
            return;
        }
       // mNormalView = (ViewGroup) view.findViewById(R.id.refresh_layout);
        mNormalView = getViewGroup(view);
        if (mNormalView == null) {
            throw new IllegalStateException(
                "The subclass of RootActivity must contain a View named 'mNormalView'.");
        }
        if (!(mNormalView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                "mNormalView's ParentView should be a ViewGroup.");
        }
        //mParent = (ViewGroup) mNormalView.getParent(); 获取不到view
        mParent = (ViewGroup) mNormalView.getRootView();
        View.inflate(mContext, R.layout.base_empty_view_fragment, mParent);
        mEmptyGroup = mParent.findViewById(R.id.empty_group);
        mEmptyView = (EmptyView)mEmptyGroup.findViewById(R.id.empty_view);
        mEmptyGroup.setVisibility(View.GONE);
        L.d("空布局++"+mEmptyView);
    }

    @Override
    public void showNormal() {
        mNormalView.setVisibility(View.VISIBLE);
        mEmptyView.hide();
        mEmptyGroup.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        mNormalView.setVisibility(View.GONE);
        mEmptyView.show();
        mEmptyGroup.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyView() {
        mNormalView.setVisibility(View.GONE);
        mEmptyView.hide();
        mEmptyGroup.setVisibility(View.VISIBLE);
        mEmptyView.showEnpty(R.mipmap.icon_empty, "这里什么都没有哦", null );

    }

    @Override
    public void showError() {
        mNormalView.setVisibility(View.GONE);
        mEmptyView.hide();
        mEmptyGroup.setVisibility(View.VISIBLE);
        mEmptyView.showError(R.mipmap.error404,"出错了",null,"重新加载", null);
    }

    //由子类实现顶层布局id
    public abstract ViewGroup getViewGroup(View view);
}
