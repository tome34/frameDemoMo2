package com.example.tome.module_shop_mall.widget;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Administrator on 2016/7/2.
 */
public class RotateAnimation extends Animation {

    /** 值为true时可明确查看动画的旋转方向。 */
    public static final boolean DEBUG = false;
    /** 沿Y轴正方向看，数值减1时动画逆时针旋转。 */
    public static final boolean ROTATE_DECREASE = true;
    /** 沿Y轴正方向看，数值减1时动画顺时针旋转。 */
    public static final boolean ROTATE_INCREASE = false;
    /** Z轴上最大深度。 */
    public static final float DEPTH_Z = 0.0f;
    /** 动画显示时长。 */
    public static final long DURATION = 3000;
    /** 图片翻转类型。 */
    private final boolean type;
    private final float centerX;
    private final float centerY;
    private Camera camera;
    /** 用于监听动画进度。当值过半时需更新txtNumber的内容。 */
    private InterpolatedTimeListener listener;

    public RotateAnimation(float cX, float cY, boolean type) {
        centerX = cX;
        centerY = cY;
        this.type = type;
        setDuration(DURATION);
    }

    /**
     *
     * @param width
     * @param height
     * @param parentWidth
     * @param parentHeight
     */
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        // 在构造函数之后、getTransformation()之前调用本方法。
        super.initialize(width, height, parentWidth, parentHeight);
        camera = new Camera();
    }

    public void setInterpolatedTimeListener(InterpolatedTimeListener listener) {
        this.listener = listener;
    }
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        // interpolatedTime:动画进度值，范围为[0.0f,10.f]
        if (listener != null) {
            listener.interpolatedTime(interpolatedTime);
        }
        float from = 0.0f, to = 0.0f;
        if (type == ROTATE_DECREASE) {
            from = 0.0f;
            to = 180.0f;
        } else if (type == ROTATE_INCREASE) {
            from = 360.0f;
            to = 180.0f;
        }
        float degree = from + (to - from) * interpolatedTime;
        boolean overHalf = (interpolatedTime > 0.5f);
        if (overHalf) {
            // 翻转过半的情况下，为保证数字仍为可读的文字而非镜面效果的文字，需翻转180度。
            degree = degree - 180;
        }
        // float depth = 0.0f;
        float depth = (0.5f - Math.abs(interpolatedTime - 0.5f)) * DEPTH_Z;
        final Matrix matrix = transformation.getMatrix();
        camera.save();
        camera.translate(0.0f, 0.0f, depth);
        camera.rotateY(degree);
        camera.getMatrix(matrix);
        camera.restore();
        if (DEBUG) {
            if (overHalf) {
                matrix.preTranslate(-centerX * 2, -centerY);
                matrix.postTranslate(centerX * 2, centerY);
            }
        } else {
            //确保图片的翻转过程一直处于组件的中心点位置
            matrix.preTranslate(-centerX, -centerY);
            matrix.postTranslate(centerX, centerY);
        }
    }



    /** 动画进度监听器。 */
    public static interface InterpolatedTimeListener {
        public void interpolatedTime(float interpolatedTime);
    }
}
