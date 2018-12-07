package com.example.tome.core.base;

/**EventBus事件类
 * Created by ghy on 2017/11/10.
 * 统一管理eventbus ,这有缺点,因为eventbus的原理是用链式存储,如果key相同,这有查找的速度就很变慢
 */

public class BaseEventbusBean<T> {
    private int type;
    private T obj;

    public BaseEventbusBean(int type, T obj) {
        this.type = type;
        this.obj = obj;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
