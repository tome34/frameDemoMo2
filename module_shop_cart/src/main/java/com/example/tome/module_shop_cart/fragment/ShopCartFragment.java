package com.example.tome.module_shop_cart.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tome.module_shop_cart.adapter.ShopCartAdapter;
import com.example.tome.core.util.Arith;
import com.example.tome.core.util.SpannableStringUtils;
import com.example.tome.core.util.ToastUtils;
import com.fec.core.router.arouter.RouterURLS;
import com.example.tome.module_shop_cart.R;
import com.example.tome.module_shop_cart.R2;
import com.example.tome.module_shop_cart.base.BaseData;
import com.example.tome.module_shop_cart.base.BaseObjs;
import com.example.tome.module_shop_cart.base.CommonFragment;
import com.example.tome.module_shop_cart.bean.GetCartInfoBean;
import com.example.tome.module_shop_cart.net.IFlag;
import com.example.tome.module_shop_cart.net.ShopDataManager;
import com.example.tome.module_shop_cart.params.AddCollectionParams;
import com.example.tome.module_shop_cart.params.DeleteProductParams;
import com.example.tome.module_shop_cart.utils.BasicTool;
import com.example.tome.module_shop_cart.widget.LinerDividerItemDecoration;
import java.util.ArrayList;
import java.util.List;

/**
 * @Created by TOME .
 * @时间 2018/6/13 20:50
 * @描述 ${购物车列表}
 */

@Route(path = RouterURLS.PRODUCT_CART)
public class ShopCartFragment extends CommonFragment implements BaseQuickAdapter.OnItemChildClickListener, ShopCartAdapter.OnChangeClick {

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
    @BindView(R2.id.tv_shop_cart_topay)
    TextView mTvShopCartTopay;
    @BindView(R2.id.lin_shopcart_empty)
    LinearLayout mLinShopcartEmpty;
    @BindView(R2.id.rv_shop_list)
    RecyclerView mRvShopList;
    @BindView(R2.id.cb_all_select)
    CheckBox mCbAllSelect;
    @BindView(R2.id.tv_total_money)
    TextView mTvTotalMoney;
    @BindView(R2.id.btn_add_collection2)
    Button mBtnAddCollection2;
    @BindView(R2.id.btn_order2)
    Button mBtnOrder2;
    @BindView(R2.id.btn_del)
    Button mBtnDel;
    @BindView(R2.id.lin_shopcart_notempty)
    LinearLayout mLinShopcartNotempty;
    @BindView(R2.id.layout_shop_cart)
    LinearLayout mLayoutShopCart;
    Unbinder unbinder;

    private boolean isEdit;
    private ShopCartAdapter shopCartAdapter;
    private GetCartInfoBean cartInfo;
    private List<GetCartInfoBean.ProductListBean> productList = new ArrayList<>();
    private int currentPos;
    private int currentNum;
    private StringBuilder selectItems = new StringBuilder();
    //  private SelectedPromotionDialog mSelectedPromotionDialog;
    //  private SelectedShopCarPromotionDialog mSelectedShopCarPromotionDialog2;
    private String productIds_end;
    private String productIds_col_del;//商品收藏成功后需要移除的购物车
    private boolean isShowDeleteLog = false;//是否显示删除成功的log

    public static ShopCartFragment newInstance(String info) {
        Bundle args = new Bundle();
        ShopCartFragment fragment = new ShopCartFragment();
        args.putString("info",info);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int setContentLayout() {
        return R.layout.cart_shop_cart_fragment;
    }

    @Override
    public void initTitle() {
        mLayoutShopCart.setBackgroundColor(getResources().getColor(R.color.windowBg));
        mTitleContentText.setText(getString(R.string.cart_shop_cart));
        Bundle arguments = getArguments();

        mTitleRightText.setVisibility(View.VISIBLE);
        mTitleRightText.setText(getString(R.string.cart_edit));
        isEdit = false;
        setEdit();
    }

    @Override
    public void initView() {

        shopCartAdapter = new ShopCartAdapter(this,productList);
        mRvShopList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvShopList.addItemDecoration(new LinerDividerItemDecoration(getContext(),1));
        mRvShopList.setAdapter(shopCartAdapter);

        shopCartAdapter.setOnItemChildClickListener(this);
        shopCartAdapter.setOnChangeClick(this);
        mCbAllSelect.setChecked(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            loadData();
        }
    }

    /**
     * 加载购物车数据
     */
    private void loadData() {
        ShopDataManager.getCartInfo(this);
    }

    @Override
    public void onSuccess(int flag,Object baseBean) {
        super.onSuccess(flag,baseBean);
        if (flag == IFlag.FLAG_GETCARTINFO) {
            //购物车列表数据
            cartInfo = ((BaseObjs<GetCartInfoBean>) baseBean).getData();
            if (cartInfo == null) {
                return;
            }
            productList.clear();
            productList.addAll(cartInfo.getProductList());
            showTotal();
            shopCartAdapter.notifyDataSetChanged();
            mCbAllSelect.setChecked(false);
            if (productList == null || productList.size() == 0) {
                mLinShopcartEmpty.setVisibility(View.VISIBLE);
                mLinShopcartNotempty.setVisibility(View.GONE);
            } else {
                mLinShopcartEmpty.setVisibility(View.GONE);
                mLinShopcartNotempty.setVisibility(View.VISIBLE);
            }
        } else if (flag == IFlag.FLAG_URL_DEL_PRODUCT_BY_CARTID) {
            //批量收藏或删除
            if (isShowDeleteLog) {
                ToastUtils.showCenter(getString(R.string.cart_delete_success));
            }else {
                ToastUtils.showShort(getActivity(), getString(R.string.cart_collection_add_success));
            }
            loadData();
        }
    }

    private void setEdit() {
        if (isEdit) {
            if (mBtnAddCollection2 != null) {
                mBtnAddCollection2.setVisibility(View.VISIBLE);
            }
            mBtnDel.setVisibility(View.VISIBLE);
            mBtnOrder2.setVisibility(View.GONE);
            mTvTotalMoney.setVisibility(View.INVISIBLE);
        } else {
            if (mBtnAddCollection2 != null) {
                mBtnAddCollection2.setVisibility(View.GONE);
            }
            mBtnDel.setVisibility(View.GONE);
            mBtnOrder2.setVisibility(View.VISIBLE);
            mTvTotalMoney.setVisibility(View.VISIBLE);
        }
    }

    @OnClick( {
        R2.id.title_right_text,R2.id.cb_all_select,R2.id.btn_order2,R2.id.tv_shop_cart_topay,R2.id.btn_del,R2.id.btn_add_collection2 })
    public void onViewClicked(View view) {
        if (view.getId() == R.id.title_right_text) {
            //编辑
            if (isEdit) {
                mTitleRightText.setText(getString(R.string.cart_edit));
            } else {
                mTitleRightText.setText(getString(R.string.cart_complete));
            }

            shopCartAdapter.setAllowSlide(isEdit);
            isEdit = !isEdit;
            setEdit();
            shopCartAdapter.notifyDataSetChanged();
        } else if (view.getId() == R.id.cb_all_select) {
            //全选
            isAllSelect(mCbAllSelect.isChecked());
        } else if (view.getId() == R.id.btn_order2) {
            //下单
            if (isSelectProduct()) {
               // RouterCenter.toCheckOrder(selectItems.toString());
                ToastUtils.showCenter("下单了");
            } else {
                ToastUtils.showCenter(getString(R.string.cart_please_shop));
            }
        } else if (view.getId() == R.id.tv_shop_cart_topay) {
            //立即购物,跳转到首页
            ToastUtils.showCenter("请到首页选购");
        } else if (view.getId() == R.id.btn_del) {
            //批量删除商品
            String cartIds = "";
            for (GetCartInfoBean.ProductListBean p : productList) {
                boolean check = p.isCheck();
                if (check) {
                    String cartId = p.getCartId();

                    if (BasicTool.isEmpty(cartIds)) {
                        cartIds = cartId;
                    } else {
                        cartIds = cartIds + "," + cartId;
                    }
                }
            }
            final String cartIds_end = cartIds;

            //是否勾选商品
            if (BasicTool.isEmpty(cartIds_end)) {
                ToastUtils.showShort(getActivity(),getString(R.string.cart_please_shop));
                return;
            }

            new AlertView(getString(R.string.cart_delete_product_hint),null,getString(R.string.cart_shop_cancel),new String[] { getString(R.string.cart_shop_confrim) },null,
                getActivity(),AlertView.Style.Alert,new OnItemClickListener() {
                @Override
                public void onItemClick(Object o,int position) {
                    if (position == 0) {
                        isShowDeleteLog = true;
                        DeleteProductParams params = new DeleteProductParams();
                        params.setCartIds(cartIds_end);
                        ShopDataManager.deleteProduct(ShopCartFragment.this,params);
                    }
                }
            }).show();
        } else if (view.getId() == R.id.btn_add_collection2) {
            //批量移入收藏
            String productIds = "";
            String cartIds = "";

            for (GetCartInfoBean.ProductListBean p : productList) {
                boolean check = p.isCheck();
                if (check) {
                    String subProductId = p.getSubProductId();
                    String cartId = p.getCartId();

                    if (BasicTool.isEmpty(productIds)) {
                        productIds = subProductId;
                    } else {
                        productIds = productIds + "," + subProductId;
                    }
                    if (BasicTool.isEmpty(cartIds)) {
                        cartIds = cartId;
                    } else {
                        cartIds = cartIds + "," + cartId;
                    }
                }
            }
            productIds_end = productIds;
            productIds_col_del = cartIds;
            //是否勾选商品
            if (BasicTool.isEmpty(productIds_end)) {
                ToastUtils.showShort(getActivity(),getString(R.string.cart_please_shop));
                return;
            }

            AddCollectionParams params = new AddCollectionParams();
            params.setProductId(productIds_end);
            ToastUtils.showShort(getActivity(),"加入收藏成功"+productIds_end);
        }
    }

    /**
     * item点击事件
     */
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter,View view,int position) {
        if (view.getId() == R.id.tv_to_collection) {
            //侧滑收藏
            ToastUtils.showCenter("收藏");

        } else if (view.getId() == R.id.cb_shop_check) {
            ToastUtils.showCenter("选择商品");

        } else if (view.getId() == R.id.tv_del_product) {
            //侧滑删除
            ToastUtils.showCenter("删除");
        } else if (view.getId() == R.id.iv_product_img) {

            ToastUtils.showCenter("点击了图片");
        } else if (view.getId() == R.id.iv_product_name) {

            ToastUtils.showCenter("点击了name");
        } else if (view.getId() == R.id.llyt_product_sale) {
            ToastUtils.showCenter("点击了条目");
        }
    }

    /**
     * editText输入数据监听
     */
    @Override
    public void textChange(View v,int num,int pos) {

    }

    //是否全选
    private void isAllSelect(boolean isAll) {
        for (GetCartInfoBean.ProductListBean p : productList) {
            p.setCheck(isAll);
        }
        shopCartAdapter.notifyDataSetChanged();
        showTotal();
    }

    /**
     * 显示总金额
     */
    private void showTotal() {
        String totalMoney = "0.00";
        for (GetCartInfoBean.ProductListBean product : productList) {
            if (product.isCheck()) {
                totalMoney = Arith.add(Arith.mul(product.getTruePrice(), product.getItemCount() + ""), totalMoney);
            }
        }
        SpannableStringBuilder totalMoneySpanble = new SpannableStringUtils(getContext())
            .getBuilder(getString(R.string.cart_shop_total))
            .setForegroundColor(ContextCompat.getColor(getContext(), R.color.color33))
            .append(BaseData.UNIT + Arith.get2Decimal(totalMoney))
            .setForegroundColor(ContextCompat.getColor(getContext(), R.color.mainColor))
            .create();
        mTvTotalMoney.setText(totalMoneySpanble);
    }

    /**
     * 是否有选择商品
     */
    private boolean isSelectProduct() {
        int count = 0;
        selectItems = new StringBuilder();
        for (GetCartInfoBean.ProductListBean p : productList) {
            if (p.isCheck()) {
                selectItems.append(p.getCartId() + ",");
                count++;
            }
        }
        if (selectItems.toString().endsWith(",")) {
            selectItems.deleteCharAt(selectItems.length() - 1);
        }
        return count != 0;
    }
}
