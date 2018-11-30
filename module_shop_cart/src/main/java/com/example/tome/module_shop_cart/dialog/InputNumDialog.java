package com.example.tome.module_shop_cart.dialog;

import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.OnClick;
import com.example.tome.core.util.L;
import com.example.tome.core.util.ToastUtils;
import com.example.tome.module_shop_cart.R;
import com.example.tome.module_shop_cart.base.BaseDialogFragment;
import com.example.tome.module_shop_cart.utils.DetectTool;
import com.example.tome.module_shop_cart.widget.CustomNumInputView;

/**
 * 类描述    :解决输入框  直接输入无法控制键盘
 * 包名      : com.fecmobile.jiubeimember.ui.dialog.shop
 * 类名称    : InputNumDialog
 * 创建人    : ghy
 * 创建时间  : 2017/5/31 14:28
 * 修改人    :
 * 修改时间  :
 * 修改备注  :
 */
public class InputNumDialog extends BaseDialogFragment implements View.OnClickListener {
    CustomNumInputView cnivCarNum;
    TextView btnNeg;
    TextView btnPos;
    private ConfirmLisener confirmLisener;
    private int current;
    private int max;
    private int min;
    private int stepSize;

    public void setStepSize(int stepSize) {
        this.stepSize = stepSize;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setConfirmLisener(ConfirmLisener confirmLisener) {
        this.confirmLisener = confirmLisener;
    }


    @Override
    protected ViewGroup.LayoutParams setLayoutParams() {
        return new FrameLayout.LayoutParams((int) (DetectTool.getResolutionX(getContext()) * 0.6), LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {
        btnPos = (TextView) view.findViewById(R.id.btn_pos);
        btnNeg = (TextView) view.findViewById(R.id.btn_neg);
        cnivCarNum = (CustomNumInputView) view.findViewById(R.id.cniv_car_num);
        L.i("setMinVal：" + min);
        L.i("setMax：" + max);
        L.i("setCurentVal：" + current);


        cnivCarNum.setCurentVal(current);
        cnivCarNum.setStepSize(stepSize);
        cnivCarNum.setMinVal(min);
        cnivCarNum.setMax(max);

        btnPos.setOnClickListener(this);
        btnNeg.setOnClickListener(this);
    }



    @Override
    public int setContentLayout() {
        return R.layout.cart_dialog_input_num;
    }


    @OnClick
    public void onClick(View view) {
        if (view.getId() == R.id.btn_neg) {
            dismiss();
        } else if (view.getId() == R.id.btn_pos) {
            if (confirmLisener != null) {
                int val = cnivCarNum.getCurentVal();
                if (val > max) {
                    ToastUtils.showCenter("最大数量：" + max);
                    return;
                } else if (val < min) {
                    ToastUtils.showCenter("最低数量：" + min);
                    return;
                }

                if (val > 0) {
                    if (stepSize > 0) {
                        val = val - (val % stepSize);
                    }
                    confirmLisener.confirm(dialog, val);
                } else {
                    dismiss();
                }
            }
        }
    }

    public interface ConfirmLisener {
        void confirm(Dialog dialog,int num);
    }
}
