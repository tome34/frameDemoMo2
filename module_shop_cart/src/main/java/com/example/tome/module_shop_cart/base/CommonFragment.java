package com.example.tome.module_shop_cart.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.tome.core.util.L;
import com.example.tome.module_shop_cart.R;
import com.example.tome.module_shop_cart.net.OKHttpHelper;

/**
 * @author tome
 * @date 2018/8/10  15:35
 * @describe ${公共逻辑}
 */
public abstract class CommonFragment extends BaseFragment implements INetResult{

    protected CommonActivity baseActivity;
    private ViewGroup titleGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        baseActivity = (CommonActivity) getActivity();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        titleGroup = contentView.findViewById(R.id.v_title_container);
        if (titleGroup != null) {
            L.d("状态栏fragment:"+titleGroup);
            setTitleMargin(titleGroup);
        }
    }

    @Override
    public void onSuccess(int flag, Object baseBean) {
        baseActivity.onSuccess(flag, baseBean);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        OKHttpHelper.cancelByTag(this);
    }

    @Override
    public void requestBefore(int flag) {
        baseActivity.requestBefore(flag);
    }

    @Override
    public void onError(String error, String code, int flag) {
        baseActivity.onError(error, code, flag);
    }

    @Override
    public void complete(int flag) {
        baseActivity.complete(flag);
    }

}
