package com.example.tome.module_shop_cart.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.tome.core.util.L;
import com.example.tome.core.base.BaseEventbusBean;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author tome
 * @date 2018/8/10  15:30
 * @describe ${Fragment基类}
 */
public abstract class BaseFragment extends Fragment implements IUIBase{

    protected Context mContext;
    private Unbinder unbinder;
    protected boolean regEvent;
    protected View contentView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context ;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(setContentLayout(), container, false);
        ButterKnife.bind(this, contentView);
        initTitle();
        initView();

        if (regEvent) {
            EventBus.getDefault().register(this);
        }
        return contentView;
    }

    public void setTitleMargin(View view) {
        BaseActivity activity = (BaseActivity) getActivity();
        activity.setTitleMargin(view);
    }

    @Override
    public void showHUD() {
        if (getActivity() != null) {
            BaseActivity baseActivity = (BaseActivity) getActivity();
            baseActivity.showHUD();
        }
    }

    @Override
    public void dismissHUD() {
        if (getActivity() != null) {
            BaseActivity baseActivity = (BaseActivity) getActivity();
            baseActivity.dismissHUD();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        L.i("当前运行的类：" + getClass().getName());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEventbusBean event) {
        onEvent(event);
    }

    protected void onEvent(BaseEventbusBean event) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (regEvent) {
            EventBus.getDefault().unregister(this);
        }
    }
}
