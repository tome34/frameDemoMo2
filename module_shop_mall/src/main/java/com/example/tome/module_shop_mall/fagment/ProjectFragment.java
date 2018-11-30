package com.example.tome.module_shop_mall.fagment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.example.tome.core.adapter.BaseFragmentStateAdapter;
import com.example.tome.core.base.mvp.BaseVpFragment;
import com.example.tome.core.util.L;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.bean.ProjectClassifyBean;
import com.example.tome.module_shop_mall.contract.IProjectContract;
import com.example.tome.module_shop_mall.presenter.ProjectPresenter;
import com.flyco.tablayout.SlidingTabLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 *
 * @author Tome
 * @date 2018/7/18  18:20
 * - generate by MvpAutoCodePlus plugin.
 */

public class ProjectFragment extends BaseVpFragment<IProjectContract.View,IProjectContract.Presenter> implements IProjectContract.View {

    @BindView(R2.id.title_back)
    TextView mTitleBack;
    @BindView(R2.id.title_content_text)
    TextView mTitleContentText;
    @BindView(R2.id.title_right_text)
    TextView mTitleRightText;
    @BindView(R2.id.title_right_icon)
    ImageView mTitleRightIcon;
    @BindView(R2.id.v_title_container)
    LinearLayout mVTitleContainer;
    @BindView(R2.id.rl_title_bar_content)
    RelativeLayout mRlTitleBarContent;
    @BindView(R2.id.project_tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R2.id.project_viewpager)
    ViewPager mViewpager;
    @BindView(R2.id.normal_view)
    LinearLayout mNormalView;

    private List<ProjectClassifyBean> mData;
    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    private int currentPage;

    @Override
    public IProjectContract.Presenter createPresenter() {
        return new ProjectPresenter();
    }

    @Override
    public IProjectContract.View createView() {
        return this;
    }


    @Override
    protected int getLayout() {
        return R.layout.mall_fragment_project;
    }

    @Override
    protected void initTitle() {
        mTitleContentText.setText("项目");
    }

    @Override
    protected void initView() {
        mPresenter.getProjectClassifyData();
    }

    @Override
    public void showProjectClassifyData(List<ProjectClassifyBean> projectClassifyDataList) {
        mData = projectClassifyDataList;
        L.d("哈哈哈:"+ mData.size(),mData.get(1).getName());
        for (ProjectClassifyBean data : mData) {
            ProjectListFragment projectListFragment = ProjectListFragment.getInstance(data.getId(), null);
            mFragments.add(projectListFragment);
            mTitleList.add(data.getName());
        }

        //设置adapter
        mViewpager.setAdapter(new BaseFragmentStateAdapter(getChildFragmentManager(), mFragments, mTitleList));
        //监听滑动
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position,float positionOffset,int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position ;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTabLayout.setViewPager(mViewpager);
        mViewpager.setCurrentItem(0);
    }
}

