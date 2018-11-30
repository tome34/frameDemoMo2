package com.example.tome.module_shop_cart.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.example.tome.core.util.L;
import com.example.tome.core.util.ToastUtils;
import com.example.tome.module_shop_cart.R;
import com.example.tome.module_shop_cart.R2;
import com.example.tome.module_shop_cart.base.BaseObjs;
import com.example.tome.module_shop_cart.base.CommonActivity;
import com.example.tome.module_shop_cart.base.Contract;
import com.example.tome.module_shop_cart.bean.GetProductDetailsBean;
import com.example.tome.module_shop_cart.dialog.SelectedProductDialog;
import com.example.tome.module_shop_cart.fragment.ProductEvaluateFragment;
import com.example.tome.module_shop_cart.fragment.ProductIntroFragment;
import com.example.tome.module_shop_cart.listener.OnDimensionSelectListener;
import com.example.tome.module_shop_cart.net.IFlag;
import com.example.tome.module_shop_cart.net.ShopDataManager;
import com.example.tome.module_shop_cart.params.AddToCartParams;
import com.example.tome.module_shop_cart.params.GetProductDetailsParams;
import com.example.tome.module_shop_cart.widget.DragLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品详情页
 */
public class ProductDetailActivity extends CommonActivity implements RadioGroup.OnCheckedChangeListener, DragLayout.ShowPageNotifier, View.OnClickListener,
    SelectedProductDialog.ProductSelect, SelectedProductDialog.GetProductIdListener {

    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.rb_product)
    RadioButton mRbProduct;
    @BindView(R2.id.rb_detail)
    RadioButton mRbDetail;
    @BindView(R2.id.rb_evaluate)
    RadioButton mRbEvaluate;
    @BindView(R2.id.radio_group)
    RadioGroup mRadioGroup;
    @BindView(R2.id.iv_shop_cart)
    ImageView mIvShopCart;
    @BindView(R2.id.fl_container)
    FrameLayout mFlContainer;
    @BindView(R2.id.iv_collection)
    ImageView mIvCollection;
    @BindView(R2.id.ll_collection)
    LinearLayout mLlCollection;
    @BindView(R2.id.tv_join_shop_cart)
    TextView mTvJoinShopCart;
    @BindView(R2.id.tv_buy_now)
    TextView mTvBuyNow;
    @BindView(R2.id.activity_product_detail)
    LinearLayout mActivityProductDetail;
    @BindView(R2.id.view_title)
    View mViewTitle;
    private String mProductId;
    private GetProductDetailsParams mParams;
    private int currentIndex;
    private Fragment currentFragment;

    private List<Fragment> fragmentList;
    //监听
    private List<OnDimensionSelectListener> mOnDimensionSelectListeners;
    private ProductIntroFragment mProductIntroFragment;
    private ProductEvaluateFragment mProductEvaluateFragment;
    private GetProductDetailsBean.DataBean productDetails;
    private SelectedProductDialog selectedProductDialog;

    @Override
    public int setContentLayout() {
        return R.layout.cart_activity_product_detail;
    }

    @Override
    public void initTitle() {
        setTitleMargin(mViewTitle);
    }

    @Override
    public void initView() {
        mOnDimensionSelectListeners = new ArrayList<>();
        mIvBack.setOnClickListener(this);
        mIvShopCart.setOnClickListener(this);
        mTvJoinShopCart.setOnClickListener(this);
        mTvBuyNow.setOnClickListener(this);

        initFragments();
        mRadioGroup.setOnCheckedChangeListener(this);
        mRbProduct.setChecked(true);
        Intent intent = getIntent();
        mProductId = intent.getStringExtra(Contract.K_PRODUCT_ID);
        L.d("商品id:" + mProductId);
        mParams = new GetProductDetailsParams();
        mParams.setProductId(mProductId);
        //网络请求
        ShopDataManager.getProductDetails(this,mParams);
    }

    private void initFragments() {
        fragmentList = new ArrayList<>();
        //商品简介
        mProductIntroFragment = new ProductIntroFragment();
        fragmentList.add(mProductIntroFragment);
        //评价详情
        mProductEvaluateFragment = new ProductEvaluateFragment();
        addOnDimensionSelectListener(mProductEvaluateFragment);
        fragmentList.add(mProductEvaluateFragment);
    }

    @Override
    public void onSuccess(int flag,Object baseBean) {
        super.onSuccess(flag,baseBean);
        //获取商品详情页
        if (flag == IFlag.FLAG_PRODUCT_GETPRODUCTDETAIL) {
            L.d("获取详情页信息成功");
            BaseObjs<GetProductDetailsBean> productDetailsBeanBase = (BaseObjs<GetProductDetailsBean>)baseBean;
            productDetails = productDetailsBeanBase.getData().getData();

            if (selectedProductDialog != null) {
                selectedProductDialog.onCreateData();
            }
            GetProductDetailsBean.DataBean.SubProductMapBean subProductMap = productDetails.getSubProductMap();
            //显示详情信息
            for (OnDimensionSelectListener listener : mOnDimensionSelectListeners) {
                listener.onProductIntroShow(productDetails);
            }
        } else if (flag == IFlag.FLAG_ADD_PRODUCT_TOB2B_CART2) {
            //加入购物车
            ToastUtils.showCenter("已加入购物车");
        }
    }

    /**
     * tab的监听
     */
    @Override
    public void onCheckedChanged(RadioGroup group,int checkedId) {
        if (checkedId == R.id.rb_product) {
            //商品
            currentIndex = 0;
            showFragment();
            if (mRbProduct.isPressed()) {
                mProductIntroFragment.showProductIntro();
            }
        } else if (checkedId == R.id.rb_detail) {
            //详情
            currentIndex = 0;
            showFragment();
            if (mRbDetail.isPressed()) {
                mProductIntroFragment.showProductDetail();
            }
        } else if (checkedId == R.id.rb_evaluate) {
            //评论
            currentIndex = 1;
            showFragment();
        }
    }

    private void showFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }
        if (!fragmentList.get(currentIndex).isAdded()) {
            //第三个参数为添加当前的fragment时绑定一个tag
            transaction.add(R.id.fl_container,fragmentList.get(currentIndex),"" + currentIndex);
        } else {
            transaction.show(fragmentList.get(currentIndex));
        }
        currentFragment = fragmentList.get(currentIndex);
        transaction.commit();
    }

    @Override
    public void shopConfrim(String select,List<GetProductDetailsBean.DataBean.PropertyBean> list) {

    }

    @Override
    public void shopBuy(String num) {

    }

    @Override
    public void shopAdd2Cart(String num) {
        // if (BaseData.IdentityType == BaseData.ENTERPRISE) {
        //本地购物车
        // addToLocalCart(num, false);
        // } else {
        //线上购物车
        L.d("购物车","id:" + productDetails.getSubProductMap().getId() + "_" + num);
        AddToCartParams params = new AddToCartParams();
        params.setProductIds(productDetails.getSubProductMap().getId() + "_" + num);
        ShopDataManager.addToCart(this,params);
        //  }
    }

    @Override
    public boolean isSelectShop() {
        return false;
    }

    @Override
    public GetProductDetailsBean.DataBean getProductDetails() {
        return productDetails;
    }

    //添加规格属性选择监听
    public void addOnDimensionSelectListener(OnDimensionSelectListener listener) {
        mOnDimensionSelectListeners.add(listener);
    }

    public void removeOnDimensionSelectListener(OnDimensionSelectListener listener) {
        mOnDimensionSelectListeners.remove(listener);
    }

    @Override
    public void onDragLast() {
        //下拉选中简介标签
        mRadioGroup.check(R.id.rb_product);
    }

    @Override
    public void onDragNext() {
        //上拉时选中详情标签
        mRadioGroup.check(R.id.rb_detail);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            finish();
        } else if (v.getId() == R.id.iv_shop_cart) {
            //跳转到购物车页面
            Intent intent = new Intent(ProductDetailActivity.this,ShopCartActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(Contract.K_FRAGMENT,"1");
            this.startActivity(intent);
        } else if (v.getId() == R.id.tv_join_shop_cart) {
            //加入购物车
            if (selectedProductDialog == null) {
                selectedProductDialog = new SelectedProductDialog(ProductDetailActivity.this,findViewById(Window.ID_ANDROID_CONTENT),this);
                selectedProductDialog.onCreateData();
            } else {
                selectedProductDialog.onCreateData();
            }
            selectedProductDialog.show();
        } else if (v.getId() == R.id.tv_buy_now) {
            //立即购买

        }
    }

    /**
     * 刷新数据
     */
    @Override
    public void toProductId(String id) {

    }

}
