package com.example.tome.module_shop_mall.fagment;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.example.tome.projectCore.base.mvp.BaseEmptyVpFragment;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.adapter.NavigationAdapter;
import com.example.tome.module_shop_mall.bean.NavigationBean;
import com.example.tome.module_shop_mall.contract.INavigationV2Contract;
import com.example.tome.module_shop_mall.presenter.NavigationV2Presenter;
import java.util.List;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * Description :
 *
 * @author Tome
 * @date 2018/7/18  15:02
 * - generate by MvpAutoCodePlus plugin.
 */

public class NavigationV2Fragment extends BaseEmptyVpFragment<INavigationV2Contract.View,INavigationV2Contract.Presenter> implements INavigationV2Contract.View {

    @BindView(R2.id.navigation_tab_layout)
    VerticalTabLayout mTabLayout;
    @BindView(R2.id.navigation_RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R2.id.normal_view)
    LinearLayout mNormalView;
    @BindView(R2.id.title_content_text)
    TextView mTitleText;

    private LinearLayoutManager mManager;
    private boolean needScroll;
    private int index;
    private boolean isClickTab;

    @Override
    public INavigationV2Contract.Presenter createPresenter() {
        return new NavigationV2Presenter();
    }

    @Override
    public INavigationV2Contract.View createView() {
        return this;
    }

    @Override
    public ViewGroup getViewGroup(View view) {
        return (ViewGroup) view.findViewById(R.id.normal_view);
    }

    @Override
    protected int getLayout() {
        return R.layout.mall_fragment_navigation_v2;
    }

    @Override
    protected void initTitle() {
        mTitleText.setText("导航");
    }

    @Override
    protected void initView() {
        mPresenter.getNavigationData();
    }

    @Override
    public void showNavigation(List<NavigationBean> dataList) {
        mTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return dataList == null ? 0 : dataList.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new ITabView.TabTitle.Builder()
                    .setContent(dataList.get(position).getName())
                    .setTextColor(ContextCompat.getColor(mContext, R.color.yellow),
                        ContextCompat.getColor(mContext, R.color.color66))
                    .build();
            }

            @Override
            public int getBackground(int position) {
                return -1;
            }
        });

        initRecyclerView(dataList);
        leftRightLinkage();

    }

    private void initRecyclerView(List<NavigationBean> dataList) {
        NavigationAdapter adapter = new NavigationAdapter(R.layout.mall_item_navigation, dataList);
        mManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
    }

    /**
     * 设置左边和右边的联动
     */
    private void leftRightLinkage() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,int newState) {
                super.onScrollStateChanged(recyclerView,newState);
                //滚动状态变化时回调
                if (needScroll && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                    scrollRecyclerView();
                }
                rightLinkageLeft(newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView,int dx,int dy) {
                super.onScrolled(recyclerView,dx,dy);
                //滚动时回调
                if (needScroll) {
                    scrollRecyclerView();
                }
            }
        });

        mTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab,int position) {
                isClickTab = true;
                selectTag(position);
            }

            @Override
            public void onTabReselected(TabView tab,int position) {

            }
        });
    }

    private void selectTag(int i) {
        index = i;
        mRecyclerView.stopScroll();
        smoothScrollToPosition(i);
    }

    /**
     * recyclerView 的滚动
     */
    private void scrollRecyclerView() {
        needScroll = false;
        int indexDistance = index - mManager.findFirstVisibleItemPosition();
        if (indexDistance >= 0 && indexDistance < mRecyclerView.getChildCount()) {
            int top = mRecyclerView.getChildAt(indexDistance).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        }
    }

    /**
     * 右边联动左边
     * @param newState
     */
    private void rightLinkageLeft(int newState) {
        //recyclerview 的newState有三种值,SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING ,SCROLL_STATE_SETTLING
        //正在滚动
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (isClickTab) {
                isClickTab = false;
                return;
            }
            int firstPosition = mManager.findFirstVisibleItemPosition();
            if (index != firstPosition) {
                index = firstPosition;
                setChecked(index);
            }
        }
    }

    /**
     * 右边选择左边的位置
     * @param position
     */
    private void setChecked(int position) {
        if (isClickTab) {
            isClickTab = false;
        } else {
            if (mTabLayout == null) {
                return;
            }
            mTabLayout.setTabSelected(index);
        }
        index = position;
    }

    private void smoothScrollToPosition(int currentPosition) {
        int firstPosition = mManager.findFirstVisibleItemPosition();
        int lastPosition = mManager.findLastVisibleItemPosition();
        if (currentPosition <= firstPosition) {
            //RecyclerView滑动到指定Position
            mRecyclerView.smoothScrollToPosition(currentPosition);
        } else if (currentPosition <= lastPosition) {
            int top = mRecyclerView.getChildAt(currentPosition - firstPosition).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        } else {
            mRecyclerView.smoothScrollToPosition(currentPosition);
            needScroll = true;
        }
    }


}

