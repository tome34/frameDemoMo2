package com.example.tome.module_common.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.example.tome.module_common.R;

public class SlideSwitch extends View {

	public static final int SHAPE_RECT = 1;
	public static final int SHAPE_CIRCLE = 2;
	/** 边距 */
	private static final int RIM_SIZE = 3;
	private static final int DEFAULT_COLOR_THEME = Color
			.parseColor("#ff00ee00");
	private static final int DEFAULT_COLOR_BTN = Color.parseColor("#ffffffff");
	/** 打开后背景的颜色 */
	private int color_theme;
	/** 开关上面按钮的颜色 */
	private int color_btn;
	/** 开关是否打开 */
	private boolean isOpen;
	/***/
	private int shape;
	/** 画笔 */
	private Paint paint;
	/** 矩形-背景 */
	private Rect backRect;
	/** 矩形-按钮 */
	private Rect frontRect;
	/** 圆形按钮 */
	private RectF frontCircleRect;
	/** 圆形背景 */
	private RectF backCircleRect;
	/** 开关打开时背景的透明度 */
	private int alpha;
	private int max_left;
	private int min_left;
	private int frontRect_left;
	private int frontRect_left_begin = RIM_SIZE;
	private int eventStartX;
	private int eventLastX;
	private int diffX = 0;
	private boolean slideable = true;
	/** 开关按钮接口 */
	private SlideListener listener;

	public interface SlideListener {

		/**
		 * 开关打开
		 */
		public void open();

		/**
		 * 开关关闭
		 */
		public void close();
	}

	public SlideSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		listener = null;// 初始化接口监听
		paint = new Paint();// 初始化画笔
		paint.setAntiAlias(true);// 设置抗锯齿
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.slideswitch);
		color_theme = a.getColor(R.styleable.slideswitch_themeColor,
				DEFAULT_COLOR_THEME);
		isOpen = a.getBoolean(R.styleable.slideswitch_isOpen, false);
		shape = a.getInt(R.styleable.slideswitch_shape, SHAPE_RECT);
		color_btn = a.getColor(R.styleable.slideswitch_btnColor,
				DEFAULT_COLOR_BTN);
		a.recycle();
	}

	public SlideSwitch(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SlideSwitch(Context context) {
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = measureDimension(120, widthMeasureSpec);
		int height = measureDimension(60, heightMeasureSpec);
		if (shape == SHAPE_CIRCLE) {
			if (width < height)
				width = height * 2;
		}
		setMeasuredDimension(width, height);
		initDrawingVal();
	}

	public void initDrawingVal() {
		int width = getMeasuredWidth();
		int height = getMeasuredHeight();

		backCircleRect = new RectF();
		frontCircleRect = new RectF();
		frontRect = new Rect();
		backRect = new Rect(0, 0, width, height);
		min_left = RIM_SIZE;
		if (shape == SHAPE_RECT)
			max_left = width / 2;
		else
			max_left = width - (height - 2 * RIM_SIZE) - RIM_SIZE;
		if (isOpen) {
			frontRect_left = max_left;
			alpha = 255;
		} else {
			frontRect_left = RIM_SIZE;
			alpha = 0;
		}
		frontRect_left_begin = frontRect_left;
	}

	public int measureDimension(int defaultSize, int measureSpec) {
		int result;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);
		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else {
			result = defaultSize; // UNSPECIFIED
			if (specMode == MeasureSpec.AT_MOST) {
				result = Math.min(result, specSize);
			}
		}
		return result;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// 判断画的是矩形开关还是圆形开关
		if (shape == SHAPE_RECT) {
			// 给画笔设置开关关闭时背景颜色-灰色
			paint.setColor(Color.GRAY);
			// 画出灰色的矩形背景
			canvas.drawRect(backRect, paint);
			// 给画笔设置开关打开时的背景颜色
			paint.setColor(color_theme);
			// 给画笔设置开关打开时的背景颜色的透明度
			paint.setAlpha(alpha);
			// 画出开关打开时矩形背景
			canvas.drawRect(backRect, paint);
			// 设置开关上面的按钮区域
			frontRect.set(frontRect_left, RIM_SIZE, frontRect_left
					+ getMeasuredWidth() / 2 - RIM_SIZE, getMeasuredHeight()
					- RIM_SIZE);
			// 个画笔设置开关上面按钮的颜色
			paint.setColor(color_btn);
			// 画出开关上面的按钮
			canvas.drawRect(frontRect, paint);

		} else {
			// draw circle
			int radius = backRect.height() / 2 - RIM_SIZE;// 获取到圆形的半径

			paint.setColor(Color.GRAY);
			backCircleRect.set(backRect);
			canvas.drawRoundRect(backCircleRect, radius, radius, paint);

			paint.setColor(color_theme);
			paint.setAlpha(alpha);
			canvas.drawRoundRect(backCircleRect, radius, radius, paint);

			frontRect.set(frontRect_left, RIM_SIZE,
					frontRect_left + backRect.height() - 2 * RIM_SIZE,
					backRect.height() - RIM_SIZE);
			frontCircleRect.set(frontRect);
			paint.setColor(color_btn);
			canvas.drawRoundRect(frontCircleRect, radius, radius, paint);
		}
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (slideable == false)
			return super.onTouchEvent(event);
		int action = MotionEventCompat.getActionMasked(event);
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			eventStartX = (int) event.getRawX();
			break;
		case MotionEvent.ACTION_MOVE:
			eventLastX = (int) event.getRawX();
			diffX = eventLastX - eventStartX;
			int tempX = diffX + frontRect_left_begin;
			tempX = (tempX > max_left ? max_left : tempX);
			tempX = (tempX < min_left ? min_left : tempX);
			if (tempX >= min_left && tempX <= max_left) {
				frontRect_left = tempX;
				alpha = (int) (255 * (float) tempX / (float) max_left);
				invalidateView();
			}
			break;
		case MotionEvent.ACTION_UP:
			int wholeX = (int) (event.getRawX() - eventStartX);
			frontRect_left_begin = frontRect_left;
			boolean toRight;
			toRight = (frontRect_left_begin > max_left / 2 ? true : false);
			if (Math.abs(wholeX) < 3) {
				toRight = !toRight;
			}
			moveToDest(toRight);
			break;
		default:
			break;
		}
		return true;
	}

	/**
	 * draw again
	 */
	private void invalidateView() {
		if (Looper.getMainLooper() == Looper.myLooper()) {
			invalidate();
		} else {
			postInvalidate();
		}
	}

	//使用调用此方法
	public void setSlideListener(SlideListener listener) {
		this.listener = listener;
	}

	@SuppressLint("NewApi")
	public void moveToDest(final boolean toRight) {
		ValueAnimator toDestAnim = ValueAnimator.ofInt(frontRect_left,
				toRight ? max_left : min_left);
		toDestAnim.setDuration(500);
		toDestAnim.setInterpolator(new AccelerateDecelerateInterpolator());
		toDestAnim.start();
		toDestAnim.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				frontRect_left = (Integer) animation.getAnimatedValue();
				alpha = (int) (255 * (float) frontRect_left / (float) max_left);
				invalidateView();
			}
		});
		toDestAnim.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				if (toRight) {
					isOpen = true;
					if (listener != null)
						listener.open();
					frontRect_left_begin = max_left;
				} else {
					isOpen = false;
					if (listener != null)
						listener.close();
					frontRect_left_begin = min_left;
				}
			}
		});
	}

	/**
	 * 设置开关的 开、关
	 *
	 * @param isOpen
	 *            true 打开 false关闭
	 */
	public void setState(boolean isOpen) {
		this.isOpen = isOpen;
		initDrawingVal();
		invalidateView();
		if (listener != null) {
			if (isOpen == true) {
				listener.open();
			} else {
				listener.close();
			}
		}
	}

	public void setShapeType(int shapeType) {
		this.shape = shapeType;
	}

	public void setSlideable(boolean slideable) {
		this.slideable = slideable;
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		if (state instanceof Bundle) {
			Bundle bundle = (Bundle) state;
			this.isOpen = bundle.getBoolean("isOpen");
			state = bundle.getParcelable("instanceState");
		}
		super.onRestoreInstanceState(state);
	}

	@Override
	protected Parcelable onSaveInstanceState() {
		Bundle bundle = new Bundle();
		bundle.putParcelable("instanceState", super.onSaveInstanceState());
		bundle.putBoolean("isOpen", this.isOpen);
		return bundle;
	}
}
