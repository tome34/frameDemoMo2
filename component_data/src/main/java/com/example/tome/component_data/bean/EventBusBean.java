package com.example.tome.component_data.bean;

import com.example.tome.component_data.constant.BaseEventbusBean;

/**
 * @Created by TOME .
 * @时间 2018/5/17 16:47
 * @描述 ${EventBus 事件类型}
 */

public class EventBusBean extends BaseEventbusBean<Object>{

    public EventBusBean(int type, Object obj) {
        super(type, obj);
    }

    public static final int CUSTOMER_FILTER = 10001;//分销商列表筛选
    public static final int SHOP_FILTER = 10002;//商品列表筛选
}
