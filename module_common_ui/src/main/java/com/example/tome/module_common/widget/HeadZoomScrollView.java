package com.example.tome.module_common.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.example.tome.core.util.L;

/**
 * 头部拉伸效果
 */
public class HeadZoomScrollView extends ScrollView {

    private static final int MSG_REST_POSITION = 0x01;
    /** The max scroll height. */
    private static final int MAX_SCROLL_HEIGHT = 400;
    /** Damping, the smaller the greater the resistance */
    private static final float SCROLL_RATIO = 0.4f;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (MSG_REST_POSITION == msg.what) {
                if (mScrollY != 0 && mTouchStop) {
                    mScrollY -= mScrollDy;

                    if ((mScrollDy < 0 && mScrollY > 0) || (mScrollDy > 0 && mScrollY < 0)) {
                        mScrollY = 0;
                    }

                    zoomView.scrollTo(0, mScrollY);
                    // continue scroll after 20ms
                    sendEmptyMessageDelayed(MSG_REST_POSITION, 20);
                }
            }
        }
    };

    public HeadZoomScrollView(Context context) {
        super(context);
    }

    public HeadZoomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeadZoomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //    用于记录下拉位置
    private float y = 0f;
    //    zoomView原本的宽高
    private int zoomViewWidth = 0;
    private int zoomViewHeight = 0;

    //    是否正在放大
    private boolean mScaling = false;

    //    放大的view，默认为第一个子view
    private View zoomView;
    public void setZoomView(View zoomView) {
        this.zoomView = zoomView;
    }

    //    滑动放大系数，系数越大，滑动时放大程度越大
    private float mScaleRatio = 0.4f;
    public void setmScaleRatio(float mScaleRatio) {
        this.mScaleRatio = mScaleRatio;
    }

    //    最大的放大倍数
    private float mScaleTimes = 2f;
    public void setmScaleTimes(int mScaleTimes) {
        this.mScaleTimes = mScaleTimes;
    }

    //    回弹时间系数，系数越小，回弹越快
    private float mReplyRatio = 0.9f;
    public void setmReplyRatio(float mReplyRatio) {
        this.mReplyRatio = mReplyRatio;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        不可过度滚动，否则上移后下拉会出现部分空白的情况
        //滑到边界后继续滑动也不会出现弧形光晕
        setOverScrollMode(OVER_SCROLL_NEVER);
//        获得默认第一个view
        if (getChildAt(0) != null && getChildAt(0) instanceof ViewGroup && zoomView == null) {

            ViewGroup vg = (ViewGroup) getChildAt(0);
            L.d("1滑动:"+"getChildAt:"+getChildAt(0) +","+vg.getChildCount());
            if (vg.getChildCount() > 0) {
                zoomView = vg.getChildAt(0);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (zoomViewWidth <= 0 || zoomViewHeight <=0) {
            zoomViewWidth = zoomView.getMeasuredWidth();
            zoomViewHeight = zoomView.getMeasuredHeight();
        }
        if (zoomView == null || zoomViewWidth <= 0 || zoomViewHeight <= 0) {
            return super.onTouchEvent(ev);
        }
        L.d("2滑动:"+"Width:"+zoomViewWidth +",Height:"+zoomViewHeight+","+getScrollY() +","+ ev.getY());

        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (!mScaling) {
                    if (getScrollY() == 0) {
                        //滑动到顶部时，记录位置
                        y = ev.getY();
                    } else {
                        break;
                    }
                }
                int distance = (int) ((ev.getY() - y)*mScaleRatio);
                //若往下滑动
                if (distance < 0){
                    break;
                }
                mScaling = true;
                L.d("3滑动:"+"y:"+y +",distance:"+distance);
                //move(ev);
                setZoom(distance);
                return true;
            case MotionEvent.ACTION_UP:
                mScaling = false;
               // moveUp();
                replyView();
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**放大view*/
    private void setZoom(float s) {
        float scaleTimes = (float) ((zoomViewWidth+s)/(zoomViewWidth*1.0));
        L.d("4滑动:"+"zoomViewWidth:"+zoomViewWidth +",scaleTimes:"+scaleTimes);
//        如超过最大放大倍数，直接返回
        if (scaleTimes > mScaleTimes){
            return;
        }

        ViewGroup.LayoutParams layoutParams = zoomView.getLayoutParams();
        layoutParams.width = (int) (zoomViewWidth + s);
        layoutParams.height = (int)(zoomViewHeight*((zoomViewWidth+s)/zoomViewWidth));
//        设置控件水平居中
        ((MarginLayoutParams) layoutParams).setMargins(-(layoutParams.width - zoomViewWidth) / 2, 0, 0, 0);
        zoomView.setLayoutParams(layoutParams);
    }

    /**回弹*/
    private void replyView() {
        final float distance = zoomView.getMeasuredWidth() - zoomViewWidth;
        L.d("5滑动:"+"Width:"+zoomView.getMeasuredWidth() +",distance:"+distance);
        // 设置动画
        ValueAnimator anim = ObjectAnimator.ofFloat(distance, 0.0F).setDuration((long) (distance * mReplyRatio));
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setZoom((Float) animation.getAnimatedValue());
            }
        });
        anim.start();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener!=null){
            onScrollListener.onScroll(l,t,oldl,oldt);
        }
    }

    private OnScrollListener onScrollListener;

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    /**滑动监听*/
    public  interface OnScrollListener{
        void onScroll(int scrollX,int scrollY,int oldScrollX,int oldScrollY);
    }

    private float mTouchY;
    private boolean mTouchStop = false;

    private int mScrollY = 0;
    private int mScrollDy = 0;

    private void moveUp(){
        mScrollY = zoomView.getScrollY();
        if (mScrollY != 0) {
            mTouchStop = true;
            mScrollDy = (int) (mScrollY / 10.0f);
            mHandler.sendEmptyMessage(MSG_REST_POSITION);
        }
    }

    private void move(MotionEvent ev){
        float nowY = ev.getY();
        int deltaY = (int) (mTouchY - nowY);
        mTouchY = nowY;
        if (isNeedMove()) {
            int offset = zoomView.getScrollY();
            if (offset < MAX_SCROLL_HEIGHT && offset > -MAX_SCROLL_HEIGHT) {
                zoomView.scrollBy(0, (int) (deltaY * SCROLL_RATIO));
                mTouchStop = false;
            }
        }
    }

    private boolean isNeedMove() {
        int viewHeight = zoomView.getMeasuredHeight();
        int scrollHeight = getHeight();
        int offset = viewHeight - scrollHeight;
        int scrollY = getScrollY();

        return scrollY == 0 || scrollY == offset;
    }
}
