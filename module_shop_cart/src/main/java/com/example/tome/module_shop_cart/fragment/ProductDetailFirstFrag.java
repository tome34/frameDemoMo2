package com.example.tome.module_shop_cart.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.example.tome.module_shop_cart.R;
import com.example.tome.module_shop_cart.R2;
import com.example.tome.module_shop_cart.base.CommonFragment;
import com.example.tome.module_shop_cart.bean.GetProductDetailsBean;
import com.example.tome.module_shop_cart.listener.OnDimensionSelectListener;
import com.example.tome.module_shop_cart.widget.AutoLoopView;
import com.example.tome.module_shop_cart.widget.CustScrollView;
import com.example.tome.module_shop_cart.widget.DragLayout;

/**
 * @author tome
 * @date 2018/8/10  16:11
 * @describe ${商品详情-商品Fragment中的第一个Fragment}
 */
public class ProductDetailFirstFrag extends CommonFragment implements DragLayout.ShowPageNotifier, OnDimensionSelectListener {
    @BindView(R2.id.banner_product_detail)
    AutoLoopView mBannerProductDetail;
    @BindView(R2.id.tv_product_detail_goods_name)
    TextView mTvProductDetailGoodsName;
    @BindView(R2.id.tv_product_detail_goods_sale)
    TextView mTvProductDetailGoodsSale;
    @BindView(R2.id.tv_product_detail_goods_desc)
    TextView mTvProductDetailGoodsDesc;
    @BindView(R2.id.tv_product_detail_goods_price)
    TextView mTvProductDetailGoodsPrice;
    @BindView(R2.id.view_divider)
    View mViewDivider;
    @BindView(R2.id.tv_product_detail_goods_specification)
    TextView mTvProductDetailGoodsSpecification;
    @BindView(R2.id.ll_product_detail_goods_specification)
    LinearLayout mLlProductDetailGoodsSpecification;
    @BindView(R2.id.ll_product_detail_see_more_evalution)
    LinearLayout mLlProductDetailSeeMoreEvalution;
    @BindView(R2.id.scrollView)
    CustScrollView mScrollView;

    @Override
    public int setContentLayout() {
        return R.layout.cart_fragment_product_detail_frirst;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void onDragLast() {

    }

    @Override
    public void onDragNext() {

    }

    @Override
    public void onSelectDemension(String dimensions,String selectCout) {

    }

    @Override
    public void onProductIntroShow(GetProductDetailsBean.DataBean bean) {

    }

    @Override
    public void onPriceChange(String price) {

    }
}
