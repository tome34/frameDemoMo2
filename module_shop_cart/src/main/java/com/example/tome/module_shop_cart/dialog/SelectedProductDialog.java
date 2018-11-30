package com.example.tome.module_shop_cart.dialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.example.tome.module_shop_cart.adapter.ProductAttrsListAdapter;
import com.example.tome.projectCore.helper.LocaleHelper;
import com.example.tome.core.util.L;
import com.example.tome.core.util.ToastUtils;
import com.example.tome.core.widget.MyRecyclerView;
import com.example.tome.module_shop_cart.R;
import com.example.tome.module_shop_cart.R2;
import com.example.tome.module_shop_cart.activity.ProductDetailActivity;
import com.example.tome.module_shop_cart.base.BaseData;
import com.example.tome.module_shop_cart.bean.GetProductDetailsBean;
import com.example.tome.module_shop_cart.utils.BasicTool;
import com.example.tome.module_shop_cart.widget.CustomNumInputView;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import java.util.List;
import java.util.Locale;


/**
 * 商品参数
 */
public class SelectedProductDialog extends TaoBaoDialog<SelectedProductDialog> implements ProductAttrsListAdapter.ProductSkuListener {
    @BindView(R2.id.rv_attrs_list)
    MyRecyclerView rvAttrsList;
    @BindView(R2.id.tv_product_name)
    TextView tvProductName;
    @BindView(R2.id.tv_product_price)
    TextView tvProductPrice;
    @BindView(R2.id.btn_buy)
    TextView btnBuy;
    @BindView(R2.id.btn_add2cart)
    TextView btnAdd2cart;
    @BindView(R2.id.btn_shop_confrim)
    TextView btnShopConfrim;
    @BindView(R2.id.iv_product_img)
    ImageView ivProductImg;
    @BindView(R2.id.iv_partivipate_crowd_fundign_minue)
    ImageView ivPartivipateCrowdfundignminue;
    @BindView(R2.id.iv_partivipate_crowd_fundign_add)
    ImageView ivPartivipateCrowdfundignAdd;
    @BindView(R2.id.tv_crowd_funding_moq)
    EditText tvCrowdfundingMoq;
    @BindView(R2.id.rlyt_layout_background)
    LinearLayout rlytLayoutBackGround;
    @BindView(R2.id.llyt_layout_background)
    LinearLayout llytLayoutBackGround;
    @BindView(R2.id.iv_product_close)
    ImageView ivProductClose;
    private int minVal = 1;
    private int curentVal = 1;
    private int max = 1;
    private int stepSize = 1;

    private ProductAttrsListAdapter productAttrsListAdaptet;
    private GetProductDetailsBean.DataBean productDetails;
    private ProductDetailActivity mProductDetailActivity;
    private ProductSelect productSelect;
    private List<GetProductDetailsBean.DataBean.PropertyBean> mProperty;
    private GetProductIdListener mGetProductIdListener;
    private CustomNumInputView.CustomNumInputTextChange customNumInputTextChange;
    private String mOnSale;

    public Context mContext ;


    public SelectedProductDialog(Context context, View rootView) {
        super(context, rootView, R.style.cart_alex_dialog_base_light_style);
        this.mContext = context ;
        this.productSelect = (ProductSelect) context;
    }
    public SelectedProductDialog(Context context, View rootView,GetProductIdListener getProductIdListener) {
        super(context, rootView, R.style.cart_alex_dialog_base_light_style);
        this.mContext = context ;
        this.productSelect = (ProductSelect) context;
        this.mGetProductIdListener = getProductIdListener;
    }

    /**
     * 配置 对话框的 布局文件
     */
    @Override
    public int getLayoutRes() {
        return R.layout.cart_dialog_selected_product;
    }

    /**
     * 在 这里 进行 findView  设置点击事件
     */
    @Override
    public void onCreateData() {
        setScaleWidth(1F);
        mProductDetailActivity = (ProductDetailActivity) context;
        if (mProductDetailActivity == null){
            return;
        }
        productDetails = mProductDetailActivity.getProductDetails();
        if (productDetails == null || productDetails.getProperty() == null) {
            return;
        }
        mProperty = productDetails.getProperty();


        rlytLayoutBackGround.setBackgroundColor(mProductDetailActivity.getResources().getColor(R.color.white));
        llytLayoutBackGround.setBackgroundColor(mProductDetailActivity.getResources().getColor(R.color.white));
        setUiSetting();
        if (productDetails != null) {
            //处理数据eg: "selectAttrStr" : "香型_82,亮度_91,耗电量_83",(一进来就需要默认选中的)
            String selectAttrStr = productDetails.getSelectAttrStr();
            if (BasicTool.isNotEmpty(selectAttrStr)) {
                String[] select_att = selectAttrStr.split(",");
                for (int i = 0; i < select_att.length; i++) {
                    String[] split_att_chil = select_att[i].split("_");

                    if (split_att_chil.length > 1) {
                        String split_att_chil_key = split_att_chil[0];
                        String split_att_chil_value = split_att_chil[1];

                        for (int j = 0; j < mProperty.size(); j++) {
                            GetProductDetailsBean.DataBean.PropertyBean propertyBean = mProperty.get(j);
                            String attrName = propertyBean.getAttrName();
                            List<GetProductDetailsBean.DataBean.PropertyBean.PropertyListBean> propertyList = propertyBean.getPropertyList();

            //以上面的假数据举例,先找到attrName==香型的,再找到香型list里面的id为82的
                            if (attrName.equals(split_att_chil_key)) {

                                for (int k = 0; k < propertyList.size(); k++) {
                                    GetProductDetailsBean.DataBean.PropertyBean.PropertyListBean propertyListBean = propertyList.get(k);
                                    String attrvalueId = propertyListBean.getAttrvalueId();
                                    if (attrvalueId.equals(split_att_chil_value)) {
                                        //设置为选中
                                        mProperty.get(j).getPropertyList().get(k).setCheck(true);

                                    }else {
                                        mProperty.get(j).getPropertyList().get(k).setCheck(false);
                                    }

                                }

                            }
                        }
                    }
                }
            }


            productAttrsListAdaptet = new ProductAttrsListAdapter(context, mProperty,this);
            rvAttrsList.setLayoutManager(new LinearLayoutManager(context));
            rvAttrsList.setAdapter(productAttrsListAdaptet);

            GetProductDetailsBean.DataBean.SubProductMapBean subProduct = productDetails.getSubProductMap();
            tvProductName.setText(subProduct.getProductName());
            tvProductPrice.setText(BaseData.UNIT + subProduct.getPfPrice());

            tvCrowdfundingMoq.setText("1");
            //库存字段
            max = productDetails.getSubProductMap().getTotalStock();
            List<String> productImgPath = productDetails.getSubProductMap().getProductImgPath();
            String product_img = "";
            if (productImgPath != null&&productImgPath.size()>0) {
                 product_img = productImgPath.get(0);
            }

           // ImageLoaderHelper.load(mContext, product_img, ivProductImg);

            mOnSale = BasicTool.isEmpty(subProduct.getPfOnSale()) ? "0" : subProduct.getPfOnSale();
            if ("1".equals(mOnSale)) {

                btnBuy.setText(context.getString(R.string.cart_shop_buy));
                btnAdd2cart.setVisibility(View.VISIBLE);
            } else {
                btnBuy.setText(context.getString(R.string.cart_undercarriage_state));
                btnAdd2cart.setVisibility(View.GONE);
            }



            tvCrowdfundingMoq.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    try {
                        String content = s.toString().trim();
                        int number = Integer.parseInt(content);
                        if (number<1){
                            number = 1;
                        }else if(max==0){
                            number = 1;
                        }else if(number>max){
                            number = max;
                        }
                        if (String.valueOf(number).equals(content)){
                            return;
                        }
                        tvCrowdfundingMoq.setText(String.valueOf(number));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });



        }
    }

    @Override
    public void show() {
        super.show();
        //立即购买和加入购物车直接显示


//        isShowConfirmBtn();
    }

    public void setCustomNumInputTextChange(CustomNumInputView.CustomNumInputTextChange customNumInputTextChange) {
        this.customNumInputTextChange = customNumInputTextChange;
    }




    /**
     * 处理按钮点击事件 并绑定 onDialogClickListener
     *
     * @param v
     */
    @Override
    public void onClick(View v, int id) {
        if (R.id.iv_product_close == id) {
            dismiss();
            if (mProductDetailActivity != null) {
                productSelect.shopConfrim(getSelectText(), productDetails.getProperty());
            }


        } else if (id == R.id.btn_shop_confrim) {
            if (max==0){
                ToastUtils.showShort(context,context.getString(R.string.cart_detail_dialog_stock_not_enough));
                return;
            }
            if (mProductDetailActivity != null) {
                if (checkSelectAttr()) {
                    productSelect.shopConfrim(getSelectText(), productDetails.getProperty());
                    dismiss();
                }
            }
        } else if (id == R.id.btn_buy) {
            if (max==0){
                ToastUtils.showShort(context,context.getString(R.string.cart_detail_dialog_stock_not_enough));
                return;
            }
            if (checkSelectAttr()) {
                if (mProductDetailActivity != null) {
                        productSelect.shopConfrim(getSelectText(), productDetails.getProperty());
                }

                productSelect.shopBuy(tvCrowdfundingMoq.getText().toString().trim() + "");
                dismiss();
            }
        } else if (id == R.id.btn_add2cart) {
            if (max==0){
                ToastUtils.showShort(context,context.getString(R.string.cart_detail_dialog_stock_not_enough));
                return;
            }
            if (checkSelectAttr()) {
                if (mProductDetailActivity != null) {
                    productSelect.shopConfrim(getSelectText(), productDetails.getProperty());
                }
                productSelect.shopAdd2Cart(tvCrowdfundingMoq.getText().toString().trim() + "");
                dismiss();
            }
        } else if (id == R.id.iv_partivipate_crowd_fundign_minue) {
            orderNum(0);
        } else if (id == R.id.iv_partivipate_crowd_fundign_add) {
            orderNum(1);
        }
    }


    private void orderNum(int type) {
        int moqNum = 1;
        String moqStr = tvCrowdfundingMoq.getText() + "";
        if (!TextUtils.isEmpty(moqStr)) {
            moqNum = Integer.parseInt(moqStr);
        }

        if (type == 1) {
            moqNum += stepSize;
            if (moqNum <= max) {
                L.i("----" + moqNum);
                if (customNumInputTextChange != null) {
                    customNumInputTextChange.textChange(tvCrowdfundingMoq, moqNum);
                }
                tvCrowdfundingMoq.setText(moqNum + "");
            }else {
                ToastUtils.showShort(context,context.getString(R.string.cart_detail_dialog_stock_not_enough));
            }
        } else if (moqNum > minVal) {
            moqNum -= stepSize;
            if (customNumInputTextChange != null) {
                customNumInputTextChange.textChange(tvCrowdfundingMoq, moqNum);
            }
            tvCrowdfundingMoq.setText(moqNum + "");
        }

    }

    public int getNum() {
        if (TextUtils.isEmpty(tvCrowdfundingMoq.getText())) {
            return 0;
        }
        String num = tvCrowdfundingMoq.getText().toString();
        return Integer.parseInt(num);
    }

    @Override
    public void toProductDataRefresh(int position1, int position2) {


        for (int i = 0; i < mProperty.get(position1).getPropertyList().size(); i++) {
            mProperty.get(position1).getPropertyList().get(i).setCheck(false);
        }
        mProperty.get(position1).getPropertyList().get(position2).setCheck(true);

        String[] select_attr  =new String[mProperty.size()];

        //取出商品属性
        for (int i = 0; i < mProperty.size(); i++) {
            List<GetProductDetailsBean.DataBean.PropertyBean.PropertyListBean> propertyList = mProperty.get(i).getPropertyList();
            String attrName = mProperty.get(i).getAttrName();

            for (int j = 0; j < propertyList.size(); j++) {
                String attrValue = propertyList.get(j).getAttrValue();
                String attrvalueId = propertyList.get(j).getAttrvalueId();
                boolean check = propertyList.get(j).isCheck();
                if (check) {
                    String content = attrName + "_" + attrvalueId;
                    select_attr[i] = content;
                }
            }

        }
            String attrMapStr = productDetails.getAttrMapStr();

        String[] product_attr = attrMapStr.split("\\$");

        for (int i = 0; i < product_attr.length; i++) {
            String product_attr_str = product_attr[i];
            int product_attr_con = 0;
            for (int j = 0; j < select_attr.length; j++) {
                if (product_attr_str.contains(select_attr[j])) {
                    product_attr_con++;
                    if (product_attr_con == select_attr.length) {
                        String[] product_id_attr = product_attr_str.split("=");
                        if (product_id_attr.length > 1) {
                            String product_id = product_id_attr[0];

                            mGetProductIdListener.toProductId(product_id);
                            L.d("商品id="+product_id);
                        }

                    }
                }

            }
        }


        productAttrsListAdaptet.notifyDataSetChanged();


    }

    public interface ProductSelect {
        void shopConfrim(String select,List<GetProductDetailsBean.DataBean.PropertyBean> list);

        void shopBuy(String num);

        void shopAdd2Cart(String num);

        boolean isSelectShop();

        GetProductDetailsBean.DataBean getProductDetails();
    }

    //判断是否选择属性
    private boolean checkSelectAttr() {

        if (mProperty != null)
        for (int i = 0; i < mProperty.size(); i++) {
            GetProductDetailsBean.DataBean.PropertyBean propertyBean = mProperty.get(i);
            List<GetProductDetailsBean.DataBean.PropertyBean.PropertyListBean> propertyList = propertyBean.getPropertyList();
            int cum = 0;
            for (int j = 0; j < propertyList.size(); j++) {
                boolean check = propertyList.get(j).isCheck();
                if (!check) {
                    cum++;
                    if (cum == propertyList.size()) {
                        ToastUtils.showCenter(context.getString(R.string.cart_please_select) + mProperty.get(i).getAttrName());
                        return false;
                    }
                }

            }

        }



        return true;
    }

    //获取选中的文本
    private String getSelectText() {
        String selectText = "";

        for (int i = 0; i < mProperty.size(); i++) {
            GetProductDetailsBean.DataBean.PropertyBean propertyBean = mProperty.get(i);
            List<GetProductDetailsBean.DataBean.PropertyBean.PropertyListBean> propertyList = propertyBean.getPropertyList();
            for (int j = 0; j < propertyList.size(); j++) {
                boolean check = propertyList.get(j).isCheck();
                if (check) {
                    String attrValue = propertyList.get(j).getAttrValue();
                    if (BasicTool.isEmpty(selectText)) {
                        selectText = attrValue;
                    }else {
                        selectText = selectText + "，" + attrValue;
                    }
                }

            }

        }





        selectText += getNum() + BaseData.COUNT_PIECE_UNIT;
        return selectText;
    }

    //是否显示确定按钮   无用代码
    private void isShowConfirmBtn() {
        if (btnShopConfrim == null) return;

        //if (ProductDetailActivity.isSelectShop()) {
        //    btnBuy.setVisibility(View.GONE);
        //    btnAdd2cart.setVisibility(View.GONE);
        //    btnShopConfrim.setVisibility(View.VISIBLE);
        //} else {
        //    btnBuy.setVisibility(View.VISIBLE);
        //    btnAdd2cart.setVisibility(View.VISIBLE);
        //    btnShopConfrim.setVisibility(View.GONE);
        //}
    }


  public  interface GetProductIdListener{
        void toProductId(String id);
    }



    private void setUiSetting(){
        Locale language = LocaleHelper.getLanguage(context);
        LocaleHelper.setLocale(context, language);
        ZXingLibrary.initDisplayOpinion(context);

        if ("ar".equals(language.getLanguage())){
            rlytLayoutBackGround.setPadding(0,0, DensityUtil.dp2px(110),0);
        }else if("en".equals(language.getLanguage())){
            rlytLayoutBackGround.setPadding( DensityUtil.dp2px(110),0, 0,0);
        }else{
            rlytLayoutBackGround.setPadding( DensityUtil.dp2px(110),0,0,0);
        }
    }
}
