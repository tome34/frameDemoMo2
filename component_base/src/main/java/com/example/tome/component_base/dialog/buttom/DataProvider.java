package com.example.tome.component_base.dialog.buttom;

import java.util.List;

/**
 * Created by dun on 17/2/9.
 */

public interface DataProvider {
    void provideData(int currentDeep, int preId, DataReceiver receiver);


    interface DataReceiver {
        void send(List<ISelectAble> data);
    }
}
