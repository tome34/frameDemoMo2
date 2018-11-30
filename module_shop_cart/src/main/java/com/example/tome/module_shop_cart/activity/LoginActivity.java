package com.example.tome.module_shop_cart.activity;

import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.example.tome.core.util.SPUtil;
import com.example.tome.core.util.ToastUtils;
import com.fec.core.router.arouter.IntentKV;
import com.example.tome.module_shop_cart.BuildConfig;
import com.example.tome.module_shop_cart.R;
import com.example.tome.module_shop_cart.R2;
import com.example.tome.module_shop_cart.base.BaseData;
import com.example.tome.module_shop_cart.base.BaseObjs;
import com.example.tome.module_shop_cart.base.CommonActivity;
import com.example.tome.module_shop_cart.bean.LoginBean;
import com.example.tome.module_shop_cart.net.IFlag;
import com.example.tome.module_shop_cart.net.OKHttpHelper;
import com.example.tome.module_shop_cart.net.ShopDataManager;
import com.example.tome.module_shop_cart.params.LoginParams;
import com.example.tome.module_shop_cart.widget.EditTextWithDel;
import com.example.tome.module_shop_cart.utils.BasicTool;

public class LoginActivity extends CommonActivity implements View.OnClickListener {

    @BindView(R2.id.title_back)
    TextView mTitleBack;
    @BindView(R2.id.etd_tel)
    EditTextWithDel mEtdTel;
    @BindView(R2.id.etd_pwd)
    EditTextWithDel mEtdPwd;
    @BindView(R2.id.cb_show_hide_pwd)
    CheckBox mCbShowHidePwd;
    @BindView(R2.id.btn_login)
    Button mBtnLogin;
    @BindView(R2.id.tv_service_phone_remark)
    TextView mTvServicePhoneRemark;
    @BindView(R2.id.layout_login)
    LinearLayout mLayoutLogin;

    @Override
    public int setContentLayout() {
        return R.layout.cart_activity_login;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {
        mBtnLogin.setOnClickListener(this);

        mCbShowHidePwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mCbShowHidePwd.isChecked()) {
                    mEtdPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);


                } else {
                    mEtdPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        if (BuildConfig.DEBUG) {
            //分销端
            mEtdTel.setText("17912340002");
            mEtdPwd.setText("888888");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login){
            //点击登录
           if (checkPage()){
               LoginParams loginParams = new LoginParams();
               loginParams.setAccount(mEtdTel.getText().toString());
               loginParams.setPassword(mEtdPwd.getText().toString());
               ShopDataManager.login(this, loginParams);
           }
        }
    }


    @Override
    public void onSuccess(int flag,Object baseBean) {
        super.onSuccess(flag,baseBean);
        if (flag == IFlag.FLAG_LOGIN) {
            ToastUtils.showCenter(getString(R.string.cart_login_login_success));

            BaseObjs<LoginBean> beanBaseObj = (BaseObjs<LoginBean>) baseBean;
            //保存登录信息
            BaseData.loginBean = beanBaseObj.getData();
            if ("1".equals(beanBaseObj.getData().getBusinessType())) {
                BaseData.IdentityType = BaseData.ENTERPRISE;
            } else if ("3".equals(beanBaseObj.getData().getBusinessType())) {
                BaseData.IdentityType = BaseData.DISTRIBUTOR;
            } else if ("6".equals(beanBaseObj.getData().getBusinessType())) {
                BaseData.IdentityType = BaseData.SHOPASSISTANT;
            }
            //登录设置
            OKHttpHelper.token = beanBaseObj.getData().getToken();
            BaseData.isLogin = true;

            SPUtil.put(this, IntentKV.K_TOKEN_VALUE, OKHttpHelper.token);
            SPUtil.put(this, IntentKV.K_IDENTITY_TYPE, BaseData.IdentityType);

            finish();
        }
    }

    /**
    * 校验
     *
     * @return
     */
    private boolean checkPage() {
        if (BasicTool.isEmpty(mEtdTel.getText())) {
            ToastUtils.showCenter(mEtdTel.getHint().toString());
            return false;
        } else if (BasicTool.isEmpty(mEtdTel.getText())) {
            ToastUtils.showCenter(mEtdTel.getHint().toString());
            return false;
        }
        return true;
    }
}
