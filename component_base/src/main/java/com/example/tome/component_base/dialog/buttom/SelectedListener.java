package com.example.tome.component_base.dialog.buttom;

import java.util.ArrayList;


public interface SelectedListener {
    /**
     * 回调接口，根据选择深度，按顺序返回选择的对象。
     * */
    void onAddressSelected(ArrayList<ISelectAble> selectAbles);
}
