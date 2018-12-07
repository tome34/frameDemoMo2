package com.example.tome.projectCore.base.mvp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tome.core.R;
import com.example.tome.core.R2;
import com.example.tome.core.base.mvp.BaseVpFragment;
import com.example.tome.core.base.mvp.inter.IPresenter;
import com.example.tome.core.base.mvp.inter.IView;
import com.example.tome.projectCore.bean.TabListBean;

import java.util.List;

import butterknife.BindView;

/**
 * @Created by TOME .
 * @时间 2018/6/5 15:27
 * @描述 ${Tab列表Activity}
 */

public abstract class BaseVpTabListFragment<V extends IView,P extends IPresenter<V>> extends BaseVpFragment<V, P> implements View.OnClickListener {

    @BindView(R2.id.title_back)
    TextView mTitleBack;
    @BindView(R2.id.title_content_text)
    TextView mTitleContentText;
    @BindView(R2.id.tl_tabs)
    TabLayout mTlTabs;
    @BindView(R2.id.vp_view)
    ViewPager mVpView;
    @BindView(R2.id.layout_frag_tab_list)
    LinearLayout mLayoutFragTabList;
    private List<TabListBean> titleList;
    protected TabAdapter tabAdapter;

    @Override
    protected int getLayout() {
        titleList = tabTitles();
        return R.layout.fragment_tab_list;
    }

    @Override
    protected void initTitle() {
        mLayoutFragTabList.setBackgroundColor(getResources().getColor(R.color.windowBg));
        mVpView.setAdapter(tabAdapter = new TabAdapter(getChildFragmentManager()));
        mTlTabs.setTabMode(isTabScrollable() ? TabLayout.MODE_SCROLLABLE : TabLayout.MODE_FIXED);
        mTlTabs.setupWithViewPager(mVpView);
        mTitleContentText.setText(setTitle());

    }

    @Override
    protected void initView() {
        mTitleBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    }



    class TabAdapter extends FragmentPagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return titleList.get(position).getFragment();
        }

        @Override
        public int getCount() {
            return titleList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position).getTitle();
        }
    }

    /**
     * tab标题集合
     *
     * @return
     */
    protected abstract List<TabListBean> tabTitles();

    /**
     * 设置标题
     * @return
     */
    protected abstract String setTitle();

    protected boolean isTabScrollable() {
        return false;
    }
}
