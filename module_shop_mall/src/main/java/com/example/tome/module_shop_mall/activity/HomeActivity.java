package com.example.tome.module_shop_mall.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tome.component_base.base.AbstractPresenter;
import com.example.tome.component_base.base.BaseMVPActivity;
import com.example.tome.component_base.util.L;
import com.example.tome.component_data.d_arouter.RouterURLS;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.adapter.ViewPagerAdapter;
import com.example.tome.module_shop_mall.fagment.BaseFragment;
import com.example.tome.module_shop_mall.helper.BottomNavigationViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Created by TOME .
 * @时间 2018/5/17 18:20
 * @描述 ${主页的tab}
 */

@Route(path = RouterURLS.SHOP_MALL_HOME)
public class HomeActivity extends BaseMVPActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @BindView(R2.id.view_pager)
    ViewPager mViewPager;
    @BindView(R2.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;
    @BindView(R2.id.nav_view)
    NavigationView mNavView;
    @BindView(R2.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R2.id.common_toolbar_title)
    TextView mToolbarTitle;
    @BindView(R2.id.common_toolbar)
    Toolbar mToolbar;

    private static String TAG = "HomeActivity";
    private MenuItem menuItem;
    private MenuItem mItemWelfare;
    private MenuItem mItemVideo;
    private MenuItem mItemAboutUs;
    private MenuItem mItemLogout;
    private TextView mMUsTv;
    private MenuItem mItemLogin;


    @Override
    protected AbstractPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mall_activity_home;
    }

    @Override
    protected void initEventAndData() {
        initToolbar();
        initData();
        initNavigationView();
        initBottomNavigationView();
        initViewPager();

    }


    private void initToolbar() {
        //取代原本的actionbar
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        mToolbarTitle.setText("首页");

        //因为setSupportActionBar里面也会setNavigationOnClickListener
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initData() {


    }

    private void initNavigationView() {
        //头部布局  登录
        mMUsTv =(TextView) mNavView.getHeaderView(0).findViewById(R.id.nav_header_login_tv);
       // View drawview  = mNavView.inflateHeaderView(R.layout.mall_nav_header_home);
       // TextView navTitle = (TextView)drawview.findViewById(R.id.nav_header_login_tv);
       // mItemLogin = mNavView.getMenu().getItem(R.id.nav_header_login_tv);
        //福利
        mItemWelfare = mNavView.getMenu().findItem(R.id.nav_item_welfare);
        //视频
        mItemVideo = mNavView.getMenu().findItem(R.id.nav_item_video);
        //关于我们
        mItemAboutUs = mNavView.getMenu().findItem(R.id.nav_item_about_us);
        //退出登录
        mItemLogout = mNavView.getMenu().findItem(R.id.nav_item_logout);

        //通过actionbardrawertoggle将toolbar与drawablelayout关联起来
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this ,mDrawerLayout, mToolbar, R.string.mall_drawer_open, R.string.mall_drawer_close);

        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //设置图片为本身的颜色
        mNavView.setItemIconTintList(null);
        //设置item的点击事件
        mNavView.setNavigationItemSelectedListener(this);
        mMUsTv.setOnClickListener(this);




    }

    private void initBottomNavigationView() {
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.tab_home) {
                    L.d(TAG, "点击了首页");
                    mViewPager.setCurrentItem(0);
                } else if (item.getItemId() == R.id.tab_goods) {
                    L.d(TAG, "点击了商品");
                    mViewPager.setCurrentItem(1);
                } else if (item.getItemId() == R.id.tab_cart) {
                    L.d(TAG, "点击了购物车");
                    mViewPager.setCurrentItem(2);
                } else if (item.getItemId() == R.id.tab_self) {
                    L.d(TAG, "点击了个人页面");
                    mViewPager.setCurrentItem(3);
                }
                return true;
            }
        });

    }

    private void initViewPager() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    mBottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = mBottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(mViewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(BaseFragment.newInstance("首页"));
        adapter.addFragment(BaseFragment.newInstance("商品"));
        adapter.addFragment(BaseFragment.newInstance("购物车"));
        adapter.addFragment(BaseFragment.newInstance("我的"));
        viewPager.setAdapter(adapter);
    }


    //设置item的点击事件
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       if (item == mItemWelfare){
            L.d(TAG, "点击了福利");
        }else if (item == mItemVideo){
            L.d(TAG, "点击了视频");
        }else if (item == mItemAboutUs){
            L.d(TAG, "点击了关于我们");
        }else if (item == mItemLogout){
            L.d(TAG, "点击了退出");
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.nav_header_login_tv){
            L.d(TAG, "点击了登录");
        }
    }
}

