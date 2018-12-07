//package com.example.tome.core.widget.roundWidget;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import com.example.tome.core.R;
//import com.example.tome.core.widget.ViewHelper;
//
///**
// * 使按钮能方便地指定圆角、边框颜色、边框粗细、背景色
// * <p>
// * 注意: 因为该控件的圆角采用 View 的 background 实现, 所以与原生的 <code>android:background</code> 有冲突。
// * <ul>
// * <li>如果在 xml 中用 <code>android:background</code> 指定 background, 该 background 不会生效。</li>
// * <li>如果在该 View 构造完后用 {@link #setBackgroundResource(int)} 等方法设置背景, 该背景将覆盖圆角效果。</li>
// * </ul>
// * </p>
// * <p>
// * 如需在 xml 中指定圆角、边框颜色、边框粗细、背景色等值,采用 xml 属性 *{@link com.qmuiteam.qmui.R.styleable#RoundButton}
// * </p>
// * <p>
// * 如需在 Java 中指定以上属性, 需要通过 {@link #getBackground()} 获取 {@link RoundButtonDrawable} 对象,
// * 然后使用 {@link RoundButtonDrawable} 提供的方法进行设置。
// * </p>
// * <p>
// * @see RoundButtonDrawable
// * </p>
// */
//public class RoundButton extends AlphaButton {
//
//    public RoundButton(Context context) {
//        super(context);
//        init(context, null, 0);
//    }
//
//    public RoundButton(Context context, AttributeSet attrs) {
//        super(context, attrs, R.attr.QMUIButtonStyle);
//        init(context, attrs, R.attr.QMUIButtonStyle);
//    }
//
//    public RoundButton(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init(context, attrs, defStyleAttr);
//    }
//
//    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
//        RoundButtonDrawable bg = RoundButtonDrawable.fromAttributeSet(context, attrs, defStyleAttr);
//        ViewHelper.setBackgroundKeepingPadding(this, bg);
//        setChangeAlphaWhenDisable(false);
//        setChangeAlphaWhenPress(false);
//    }
//}
