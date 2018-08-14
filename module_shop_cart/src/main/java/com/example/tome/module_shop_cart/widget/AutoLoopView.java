package com.example.tome.module_shop_cart.widget;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import com.example.tome.module_shop_cart.R;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Void Young on 9/26/2015. 自动轮播视图
 */

public class AutoLoopView<ITEM> extends RelativeLayout implements LifecycleObserver {

    private ArrayList<ChangeViewBean<ITEM>> mList;
    private int lastDot = 0;
    private int autoChangeDuration = 3000;//自动轮播的延时
    private int changeDuration = 600;//手动滑动的延时
    private int                       dotSize;
    private ViewPager                 vp;
    private LinearLayout              dot_layout;
    private OnPageClickListener<ITEM> listener;
    private OnChangeListener<ITEM>    mChangeListener;
    private OnBindingData<ITEM>       onBinding;
    private Runnable autoLoopTask = new Runnable() {
        @Override
        public void run() {
            int currentItem = vp.getCurrentItem();
            vp.setCurrentItem(currentItem + 1);// 会调用到onPageScrollStateChanged方法触发跳转,所以不会下标越界
            startAutoChange();
        }
    };
    private static final String TAG = "AutoChangeView";
    private FixedSpeedScroller mScroller;
    private int itemLayoutId;
    private boolean isAutoScrolling = false;
    private boolean isInitOK = false;
    private boolean mIsVisibleToUser = true;
    private int selectDotRes;
    private int noSelectDotRes;

    public boolean isAutoScrolling() {
        return isAutoScrolling;
    }

    public AutoLoopView(Context context) {
        this(context, null);
    }

    public AutoLoopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cart_view_auto_poll_image, this, true);
        vp = (ViewPager) findViewById(R.id.vp);
        dot_layout = (LinearLayout) findViewById(R.id.ll_dot_layout);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.cart_AutoLoopView);//AutoLoopView_cart_dot_size
        dotSize = attributes.getDimensionPixelSize(R.styleable.cart_AutoLoopView_cart_dot_size, 5);
        autoChangeDuration = attributes.getInt(R.styleable.cart_AutoLoopView_cart_autoChangeDuration, autoChangeDuration);
        changeDuration = attributes.getInteger(R.styleable.cart_AutoLoopView_cart_changeDuration, changeDuration);
        selectDotRes = attributes.getResourceId(R.styleable.cart_AutoLoopView_cart_selectedDotRes, R.mipmap.dian_blue);
        noSelectDotRes = attributes.getResourceId(R.styleable.cart_AutoLoopView_cart_noSelectedDotRes, R.mipmap.dian_grey);
        int dotLayoutGravity = attributes.getInteger(R.styleable.cart_AutoLoopView_cart_dotLayoutGravity, 1);
        ViewGroup.LayoutParams layoutParams = dot_layout.getLayoutParams();
        LayoutParams params = new LayoutParams(layoutParams);
        params.addRule(ALIGN_PARENT_BOTTOM);
        switch (dotLayoutGravity) {
            case 0:
                params.addRule(ALIGN_PARENT_LEFT);
                break;
            case 2:
                params.addRule(ALIGN_PARENT_RIGHT);
                break;
            default:
                params.addRule(CENTER_HORIZONTAL);
        }
        dot_layout.setLayoutParams(params);
        // 获取dot从dp转化为pixel后的大小
        attributes.recycle();
        // setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");//反射实现控制滑动速度
            mField.setAccessible(true);
            mScroller = new FixedSpeedScroller(vp.getContext(), new AccelerateInterpolator());
            mField.set(vp, mScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startAutoChange() {
        postDelayed(autoLoopTask, autoChangeDuration);
    }

    public void startAutoScroll() {
        if (!isAutoScrolling && isInitOK) {
            isAutoScrolling = true;
            mScroller.setmDuration(changeDuration);
            startAutoChange();
        }
    }

    public void stopAutoScroll() {
        if (isAutoScrolling) {
            isAutoScrolling = false;
            removeCallbacks(autoLoopTask);
        }
    }

    public void setDotSize(int dotSize) {
        this.dotSize = dotSize;
    }

    /**
     * 先设置{@link #setOnPageClickListener(OnPageClickListener)}再putdata,或者使用{@link #putData(LifecycleOwner, int, List, OnPageClickListener, OnBindingData)} 否则可能无法点击
     */
    public void putData(LifecycleOwner owner,@LayoutRes int itemLayoutId, List<ITEM> datas, OnBindingData<ITEM> bindingData) {// 先不执行全部的构造方法,把這下面本来放在构造方法内执行的方法,
        // 放到获取到datas之后再执行来达到目的
        if (datas == null || datas.size() < 1) {
            throw new IllegalArgumentException("mList not null or empty");
        }
        owner.getLifecycle().addObserver(this);
        mList = new ArrayList<>(datas.size());
        for (ITEM data : datas) {
            ChangeViewBean<ITEM> viewBean = new ChangeViewBean<>(data);
            mList.add(viewBean);
        }
        this.itemLayoutId = itemLayoutId;
        this.onBinding = bindingData;
        initView();
        initEvent();
        isInitOK = true;
    }


    /**
     * DESC : 推入datas 和ClickListener . <br/>
     *
     * @param listener 自定义的ClickListener 传入后设置给ImageView 达到监听点击事件的目的
     */
    public void putData(LifecycleOwner owner,@LayoutRes int itemLayoutId, List<ITEM> datas, OnPageClickListener<ITEM> listener, OnBindingData<ITEM> bindingData) {
        this.listener = listener;
        putData(owner,itemLayoutId, datas, bindingData);
    }

    public OnPageClickListener<ITEM> getOnPageClickListener() {
        return listener;
    }

    public void setOnPageClickListener(OnPageClickListener<ITEM> listener) {
        this.listener = listener;
    }

    public OnChangeListener<ITEM> getChangeListener() {
        return mChangeListener;
    }

    public void setChangeListener(OnChangeListener<ITEM> changeListener) {
        mChangeListener = changeListener;
    }

    private void initView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dotSize, dotSize);// 通过params设置xml中layout开头的属性
        dot_layout.removeAllViews();
        for (int i = 0; i < mList.size(); i++) {
            if (i != mList.size() - 1) {// 最后一个跳过不设置margin
                params.rightMargin = 10;
            }
            View dot = new View(getContext());
            dot.setBackgroundResource(noSelectDotRes);
            dot.setLayoutParams(params);
            dot_layout.addView(dot);
            mList.get(i).dot = dot;
        }
    }

    private void initEvent() {
        MyPageAdapter adapter = new MyPageAdapter();
        vp.setAdapter(adapter);
        vp.setOnPageChangeListener(new MyOnPagerChangerListener());
        vp.setCurrentItem(1);// 设置当前item为实现了无极滚动的第一个item 就是原来的0
        ChangeViewBean<ITEM> bean = mList.get(0);

        bean.dot.setBackgroundResource(selectDotRes);// 需要在设置adapter后设置才有效
    }

    /**
     * 自动轮播的速度
     */
    public void setAutoChangeDuration(int autoChangeDuration) {
        this.autoChangeDuration = autoChangeDuration;
    }

    /**
     * 修改页面过渡的速度
     */
    public void setChangeSpeed(int duration) {
        changeDuration = duration;
    }

    private class MyOnPagerChangerListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {// 拖动中调用
            // LogUtils.w("void", "onPageScrolled: ");
        }

        @Override
        public void onPageSelected(int position) {// 被选择后调用,就是出现后调用
            position = getBeanPosition(position);
            if (position == lastDot) {// 如果选中点的位置跟上次选中点是同一个位置，不做处理
                return;
            }
            ChangeViewBean<ITEM> bean = mList.get(position);
            bean.dot.setBackgroundResource(selectDotRes);
            mList.get(lastDot).dot.setBackgroundResource(noSelectDotRes);
            if (mChangeListener != null) {
                mChangeListener.onPageChanged(bean, position);
            }
            lastDot = position;// 更新上一个dot的位置
        }

        /**
         * DESC : move to 头尾时进行跳转 模拟实现无限滚动<br/>
         *
         * @see ViewPager.OnPageChangeListener#onPageScrollStateChanged(int)
         */
        @Override
        public void onPageScrollStateChanged(int state) {// 当拖动状态改变时调用,有空闲0 拖动中1 放开后设置中2 三种状态
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                int currentItem = vp.getCurrentItem();
                if (currentItem == 0 || currentItem == mList.size() + 1) {
                    int moveToPosition = getBeanPosition(currentItem) + 1;// 将要跳转到的位置 因为前面加了一个过渡item
                    // 所以正好是bean的位置+1
                    vp.setCurrentItem(moveToPosition, false);// 直接跳转 会有闪烁
                }
            }
        }
    }

    private class MyPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mList.size() + 2;// 添加头尾实现"无限"滚动,
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {// 当返回true的时候才认为这个view是自己的才会显示出来
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {// 不要在這里设置title 会混乱
            // position = position % mList.size();// 获取datas范围内的position
            position = getBeanPosition(position);
            View view = LayoutInflater.from(getContext()).inflate(itemLayoutId, container, false);
            final ChangeViewBean<ITEM> bean = mList.get(position);
            if (onBinding != null) {
                view = onBinding.binding(view, bean.data);
            }
            container.addView(view);
            if (listener != null) {
                final int finalPosition = position;
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.OnPageClick(bean.data, finalPosition);
                    }
                });
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {// 超过缓存大小,需要destroy一个ITEM的时候被调用,必须覆写
            container.removeView((View) object);
        }
    }

    /**
     * DESC : 获取对应的真正的bean的position . <br/>
     */
    private int getBeanPosition(int position) {
        position = (position - 1) % mList.size();
        if (position < 0) {
            position = mList.size() - 1;
        }
        return position;
    }

    public ChangeViewBean<ITEM> getCurrentBean() {
        int currentItem = vp.getCurrentItem();
        return mList.get(getBeanPosition(currentItem));
    }

    /**
     * DESC : 拦截左右滑动事件为己所用<br/>
     *
     * @see ViewGroup#onInterceptTouchEvent(MotionEvent)
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float dx = 0;
        float dy = 0;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dx = ev.getX();
                dy = ev.getY();
                stopAutoScroll();
                break;
            case MotionEvent.ACTION_MOVE:
                mScroller.setmDuration(200);
                float mx = getX();
                float my = getY();
                float resultX = Math.abs(dx - mx);
                float resultY = Math.abs(dy - my);
                if (resultX > resultY) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                startAutoScroll();
                break;
            default:
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        requestDisallowInterceptTouchEvent(true);// 请求父容器不要拦截我的touch事件
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(autoLoopTask);
    }

    /**
     * ClassName: OnPageClickListener <br/>
     * Function: 外传图片的点击事件 实现控件的点击响应. <br/>
     * date: Oct 6, 2015 9:08:25 PM <br/>
     *
     * @author Void Young
     * @version AutoChangePollImageView
     */
    @FunctionalInterface
    public interface OnPageClickListener<ITEM> {
        void OnPageClick(ITEM bean,int pos);
    }

    /**
     * 绑定数据后原路返回
     */
    @FunctionalInterface
    public interface OnBindingData<ITEM> {
        View binding(View view,ITEM bean);
    }

    /**
     * 切换监听器
     */
    @FunctionalInterface
    public interface OnChangeListener<ITEM> {
        void onPageChanged(ChangeViewBean<ITEM> bean,int position);
    }

    public static class FixedSpeedScroller extends Scroller {
        private int mDuration = 1000;

        public FixedSpeedScroller(Context context) {
            super(context);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        public void setmDuration(int time) {
            mDuration = time;
        }

        public int getmDuration() {
            return mDuration;
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        if (mIsVisibleToUser) {
            startAutoScroll();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        stopAutoScroll();
    }

    public void setVisibleToUser(boolean visibleToUser) {
        mIsVisibleToUser = visibleToUser;
    }


    public static class ChangeViewBean<ITEM> {
        public ITEM data;
        public View dot;

        public ChangeViewBean(ITEM data) {
            this.data = data;
        }
    }
}
