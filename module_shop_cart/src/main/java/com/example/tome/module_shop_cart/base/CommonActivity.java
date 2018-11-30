package com.example.tome.module_shop_cart.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.example.tome.core.util.L;
import com.example.tome.core.util.ToastUtils;
import com.example.tome.module_shop_cart.R;

/**
 * @author tome
 * @date 2018/8/10  10:49
 * @describe ${公共逻辑}
 */
public abstract class CommonActivity extends BaseActivity implements INetResult<Object>{
    private ViewGroup titleGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isResizeTitlebarHeight()) {
            titleGroup = (ViewGroup) findViewById(R.id.v_title_container);
            L.d("状态栏titleGroup"+titleGroup);
            if (titleGroup != null) {
                setTitleMargin(titleGroup);
            }
        }
    }

    @Override
    public void onSuccess(int flag, Object baseBean) {
        dismissHUD();
    }

    @Override
    public void onError(String error,String code,int flag) {
        if (isDestory){
            return;
        }
        ToastUtils.show(this, error + code ,2);
    }

    protected boolean isResizeTitlebarHeight() {
        return true;
    }

    public void back(View v) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
