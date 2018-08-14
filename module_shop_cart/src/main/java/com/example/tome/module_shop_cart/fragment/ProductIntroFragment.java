package com.example.tome.module_shop_cart.fragment;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import butterknife.BindView;
import com.example.tome.module_shop_cart.R;
import com.example.tome.module_shop_cart.R2;
import com.example.tome.module_shop_cart.activity.ProductDetailActivity;
import com.example.tome.module_shop_cart.base.CommonFragment;
import com.example.tome.module_shop_cart.widget.DragLayout;

/**
 * @author tome
 * @date 2018/8/10  15:25
 * @describe ${商品详情页介绍,包含两个fragment}
 */
public class ProductIntroFragment extends CommonFragment {

    @BindView(R2.id.fl_first)
    FrameLayout mFlFirst;
    @BindView(R2.id.fl_second)
    FrameLayout mFlSecond;
    @BindView(R2.id.dl_product)
    DragLayout mDragLayout;

    private ProductDetailActivity mActivity;
    private ProductDetailFirstFrag mFirstFrag;
    private ProductDetailSecondFragment  mSecondFrag;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProductDetailActivity) {
            mActivity = (ProductDetailActivity) context;
        }
    }
    @Override
    public int setContentLayout() {
        return R.layout.cart_fragment_product_intro;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {
        mFirstFrag = new ProductDetailFirstFrag();
        mSecondFrag = new ProductDetailSecondFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.fl_first, mFirstFrag).add(R.id.fl_second, mSecondFrag).commit();
        //淡出动画
        final AlphaAnimation animationout = new AlphaAnimation(1, 0);
        animationout.setDuration(1000);
        //淡入动画
        final AlphaAnimation animationin = new AlphaAnimation(0, 1);
        animationin.setDuration(1000);
        //todo
        mDragLayout.addPageListener(mActivity);
        mDragLayout.addPageListener(mFirstFrag);

        if (mActivity != null) {
            mActivity.addOnDimensionSelectListener(mFirstFrag);
            mActivity.addOnDimensionSelectListener(mSecondFrag);
        }

    }

    //显示简介
    public void showProductIntro() {
        mDragLayout.selectLastPage();
                mDragLayout.selectLastPageSmooth();
       // mFirstFrag.scrollToTop();
    }

    //显示详情
    public void showProductDetail() {
        mDragLayout.selectNextPage();
        //        mDragLayout.selectNextPageSmooth();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mActivity != null) {
            mActivity.removeOnDimensionSelectListener(mFirstFrag);
            mActivity.removeOnDimensionSelectListener(mSecondFrag);
        }
    }
}
