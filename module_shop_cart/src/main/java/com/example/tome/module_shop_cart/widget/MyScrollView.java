package com.example.tome.module_shop_cart.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Chiahsin on 2016/8/14.
 */
public class MyScrollView extends ScrollView {

    boolean allowDragTop     = true; // 如果是true，则允许拖动至底部的下一页
    float   downY            = 0;
    boolean needConsumeTouch = true; // 是否需要承包touch事件，needConsumeTouch一旦被定性，则不会更改

    private OnScrollChangListener mListener;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mListener != null) {
            mListener.onScollChanged(l, t, oldl, oldt);
        }
    }


    public void setScrollListener(OnScrollChangListener mListener) {
        this.mListener = mListener;
    }


    public interface OnScrollChangListener {
        void onScollChanged(int l,int t,int oldl,int oldt);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int count = ev.getPointerCount();
        if (count > 1) {
            needConsumeTouch = true;
        } else {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                downY = ev.getRawY();
                needConsumeTouch = true; // 默认情况下，WebView内部的滚动优先，默认情况下由该WebView去消费touch事件
                allowDragTop = isAtTop();
            } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
                if (!needConsumeTouch) {
                    // 在最顶端且向上拉了，则这个touch事件交给父类去处理
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                } else if (allowDragTop) {
                    // needConsumeTouch尚未被定性，此处给其定性
                    // 允许拖动到底部的下一页，而且又向上拖动了，就将touch事件交给父view
                    if (ev.getRawY() - downY > 2) {
                        // flag设置，由父类去消费
                        needConsumeTouch = false;
                        getParent().requestDisallowInterceptTouchEvent(false);
                        return false;
                    }
                }
            }
        }

        // 通知父view是否要处理touch事件
        getParent().requestDisallowInterceptTouchEvent(needConsumeTouch);
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判断WebView是否在顶部
     *
     * @return 是否在顶部
     */
    private boolean isAtTop() {
        return getScrollY() == 0;
    }


    private OnScrollListener onScrollListener;
    /**
     * 主要是用在用户手指离开MyScrollView，MyScrollView还在继续滑动，我们用来保存Y的距离，然后做比较
     */
    private int              lastScrollY;

    /**
     * 设置滚动接口
     *
     * @param onScrollListener
     */
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    /**
     * 用于用户手指离开MyScrollView的时候获取MyScrollView滚动的Y距离，然后回调给onScroll方法中
     */
    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            int scrollY = MyScrollView.this.getScrollY();

            if (onScrollListener != null) {
                onScrollListener.onScroll(scrollY);
                onScrollListener.onScrollDistance(scrollY - lastScrollY);
                onScrollListener.onStateChange(lastScrollY != scrollY);
            }

            //此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
            if (lastScrollY != scrollY) {
                lastScrollY = scrollY;
                handler.sendMessageDelayed(handler.obtainMessage(), 5);
            }

        }

    };

    private float lastY;

    /**
     * 重写onTouchEvent， 当用户的手在MyScrollView上面的时候，
     * 直接将MyScrollView滑动的Y方向距离回调给onScroll方法中，当用户抬起手的时候，
     * MyScrollView可能还在滑动，所以当用户抬起手我们隔5毫秒给handler发送消息，在handler处理
     * MyScrollView滑动的距离
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {


        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                handler.sendMessageDelayed(handler.obtainMessage(), 20);
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = ev.getY() - lastY;
                if (onScrollListener != null && Math.abs(dy) > 20) {
                    onScrollListener.onScroll(this.getScrollY());
                    onScrollListener.onScrollDistance((int)dy);
                    onScrollListener.onStateChange(true);
                }
                break;
            case MotionEvent.ACTION_DOWN:
                lastY = ev.getY();
                handler.sendEmptyMessage(0);
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 滚动的回调接口
     */
    public interface OnScrollListener {
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         */
        void onScroll(int scrollY);

        void onStateChange(boolean isScroll);//滚动状态

        void onScrollDistance(int dy);//与上一次滚动的距离差
    }

}
