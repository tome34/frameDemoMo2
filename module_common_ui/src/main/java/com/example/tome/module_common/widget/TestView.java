package com.example.tome.module_common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.example.tome.core.util.L;
import com.example.tome.module_common.R;

/**
 * @author tome
 * @date 2018/9/5  13:55
 * @describe ${test}
 */
public class TestView extends View{

    private Paint mPaint;
    private Bitmap mBitmap;
    private Paint mPaint1;

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context,@Nullable AttributeSet attrs) {
        this(context,attrs, 0);
    }

    public TestView(Context context,@Nullable AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        init();
    }

    private void init() {
        L.d("绘制","1");
        mBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.addimg_1x);
        mPaint = new Paint();
        mPaint.setTextSize(30);
        mPaint.setColor(Color.BLUE);

        mPaint1 = new Paint();
        mPaint1.setColor(Color.RED);//设置画笔颜色
        mPaint1.setAntiAlias(true); //设置是否抗锯齿;
        mPaint1.setStyle(Paint.Style.FILL_AND_STROKE); //设置填充样式
        mPaint1.setStrokeWidth(2);//设置画笔宽度
        mPaint1.setShadowLayer(10,15,15,Color.RED);//设置阴影

        //画路径


    }

    @Override
    protected void onDraw(Canvas canvas) {
        L.d("绘制","2");
        canvas.drawText("你好呀", 5,50 ,mPaint);

        //画直线
        canvas.drawLine(20,60,100,70,mPaint1);
        //画圆角矩形方法：
        RectF rect=new RectF(100,100,200,110);
        canvas.drawRoundRect(rect,10,10,mPaint);

        //画圆形:圆心离X坐标位置，圆离Y坐标位置，半径，样式
        canvas.drawCircle(100,210,50,mPaint);
        //绘制图形
        canvas.drawBitmap(mBitmap, 10, 350, mPaint);

        //画路径
        Path path = new Path();
        path.lineTo(200, 200);
        path.lineTo(400, 0);
        canvas.drawPath(path, mPaint);
    }
}
