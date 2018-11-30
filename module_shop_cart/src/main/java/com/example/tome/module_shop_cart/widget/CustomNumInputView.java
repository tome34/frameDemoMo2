package com.example.tome.module_shop_cart.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.tome.core.util.L;
import com.example.tome.core.util.ToastUtils;
import com.example.tome.module_shop_cart.R;
import com.example.tome.module_shop_cart.base.BaseActivity;
import com.example.tome.module_shop_cart.dialog.InputNumDialog;

/**
 * 数字输入框
 * Created by gonghuiyuan on 2016/9/18.
 * contact:gonghuiyuan516@qq.com
 */
public class CustomNumInputView extends LinearLayout implements View.OnClickListener, TextWatcher, InputNumDialog.ConfirmLisener {
    private ImageView ivPartivipateCrowdfundignminue;
    private ImageView ivPartivipateCrowdfundignAdd;
    private EditText tvCrowdfundingMoq;
    private CustomNumInputTextChange customNumInputTextChange;
    private InputNumDialog inputNumDialog;
    private int minVal = 1;
    private int curentVal = 1;
    private int max = 1;
    private int stepSize = 1;
    private BaseActivity activity;
    private boolean isAuto;

    public void setAllowInputNum(BaseActivity baseActivity) {
        this.activity = baseActivity;
    }

    public void setStepSize(int stepSize) {
        this.stepSize = stepSize;
    }

    public void setMax(long max) {
        this.max = (int) max;
    }

    public void setFoc(){
        ivPartivipateCrowdfundignminue.setFocusable(true);
        ivPartivipateCrowdfundignAdd.setFocusable(true);
        ivPartivipateCrowdfundignminue.setClickable(true);
        ivPartivipateCrowdfundignAdd.setClickable(true);
    }

    public void setMax(String max) {
        try {
            this.max = Integer.parseInt(max);
        } catch (Exception ex) {
            L.i(ex.toString());
        }
        this.max = 1;
    }

    public int getMax() {
        return max;
    }

    public void setCurentVal(int curentVal) {
        this.curentVal = curentVal;
        L.i("setCurentVal:" + curentVal);
        tvCrowdfundingMoq.setText(curentVal + "");
    }

    public int getCurentVal() {
        int c;
        try {
            c = Integer.parseInt(tvCrowdfundingMoq.getText() + "");
        } catch (Exception e) {
            c = 0;
        }
        return this.curentVal = c;
    }

    public int getMinVal() {
        return minVal;
    }

    public void setMinVal(int minVal) {
        this.minVal = minVal;
    }

    public CustomNumInputView(Context context) {
        this(context, null);
    }

    public CustomNumInputView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomNumInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.cart_layout_custom_num_input, this);
        ivPartivipateCrowdfundignminue = (ImageView) findViewById(R.id.iv_partivipate_crowd_fundign_minue);
        tvCrowdfundingMoq = (EditText) findViewById(R.id.tv_crowd_funding_moq);
        ivPartivipateCrowdfundignAdd = (ImageView) findViewById(R.id.iv_partivipate_crowd_fundign_add);

        ivPartivipateCrowdfundignminue.setOnClickListener(this);
        ivPartivipateCrowdfundignAdd.setOnClickListener(this);
        tvCrowdfundingMoq.setOnClickListener(this);
        tvCrowdfundingMoq.addTextChangedListener(this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.cart_CustomNumInputView);
        boolean focus = typedArray.getBoolean(R.styleable.cart_CustomNumInputView_cart_isFocus, false);
        tvCrowdfundingMoq.setFocusable(focus);
    }

    public void setCustomNumInputTextChange(CustomNumInputTextChange customNumInputTextChange) {
        this.customNumInputTextChange = customNumInputTextChange;
    }

    public int getNum() {
        if (TextUtils.isEmpty(tvCrowdfundingMoq.getText())) {
            return 0;
        }
        String num = tvCrowdfundingMoq.getText().toString();
        return Integer.parseInt(num);
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
                    customNumInputTextChange.textChange(this, moqNum);
                }
                tvCrowdfundingMoq.setText(moqNum + "");
            }else {
                ToastUtils.showShort(getContext(),"库存不足");
            }
        } else if (moqNum > minVal) {
            moqNum -= stepSize;
            if (customNumInputTextChange != null) {
                customNumInputTextChange.textChange(this, moqNum);
            }
            tvCrowdfundingMoq.setText(moqNum + "");
        }

    }

    @Override
    public void onClick(View v) {
        L.i("onClick：" + v);

        if (v.getId() == R.id.iv_partivipate_crowd_fundign_minue) {
            orderNum(0);
        } else if (v.getId() == R.id.iv_partivipate_crowd_fundign_add) {
            orderNum(1);
        } else if (v.getId() == R.id.tv_crowd_funding_moq) {
            if (activity != null) {
                if (inputNumDialog == null) {
                    isAuto = true;
                    inputNumDialog = new InputNumDialog();
                    inputNumDialog.setConfirmLisener(this);
                }
                curentVal = Integer.parseInt(tvCrowdfundingMoq.getText() + "");
                inputNumDialog.setCurrent(curentVal);
                inputNumDialog.setStepSize(stepSize);
                inputNumDialog.setMax(max);
                inputNumDialog.setMin(minVal);
                inputNumDialog.show(activity.getSupportFragmentManager(), "inputNumDialog");

                L.i(" inputNumDialog.setCurrent：" + curentVal);
                L.i(" inputNumDialog.setMax：" + max);
                L.i(" inputNumDialog.setMin：" + minVal);
            }
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        L.i("onTextChanged：" + s);
        if (!isAuto) {
            return;
        }

        int moqNum = 1;
        if (!TextUtils.isEmpty(tvCrowdfundingMoq.getText())) {
            String moqStr = tvCrowdfundingMoq.getText() + "";
            moqNum = Integer.parseInt(moqStr);
            if (moqNum < minVal) {
                moqStr = minVal + "";
                ToastUtils.showCenter("最小数量：" + minVal);
                tvCrowdfundingMoq.setText(moqStr);
            } else if (moqNum > max) {
                moqStr = max + "";
                ToastUtils.showCenter("最大数量：" + max);
                tvCrowdfundingMoq.setText(moqStr);
            }

            tvCrowdfundingMoq.setSelection(tvCrowdfundingMoq.getText().length());
        }
        curentVal = moqNum;
        if (customNumInputTextChange != null)
            customNumInputTextChange.textChange(this, moqNum);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void confirm(Dialog dialog, int num) {
        setCurentVal(num);
        dialog.dismiss();
    }

    public interface CustomNumInputTextChange {
        void textChange(View v,int num);
    }
}
