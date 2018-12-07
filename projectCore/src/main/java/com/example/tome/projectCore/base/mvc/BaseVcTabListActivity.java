package com.example.tome.projectCore.base.mvc;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.example.tome.core.R;
import com.example.tome.core.R2;
import com.example.tome.core.base.mvc.BaseVcActivity;
import com.example.tome.projectCore.bean.TabListBean;
import java.util.List;

/**
 * @Created by TOME .
 * @时间 2018/6/5 16:06
 * @描述 ${Tab列表Activity}
 */

public abstract class BaseVcTabListActivity extends BaseVcActivity {


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
    protected int getLayoutId() {
        titleList = tabTitles();
        return R.layout.fragment_tab_list;
       // return getTabLayoutId();
    }

    @Override
    protected void initTitle() {
        mLayoutFragTabList.setBackgroundColor(getResources().getColor(R.color.windowBg));
        if (titleList == null || titleList.size() == 0){
            return;
        }
        int size = titleList.size();
        for (int i = 0; i < size; i++) {
            mTlTabs.addTab(mTlTabs.newTab().setText(titleList.get(i).getTitle()));
        }
        mVpView.setOffscreenPageLimit(size);
        mTitleContentText.setText(setTitle());
        mTitleBack.setVisibility(View.GONE);
    }

    @Override
    protected void initView() {
        mVpView.setAdapter(tabAdapter = new TabAdapter(getSupportFragmentManager()));
        mTlTabs.setTabMode(isTabScrollable() ? TabLayout.MODE_SCROLLABLE : TabLayout.MODE_FIXED);
        mTlTabs.setupWithViewPager(mVpView);
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

    protected boolean isTabScrollable() {
        return false;
    }

  //  protected abstract int getTabLayoutId();
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
}
