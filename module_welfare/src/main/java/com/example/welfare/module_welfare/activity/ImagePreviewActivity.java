package com.example.welfare.module_welfare.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.tome.component_base.base.BaseMVPActivity;
import com.example.tome.component_base.base.BasePermissionActivity;
import com.example.tome.component_base.base.BasePresenter;
import com.example.tome.component_base.base.inter.AbstractPresenter;
import com.example.tome.component_base.util.L;
import com.example.tome.component_data.d_arouter.IntentKV;
import com.example.tome.component_data.d_arouter.RouterURLS;
import com.example.welfare.module_welfare.R;
import com.example.welfare.module_welfare.R2;
import com.example.welfare.module_welfare.adapter.ImagePreviewAdapter;
import com.example.welfare.module_welfare.bean.PreviewBean;
import com.example.welfare.module_welfare.contract.SaveImageContract;
import com.example.welfare.module_welfare.presenter.SaveImagePresenter;
import com.example.welfare.module_welfare.widget.HackyViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = RouterURLS.WELFARE_PREVIEW)
public class ImagePreviewActivity extends BasePermissionActivity<SaveImagePresenter> implements SaveImageContract.View, View.OnClickListener {

    @BindView(R2.id.view_pager)
    HackyViewPager mViewPager;
    @BindView(R2.id.tv_save)
    TextView mTvSave;
    @BindView(R2.id.tv_pager)
    TextView mTvPager;
    @BindView(R2.id.layout_preview)
    RelativeLayout mLayoutPreview;

    @Autowired
    public ArrayList<PreviewBean> mPreviewBeans ;
    @Autowired
    public int posit ;
    public int cuccentPosit ;


    @Override
    protected SaveImagePresenter getPresenter() {
        return new SaveImagePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.welfare_image_preview_activity;
    }

    @Override
    protected void initTitle() {
        mPreviewBeans = getIntent().getParcelableArrayListExtra(IntentKV.K_WELFARE_PHOTO);
        posit = getIntent().getIntExtra(IntentKV.K_WELFARE_POSITION, 0);
        mTvSave.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        ImagePreviewAdapter pagerAdapter = new ImagePreviewAdapter(this, mPreviewBeans);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(posit);

        int size = mPreviewBeans.size();
        mTvPager.setText((posit+1) +"/"+size);
        cuccentPosit = posit + 1 ;
        //滑动监听器OnPageChangeListener
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // position :当前页面，及你点击滑动的页面；arg1:当前页面偏移的百分比；arg2:当前页面偏移的像素位置

            }

            @Override
            public void onPageSelected(int position) {
                // position是当前选中的页面的Position
                L.d("当前页面:"+position);
                cuccentPosit = position + 1 ;
                mTvPager.setText((position+1) +"/"+size);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //state ==1的时表示正在滑动，state==2的时表示滑动完毕了，state==0的时表示什么都没做。
            }
        });

    }

    /**
     * 保存图片到相册
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (! getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, PERMISSION_STORAGE)){
            return;

            
        }


    }

    @Override
    public void showSaveSuccess() {

    }
}
