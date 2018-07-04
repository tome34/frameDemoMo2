package com.example.tome.component_base.widget.emptyView;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tome.component_base.R;

import static com.example.tome.component_base.widget.emptyView.IEmptyLayout.OnStateChangeListener.TYPE_EMPTY;
import static com.example.tome.component_base.widget.emptyView.IEmptyLayout.OnStateChangeListener.TYPE_LOADING;
import static com.example.tome.component_base.widget.emptyView.IEmptyLayout.OnStateChangeListener.TYPE_SUCCESS;


/**
 * Created by 黄俊龙 on 2016/12/2.
 */

public class EmptyLayout extends FrameLayout implements IEmptyLayout {

    private Context mContext;
    private View mEmptyView;
    private View mBindView;
    private View mErrorView;
    private View mLoadingView;
    private LoadingView loadingView;
    private ImageView emptyImage;
    private TextView emptyText;
    private String emptyContent;
    private OnStateChangeListener mOnEmptyListener;
    private int mLoadingState = TYPE_SUCCESS;


    public EmptyLayout(Context context) {
        this(context,null);
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public EmptyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        this.mContext = context;

        //数据为空时的布局
        mEmptyView = LayoutInflater.from(mContext).inflate(R.layout.layout_empty,null);
        emptyImage = (ImageView)mEmptyView.findViewById(R.id.emptyImage);
        emptyText = (TextView)mEmptyView.findViewById(R.id.emptyText);
        addView(mEmptyView);

        //加载中的布局
        mLoadingView = LayoutInflater.from(mContext).inflate(R.layout.layout_loading,null);
        loadingView = (LoadingView)mLoadingView.findViewById(R.id.loadingView);
       // LevelLoadingRenderer build = new LevelLoadingRenderer.Builder(mContext).setLevelColor(ContextCompat.getColor(mContext,R.color.colorAccent)).build();
       // loadingView.setLoadingRenderer(build);
        addView(mLoadingView);

        //网络访问失败的布局
        mErrorView = LayoutInflater.from(mContext).inflate(R.layout.layout_error,null);
        addView(mErrorView);

        //全部隐藏
        setGone();
    }

    /**
     * 全部隐藏
     */
    private void setGone() {
        mEmptyView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showEmpty() {
        if (mOnEmptyListener != null) {
            mOnEmptyListener.onChange(TYPE_EMPTY);
        }
        if (mBindView != null) {
            mBindView.setVisibility(View.GONE);
        }
        setGone();
        if (!TextUtils.isEmpty(emptyContent)) {
            setEmptyViewText(emptyContent);
        }
        mLoadingState = TYPE_EMPTY;
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSuccess() {
        if (mOnEmptyListener != null) {
            mOnEmptyListener.onChange(TYPE_SUCCESS);
        }
        setGone();
        mLoadingState = TYPE_SUCCESS;
        if (mBindView != null) {
            mBindView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError() {
        if (mOnEmptyListener != null) {
           // mOnEmptyListener.onChange(TYPE_ERROR);
        }
        if (mBindView != null) {
            mBindView.setVisibility(View.GONE);
        }
        setGone();
        mLoadingState = OnStateChangeListener.TYPE_ERROR;
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        if (mOnEmptyListener != null) {
            mOnEmptyListener.onChange(TYPE_LOADING);
        }
        if (mBindView != null) {
            mBindView.setVisibility(View.GONE);
        }
        setGone();
        mLoadingState = TYPE_LOADING;
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void bindView(View view) {
        mBindView = view;
    }

    @Override
    public void setOnStateChangeListener(OnStateChangeListener listener) {
        mOnEmptyListener = listener;
    }

    @Override
    public void setEmptyMsgAndIcon(String msg,@DrawableRes int icon) {
        emptyContent = msg;
        emptyImage.setImageResource(icon);
    }

    @Override
    public int getLoadingState() {
        return mLoadingState;
    }

    @Override
    public void setCustomView(int type,View view) {
        switch (type) {
            case TYPE_EMPTY:
                removeView(mEmptyView);
                mEmptyView = view;
                addView(mEmptyView);
                break;
            case OnStateChangeListener.TYPE_ERROR:
                removeView(mErrorView);
                mErrorView = view;
                addView(mErrorView);
                break;
            case TYPE_LOADING:
                removeView(loadingView);
                loadingView = (LoadingView)view;
                addView(loadingView);
                break;
            default:
        }
    }

    public void showEmpty(String text) {
        if (mOnEmptyListener != null) {
            mOnEmptyListener.onChange(TYPE_EMPTY);
        }
        if (text == null) {
            setEmptyViewText(TextUtils.isEmpty(emptyContent) ? getContext().getString(R.string.data_error) : emptyContent);
        } else {
            setEmptyViewText(text);
        }
        if (mBindView != null) {
            mBindView.setVisibility(View.GONE);
        }
        setGone();
        mLoadingState = TYPE_EMPTY;
        mEmptyView.setVisibility(View.VISIBLE);
    }

    public void setEmptyViewText(String text) {
        TextView tv = (TextView)mEmptyView.findViewById(R.id.emptyText);
        tv.setText(text);
    }
}