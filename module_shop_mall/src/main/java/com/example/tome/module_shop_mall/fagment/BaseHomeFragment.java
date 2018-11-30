package com.example.tome.module_shop_mall.fagment;

import android.os.Bundle;
import com.example.tome.core.base.mvc.BaseVcFragment;
import com.example.tome.module_shop_mall.R;

/**
 * Created by bruce on 2016/11/1.
 * BaseHomeFragment
 */

public class BaseHomeFragment extends BaseVcFragment {

    public static BaseHomeFragment newInstance(String info) {
        Bundle args = new Bundle();
        BaseHomeFragment fragment = new BaseHomeFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
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
