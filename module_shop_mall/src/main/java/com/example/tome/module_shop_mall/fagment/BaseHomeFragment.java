package com.example.tome.module_shop_mall.fagment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tome.component_base.base.BaseMVPFragment;
import com.example.tome.component_base.base.inter.AbstractPresenter;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.contract.KnowledgeSystemContract;

/**
 * Created by bruce on 2016/11/1.
 * BaseHomeFragment
 */

public class BaseHomeFragment extends BaseMVPFragment {

    public static BaseHomeFragment newInstance(String info) {
        Bundle args = new Bundle();
        BaseHomeFragment fragment = new BaseHomeFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected AbstractPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.mall_fragment_base;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
       // super.regEvent = true;

    }
}
