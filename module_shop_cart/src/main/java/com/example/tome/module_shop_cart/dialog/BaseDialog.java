package com.example.tome.module_shop_cart.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.tome.module_shop_cart.annotation.AnimType;
import com.example.tome.module_shop_cart.annotation.ClickPosition;
import com.example.tome.module_shop_cart.listener.SimpleOnKeyListener;
import com.example.tome.module_shop_cart.widget.OnClickHelper;

@SuppressWarnings("all")
public abstract class BaseDialog<D extends BaseDialog> extends Dialog implements View.OnClickListener {
    /**
     * 对话框 根视图
     */
    protected View rootView;

    /**
     * dialog width scale(宽度比例)
     */
    protected float scaleWidth = 1;
    /**
     * dialog height scale(高度比例)
     */
    protected float scaleHeight;
    /**
     * max height(最大高度)
     */
    protected float maxHeight;
    /**
     * (DisplayMetrics)设备密度
     */
    protected DisplayMetrics displayMetrics;
    protected Context context;
    protected int gravity;
    protected int animType;
    /**
     * Dialog的标记
     */
    public Object tag;
    public OnDialogClickListener onDialogClickListener;
    Unbinder unbinder;

    /**
     * Creates a dialog window that uses the default dialog theme.
     * <p>
     * The supplied {@code context} is used to obtain the window manager and
     * base theme used to present the dialog.
     *
     * @param context the context in which the dialog should run
     * @see android.R.styleable#Theme_dialogTheme
     */
    public BaseDialog(Context context) {
        super(context);
        this.context = context;
        initBaseDialogTheme();
        initDialog();
    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
        initBaseDialogTheme();
        initDialog();
    }

    protected void initDialog() {
        displayMetrics = getContext().getResources().getDisplayMetrics();
        maxHeight = displayMetrics.heightPixels;
        rootView = LayoutInflater.from(getContext()).inflate(getLayoutRes(), null);
        tag = getClass().getSimpleName();
        setContentView(rootView);

        unbinder = ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);
        OnClickHelper.getInstance().onBindClickListener(rootView, this);
        onCreateData();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        synchronized (this) {
            if (unbinder != null) {
//                unbinder.unbind();
            }
        }
    }

    /**
     * 配置 对话框的 布局文件
     */
    @LayoutRes
    public abstract int getLayoutRes();

    /**
     * 在 这里 进行 findView  设置点击事件
     */
    public abstract void onCreateData();

    public D tag(@NonNull Object tag) {
        if (tag != null) {
            this.tag = tag;
        }
        return (D) this;
    }

    public Object tag() {
        return tag;
    }

    /**
     * 设置监听器
     */
    public D setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
        return (D) this;
    }

    /**
     * 销毁一切资源
     */
    public void destody() {
        super.dismiss();
        context = null;
        rootView.destroyDrawingCache();
        rootView = null;
        displayMetrics = null;
        onDialogClickListener = null;
    }

    /**
     * 设置 对话框 高度比 (0, 1]
     */
    public D setScaleHeight(float scaleHeight) {
        this.scaleHeight = scaleHeight;
        return (D) this;
    }

    /**
     * 设置 对话框 宽度比 (0, 1]
     */
    public D setScaleWidth(float scaleWidth) {
        this.scaleWidth = scaleWidth;
        return (D) this;
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        int width;
        if (scaleWidth == 0) {
            width = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else {
            width = (int) (displayMetrics.widthPixels * scaleWidth);
        }
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
//        if (scaleHeight == 0) {
//            height = ViewGroup.LayoutParams.WRAP_CONTENT;
//        } else if (scaleHeight == 1) {
//            height = ViewGroup.LayoutParams.MATCH_PARENT;
//        } else {
//            height = (int) (maxHeight * scaleHeight);
//        }
        rootView.setLayoutParams(new FrameLayout.LayoutParams(width, height));
    }

    /**
     * set dialog theme(设置对话框主题)
     */
    protected void initBaseDialogTheme() {
        animType = AnimType.CENTER_NORMAL;
        /*android:windowNoTitle*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        /* android:windowBackground*/
//     window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80000000")));
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                /*android:backgroundDimEnabled默认是true的*/
        //window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    public <T extends View> T findView(@IdRes int id) {
        return (T) rootView.findViewById(id);
    }


    public D setText(@IdRes int id, @NonNull String text) {
        View view = findView(id);
        setText(view, text);
        return (D) this;
    }

    public D setText(@NonNull View view, @NonNull String text) {
        if ((view == null) || (text == null)) {
            return (D) this;
        }
        if (view instanceof TextView) {
            ((TextView) view).setText(text);
        }
        if (view instanceof EditText) {
            ((EditText) view).setSelection(text.length());
        }
        return (D) this;
    }

    public D setOnCilckListener(@IdRes int id) {
        View view = findView(id);
        if (view == null) {
            return (D) this;
        }
        view.setOnClickListener(this);
        return (D) this;
    }

    public D setOnCilckListener(@IdRes int... id) {
        for (int i = 0; (id != null) && (i < id.length); i++) {
            setOnCilckListener(id[i]);
        }
        return (D) this;
    }

    /**
     * 处理按钮点击事件 并绑定 onDialogClickListener
     */
    @Override
    public void onClick(View v) {
        onClick(v, v.getId());
    }

    /**
     * 处理按钮点击事件 并绑定 onDialogClickListener
     */
    public abstract void onClick(View v, int id);

    public void onDialogClickListener(@ClickPosition String clickPosition) {
        if (onDialogClickListener != null) {
            onDialogClickListener.onDialogClick((D) this, clickPosition);
        }
    }

    /**
     * 设置 物理按键的 监听事件
     *
     * @param simpleOnKeyListener
     */
    public D setOnKeyListener(SimpleOnKeyListener simpleOnKeyListener) {
        super.setOnKeyListener(simpleOnKeyListener);
        return (D) this;
    }

    /**
     * 设置对话框的显示位置
     */
    public D setGravity(int gravity) {
        this.gravity = gravity;
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(gravity);
        return (D) this;
    }

    /**
     * 设置对话框的显示位置，以及Y轴的向下偏移量（单位 dp）
     */
    public D setGravity(int gravity, int yDP) {
        this.gravity = gravity;
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = (int) (yDP * context.getResources().getDisplayMetrics().density);
        dialogWindow.setGravity(gravity);
        return (D) this;
    }

    public D setAnimType(@AnimType int animType) {
        this.animType = animType;
        /*如果根据  AnimType 的类型，强制选择Dialog出现的位置*/
        if (AnimType.BOTTOM_2_TOP == animType) {
            setGravity(Gravity.BOTTOM);
        } else if (AnimType.TOP_2_BOTTOM == animType) {
            setGravity(Gravity.TOP);
        } else if (AnimType.CENTER_SCALE == animType) {
            setGravity(Gravity.CENTER);
        } else if (AnimType.CENTER_NORMAL == animType) {
            setGravity(Gravity.CENTER);
        }
        return (D) this;
    }

    private void attachView(View view) {
        int[] position = new int[2];
        view.getLocationInWindow(position);

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();

        window.setGravity(Gravity.LEFT | Gravity.TOP);
        params.x = position[0] + getViewWidth(view) / 2;
        params.y = position[1] + getViewHeight(view) / 2;

        window.setAttributes(params);
    }

    /**
     * 测量这个view
     * 最后通过getMeasuredWidth()获取宽度和高度.
     *
     * @param view 要测量的view
     * @return 测量过的view
     */
    private void measureView(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight, View.MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }
        view.measure(childWidthSpec, childHeightSpec);
    }

    /**
     * 获得这个View的宽度
     * 测量这个view，最后通过getMeasuredWidth()获取宽度.
     *
     * @param view 要测量的view
     * @return 测量过的view的宽度
     */
    protected int getViewWidth(View view) {
        measureView(view);
        return view.getMeasuredWidth();
    }

    /**
     * 获得这个View的高度
     * 测量这个view，最后通过getMeasuredHeight()获取高度.
     *
     * @param view 要测量的view
     * @return 测量过的view的高度
     */
    protected int getViewHeight(View view) {
        measureView(view);
        return view.getMeasuredHeight();
    }
}

