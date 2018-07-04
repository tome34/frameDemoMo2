package com.example.tome.component_base.base.mvp;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.tome.component_base.base.mvc.inter.BaseView;
import com.example.tome.component_base.base.mvc.inter.ILoadingDialogView;
import com.example.tome.component_base.base.mvp.inter.IView;
import com.example.tome.component_base.util.ActivityUtils;
import com.example.tome.component_base.util.L;
import com.example.tome.component_base.util.NetUtils;
import com.example.tome.component_data.ServerException.ServerException;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

/**
 * @author by TOME .
 * @data on      2018/6/29 11:34
 * @describe ${TODO}
 */

public abstract class BaseVpObserver<T> extends ResourceObserver<T> {
    private String mErrorMsg;
    private IView mView;
    protected IView mDialogView = null;
    protected String msg = "正在加载中...";
    private boolean isShowError = true;
    private SmartRefreshLayout rlRefreshLayout = null;
    private Activity mCurrentActivity;

    public BaseVpObserver(String operatingStr) {
        if (operatingStr != null) {
            this.msg = operatingStr;
        } else {
            isShowError = false;
        }
    }

    public BaseVpObserver() {
    }

    public BaseVpObserver(IView view) {
        this.mView = view ;
    }

    public BaseVpObserver(SmartRefreshLayout rlRefreshLayout) {
        this.rlRefreshLayout = rlRefreshLayout;


    }

    /**
     * 执行开始（可选）
     */
    @Override
    protected void onStart() {
        super.onStart();
        if (mView != null){
            mView.showHUD(msg);
        }
        mCurrentActivity = ActivityUtils.getInstance().currentActivity();
        if (mCurrentActivity != null && !NetUtils.isNetConnected(mCurrentActivity)) {
            Toast.makeText(mCurrentActivity, "当前无网络", Toast.LENGTH_SHORT).show();
            onComplete();
        }

    }
//如果数据返回格式统一,可以再这里处理后再把成功数据返回
    // @Override
  //  public void onNext(T t) {
//        onNextSuccess(t);
//        if (t.isOk()) {
//            onNextSuccess(t);
//        } else {
//            if (!TextUtils.isEmpty(t.getMessage())) {
//                onError(new ResultException(t.getState(),t.getMessage()));
//            } else {
//                onError(new ResultException(t.getState(),msg+"失败,请检查网络或稍后重试"));
//            }
 //       }
  //  }

    @Override
    public void onComplete() {
        L.d("执行结果");

        if (mView != null) {
            mView.dismissHUD();
        }else if (mView == null && rlRefreshLayout != null){
            rlRefreshLayout.finishRefresh();
            rlRefreshLayout.finishLoadMore();
        }
    }

    @Override
    public void onError(Throwable e) {
        L.d("网络异常"+e.toString());
        String errorMsgmsg = "未知错误";
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {

        }
        if (e instanceof UnknownHostException) {
            errorMsgmsg = "网络不可用";
        } else if (e instanceof ServerException) {
            errorMsgmsg = e.toString();
        } else if (e instanceof SocketTimeoutException) {
            errorMsgmsg = "请求网络超时";
        } else {
            errorMsgmsg = "未知错误";
        }
        if (mCurrentActivity != null){
            Toast.makeText(ActivityUtils.getInstance().currentActivity(), errorMsgmsg, Toast.LENGTH_SHORT).show();
        }

        onComplete();
    }

  //  protected abstract void onNextSuccess(T t) ;

}
