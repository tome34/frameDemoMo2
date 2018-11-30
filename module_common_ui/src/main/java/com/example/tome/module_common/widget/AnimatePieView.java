package com.example.tome.module_common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.example.tome.core.util.LogUtil;

/**
 * @author tome
 * @date 2018/10/22  17:10
 * @describe ${TODO}
 */
public class AnimatePieView extends View {

    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private int mLastx;
    private int mLasty;

    public AnimatePieView(Context context) {
        this(context, null);
    }

    public AnimatePieView(Context context,@Nullable AttributeSet attrs) {
        this(context,attrs, 0);
    }

    public AnimatePieView(Context context,@Nullable AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(80);
        paint1.setColor(Color.RED);

        paint2 = new Paint(paint1);
        paint2.setColor(Color.GREEN);

        paint3 = new Paint(paint1);
        paint3.setColor(Color.BLUE);

    }
    RectF mDrawRectf = new RectF();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getWidth()/2;
        float height = getHeight() - getPaddingTop() - getPaddingBottom();
        canvas.translate(width/2 , height/2);
        float radius = (float) (Math.min(width, height) / 2 * 0.85);

        mDrawRectf.set(-radius, -radius, radius, radius);
        canvas.drawArc(mDrawRectf,0, 120,false,paint1);
        canvas.drawArc(mDrawRectf, 120, 120, false, paint2);
        canvas.drawArc(mDrawRectf, 240, 120, false, paint3);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            mLastx = x;
            mLasty = y;
            LogUtil.d("自定义"+"x:"+x+",y:"+y);
        }else if (event.getAction() == MotionEvent.ACTION_MOVE){

           int offsetX = x - mLastx;
           int offsetY = y - mLasty;
            LogUtil.d("自定义移动"+"x:"+x+",y:"+y +",setx:"+offsetX +",sety:"+offsetY);
               layout(getLeft() + offsetX, getTop()+offsetY, getRight()+offsetX,getBottom()+offsetY);
        }else if (event.getAction() == MotionEvent.ACTION_UP){

        }
        return true;
    }
}
