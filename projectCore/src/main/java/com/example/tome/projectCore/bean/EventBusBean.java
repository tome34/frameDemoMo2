package com.example.tome.projectCore.bean;

import com.example.tome.core.base.BaseEventbusBean;

/**
 * @Created by TOME .
 * @时间 2018/5/17 16:47
 * @描述 ${EventBus 事件类型}
 */

public class EventBusBean extends BaseEventbusBean<Object> {

    public EventBusBean(int type, Object obj) {
        super(type, obj);
    }

    //例子
    public static final int CUSTOMER_FILTER = 10000;

    //首页侧滑
    public static final int SHOP_MALL_HOME = 10001;
}
