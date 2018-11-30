package com.example.tome.module_shop_cart.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tome.core.util.Arith;
import com.example.tome.core.util.L;
import com.example.tome.module_shop_cart.R;
import com.example.tome.module_shop_cart.base.BaseData;
import com.example.tome.module_shop_cart.bean.GetCartInfoBean;
import com.example.tome.module_shop_cart.utils.BasicTool;
import com.example.tome.module_shop_cart.widget.CustomNumInputView;
import java.util.List;

/**
 * 购物车
 * Created by Administrator on 2017/12/7.
 */

public class ShopCartAdapter extends BaseQuickAdapter<GetCartInfoBean.ProductListBean,BaseViewHolder> {
    private Fragment fragment;
    //是否允许侧滑
    private boolean allowSlide = true;
    private OnChangeClick onChangeClick;

    public void setOnChangeClick(OnChangeClick onChangeClick) {
        this.onChangeClick = onChangeClick;
    }

    public void setAllowSlide(boolean allowSlide) {
        this.allowSlide = allowSlide;
    }

    public ShopCartAdapter(Fragment fragment, @Nullable List<GetCartInfoBean.ProductListBean> data) {
        super(R.layout.cart_item_shopcart, data);
        this.fragment = fragment;
    }

    @Override
    protected void convert(final BaseViewHolder helper, GetCartInfoBean.ProductListBean item) {
      //  ImageLoaderHelper.load(mContext, item.getFull_path(), (ImageView) helper.getView(R.id.iv_product_img));
        helper.setText(R.id.iv_product_name, item.getProductName());
        helper.setText(R.id.iv_product_price, BaseData.UNIT + Arith.get2Decimal(item.getTruePrice() + ""));

        CustomNumInputView civProductNum = helper.getView(R.id.civ_product_num);
        civProductNum.setCurentVal(item.getItemCount());
        civProductNum.setMax(item.getTotal_stock());

        civProductNum.setCustomNumInputTextChange(new CustomNumInputView.CustomNumInputTextChange() {
            @Override
            public void textChange(View v, int num) {
                if (onChangeClick != null) {
                    onChangeClick.textChange(v, num,helper.getAdapterPosition());
                }
            }
        });


        helper.getView(R.id.tv_to_collection).setVisibility(allowSlide ? View.VISIBLE : View.GONE);
        helper.getView(R.id.tv_del_product).setVisibility(allowSlide ? View.VISIBLE : View.GONE);

        LinearLayout llyt_product_sale = helper.getView(R.id.llyt_product_sale);
        TextView tv_product_sale_tag = helper.getView(R.id.tv_product_sale_tag);
        TextView tv_product_sale_content = helper.getView(R.id.tv_product_sale_content);


        List<GetCartInfoBean.MarketRuleList> marketRuleList = item.getMarketRuleList();

        if (marketRuleList != null && marketRuleList.size() > 0) {
            llyt_product_sale.setVisibility(View.VISIBLE);

            L.d("item.getMarketRuleName()=="+item.getMarketRuleName());
            tv_product_sale_tag.setText(BasicTool.isEmpty(item.getMarketRuleName())?"":item.getMarketRuleName());
            tv_product_sale_content.setText(Html.fromHtml(marketRuleList.get(0).getRuleInfo()));
        }


        CheckBox cbShopBox = helper.getView(R.id.cb_shop_check);
        cbShopBox.setChecked(item.isCheck());


        helper
                .addOnClickListener(R.id.llyt_product_sale)
                .addOnClickListener(R.id.iv_product_img)
                .addOnClickListener(R.id.iv_product_name)
                .addOnClickListener(R.id.tv_to_collection)  //侧滑加入收藏
                .addOnClickListener(R.id.cb_shop_check)
                .addOnClickListener(R.id.tv_del_product);   //侧滑删除


        LinearLayout llytLabs = helper.getView(R.id.llyt_lables);
        llytLabs.removeAllViews();
        for (int i = 0; i < item.getProperty().size(); i++) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.cart_item_product_tag, null);
            TextView tvProductTag = view.findViewById(R.id.tv_product_tag);
            tvProductTag.setText( item.getProperty().get(i)+"");
            llytLabs.addView(view);
        }
    }

    public interface OnChangeClick {
        void textChange(View v,int num,int pos);
    }
}
