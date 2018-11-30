package com.example.tome.module_shop_cart.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioButton;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tome.projectCore.adapter.CommViewHolder;
import com.example.tome.projectCore.adapter.CommonAdapter;
import com.example.tome.module_shop_cart.R;
import com.example.tome.module_shop_cart.bean.GetProductDetailsBean;
import com.example.tome.module_shop_cart.widget.MyGridView;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品属性
 * Created by Administrator on 2017/12/1.
 */

public class ProductAttrsListAdapter extends BaseQuickAdapter<GetProductDetailsBean.DataBean.PropertyBean,BaseViewHolder> {
    private Context context;
    private AttrsAdapter mAttrsAdapter;
    private List<GetProductDetailsBean.DataBean.PropertyBean.PropertyListBean> propertyList_Base = new ArrayList<>();
    private ProductSkuListener mProductSkuListener;
    private int mAdapterPosition;

    public ProductAttrsListAdapter(Context context, @Nullable List<GetProductDetailsBean.DataBean.PropertyBean> data, ProductSkuListener productSkuListener) {
        super(R.layout.cart_selected_product_atts, data);
        this.context = context;
        this.mProductSkuListener = productSkuListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, GetProductDetailsBean.DataBean.PropertyBean item) {
        helper.setText(R.id.tv_shop_attr_name, item.getAttrName());

        mAdapterPosition = helper.getAdapterPosition();
        MyGridView gridView = helper.getView(R.id.mgrid_atts);
        List<GetProductDetailsBean.DataBean.PropertyBean.PropertyListBean> propertyList = item.getPropertyList();
        propertyList_Base.clear();
        propertyList_Base.addAll(propertyList);
//        mAttrsAdapter = new AttrsAdapter(context, propertyList_Base);
        AttrsAdapter attrsAdapter = new AttrsAdapter(context, propertyList, mAdapterPosition);
        gridView.setAdapter(attrsAdapter);



//        RadioGroup rg_group = helper.getView(R.id.rg_group);
//        rg_group.removeAllViews();
//        List<GetProductDetailsBean.DataBean.PropertyBean.PropertyListBean> propertyList = item.getPropertyList();
//        for (int i = 0; i < propertyList.size(); i++) {
//            RadioButton radioButton = (RadioButton) LayoutInflater.from(mContext).inflate(R.layout.shop_item_product_attrs, rg_group,false);
//            ComParamsSet.setProductSKU( (Activity) context,radioButton);
//            radioButton.setChecked(propertyList.get(i).isCheck());
//            radioButton.setText(propertyList.get(i).getAttrValue());
//            final int finalI = i;
//            radioButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mProductSkuListener.toProductDataRefresh(adapterPosition, finalI);
//                }
//            });
//
//
//            rg_group.addView(radioButton);
//        }

    }

   public interface ProductSkuListener{
        void toProductDataRefresh(int position1,int position2);
    }

    class AttrsAdapter extends CommonAdapter<GetProductDetailsBean.DataBean.PropertyBean.PropertyListBean> {
        private int mPosition;
        /**
         * @param mContext 上下文对象
         * @param mData    数据源
         */
        public AttrsAdapter(Context mContext, List<GetProductDetailsBean.DataBean.PropertyBean.PropertyListBean> mData,int position) {
            super(mContext, mData, R.layout.cart_item_product_attrs);
            mPosition = position;
        }

        @Override
        public void convert(final CommViewHolder holder, final GetProductDetailsBean.DataBean.PropertyBean.PropertyListBean item) {
            final RadioButton cbArrtName = holder.getView(R.id.cb_arrt_name);
            cbArrtName.setText(item.getAttrValue());
            cbArrtName.setChecked(item.isCheck());
            final int position = holder.getPosition();
            cbArrtName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    for (GetProductDetailsBean.DataBean.PropertyBean.PropertyListBean d : mData) {
//                        d.setCheck(false);
//                    }
//                    item.setCheck(cbArrtName.isChecked());

//                    for (int i = 0; i < propertyList_Base.size(); i++) {
//                        propertyList_Base.get(i).setCheck(false);
//                    }
//                    propertyList_Base.get(holder.getPosition()).setCheck(true);
//                    notifyDataSetChanged();

                    mProductSkuListener.toProductDataRefresh(mPosition, position);
                }
            });
        }
    }
}
