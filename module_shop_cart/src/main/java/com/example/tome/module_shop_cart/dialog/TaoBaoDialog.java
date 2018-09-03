package com.example.tome.module_shop_cart.dialog;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import com.example.tome.module_shop_cart.annotation.AnimType;
import com.example.tome.module_shop_cart.annotation.anim.BaseAnimatorSet;
import com.example.tome.module_shop_cart.R;

/**
 * 仿淘宝商品参数
 */
public abstract class TaoBaoDialog<D extends TaoBaoDialog> extends BaseDialog<D> {
    private BaseAnimatorSet inAnimSet;
    private BaseAnimatorSet outAnimSet;
    private View rootView;
    protected int duration;
    protected int backgroundColor;
    private View parentView;

    public TaoBaoDialog(Context context, View rootView, int theme) {
        super(context, theme);
        this.rootView = rootView;
        initView();
    }

    protected void initView() {
        duration = 300;
        backgroundColor = Color.parseColor("#111111");
        parentView = (View) rootView.getParent();
        parentView.setBackgroundColor(backgroundColor);
        inAnimSet = new WindowsInAs();
        outAnimSet = new WindowsOutAs();
    }

    /**
     * 显示对话框，强制转换对话框的动画类型
     */
    @Override
    public void show() {
        show(AnimType.BOTTOM_2_TOP);
    }

    /**
     * 显示对话框，强制转换对话框的动画类型
     *
     * @param animType
     */
    public void show(@AnimType int animType) {
        setGravity(Gravity.BOTTOM);
        getWindow().setWindowAnimations(R.style.cart_alex_dialog_anim_bottom2top);
        parentView.setBackgroundColor(backgroundColor);
        if (rootView != null && inAnimSet != null) {
            inAnimSet.duration(duration).playOn(rootView);
        }
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                parentView.setBackgroundColor(0);
            }
        }.sendEmptyMessageDelayed(100, duration);
        if (rootView != null && outAnimSet != null) {
            outAnimSet.duration(duration).playOn(rootView);
        }
    }

    private final class WindowsInAs extends BaseAnimatorSet {
        @Override
        public void setAnimation(View view) {
            ObjectAnimator rotationX = ObjectAnimator.ofFloat(view, "rotationX", 10, 0f).setDuration(150);
            rotationX.setStartDelay(200);
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.95f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.95f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "rotationX", 0f, 10).setDuration(200),
                    rotationX
            );
//            ObjectAnimator rotationX = ObjectAnimator.ofFloat(view, "rotationX", 10, 0f).setDuration(150);
//            rotationX.setStartDelay(200);
//            animatorSet.playTogether(
//                    ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.95f).setDuration(350),
//                    ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.95f).setDuration(350),
//                    ObjectAnimator.ofFloat(view, "rotationX", 0f, 10).setDuration(200),
//                    rotationX,
//                    ObjectAnimator.ofFloat(view, "translationY", 0, -0.1f * displayMetrics.heightPixels).setDuration(350)
//            );
        }
    }

    private final class WindowsOutAs extends BaseAnimatorSet {
        @Override
        public void setAnimation(View view) {
            ObjectAnimator rotationX = ObjectAnimator.ofFloat(view, "rotationX", 10, 0f).setDuration(150);
            rotationX.setStartDelay(200);
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(view, "scaleX", 0.95f, 1.0f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "scaleY", 0.95f, 1.0f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "rotationX", 0f, 10).setDuration(200),
                    rotationX
            );
//            ObjectAnimator rotationX = ObjectAnimator.ofFloat(view, "rotationX", 10, 0f).setDuration(150);
//            rotationX.setStartDelay(200);
//            animatorSet.playTogether(
//                    ObjectAnimator.ofFloat(view, "scaleX", 0.95f, 1.0f).setDuration(350),
//                    ObjectAnimator.ofFloat(view, "scaleY", 0.95f, 1.0f).setDuration(350),
//                    ObjectAnimator.ofFloat(view, "rotationX", 0f, 10).setDuration(200),
//                    rotationX,
//                    ObjectAnimator.ofFloat(view, "translationY", -0.1f * displayMetrics.heightPixels, 0).setDuration(350)
//            );
        }
    }
}
