package com.example.tome.core.widget;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * 圆形图片
 * @author Administrator
 */
public class CircularImageView extends AppCompatImageView {
	// 设置外圈的宽度
	private int borderWidth = 5;
	private int viewWidth;
	private int viewHeight;
	private Bitmap image;
	private Paint paintBorder;//背景画笔

	public CircularImageView(Context context) {
		super(context);
		setup();
	}

	public CircularImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setup();
	}

	public CircularImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setup();
	}

	private void setup() {
		//初始化背景画笔
		paintBorder = new Paint();
		//设置背景颜色
		setBorderColor(Color.WHITE);
		paintBorder.setAntiAlias(true);
	}

	// 设置外圈的宽度
	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
		this.invalidate();
	}

	// 设置底图颜色
	public void setBorderColor(int borderColor) {
		if (paintBorder != null)
			paintBorder.setColor(borderColor);

		this.invalidate();
	}

    public void loadBitmap() {
        Drawable drawable=   this.getDrawable();
		if(drawable==null){return;}
        image = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
                        : Config.RGB_565);
        Canvas canvas = new Canvas(image);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
    }

	@Override
	public void onDraw(Canvas canvas) {
		// load the bitmap  加载图片
		loadBitmap();

		// init shader
		if (image != null) {

			int circleCenter = viewWidth / 2;

			int min = Math.min(viewWidth, viewHeight);

			image = Bitmap.createScaledBitmap(image, min, min, false);

			canvas.drawCircle(circleCenter + borderWidth, circleCenter+ borderWidth, circleCenter + borderWidth, paintBorder);
//			canvas.drawCircle(circleCenter + borderWidth, circleCenter+ borderWidth, circleCenter, paint);
			canvas.drawBitmap(createCircleImage(image, min), borderWidth, borderWidth, null);
		}
	}
	/**
	 * 根据原图和边长绘制圆形图片
	 *
	 * @param source
	 * @param min
	 * @return
	 */
	private Bitmap createCircleImage(Bitmap source, int min)
	{
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		Bitmap target = Bitmap.createBitmap(min, min, Config.ARGB_8888);
		/**
		 * 产生一个同样大小的画布
		 */
		Canvas canvas = new Canvas(target);
		/**
		 * 首先绘制圆形
		 */
		canvas.drawCircle(min / 2, min / 2, min / 2, paint);
		/**
		 * 使用SRC_IN，参考上面的说明
		 */
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		/**
		 * 绘制图片
		 */
		canvas.drawBitmap(source, 0, 0, paint);
		return target;
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = measureWidth(widthMeasureSpec);
		int height = measureHeight(heightMeasureSpec, widthMeasureSpec);

		viewWidth = width - (borderWidth * 2);
		viewHeight = height - (borderWidth * 2);

		setMeasuredDimension(width, height);
	}

	private int measureWidth(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			// We were told how big to be
			result = specSize;
		} else {
			// Measure the text
			result = viewWidth;

		}

		return result;
	}

	private int measureHeight(int measureSpecHeight, int measureSpecWidth) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpecHeight);
		int specSize = MeasureSpec.getSize(measureSpecHeight);

		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else {
			result = viewHeight;
		}
		return result;
	}

}
