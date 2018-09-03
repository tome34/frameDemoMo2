package com.example.tome.module_shop_cart.dialog;

import android.app.Dialog;
import com.example.tome.module_shop_cart.annotation.ClickPosition;

@SuppressWarnings("all")
public interface OnDialogClickListener<D extends Dialog>
{
	public  void onDialogClick(D dialog,@ClickPosition String clickPosition);
}
