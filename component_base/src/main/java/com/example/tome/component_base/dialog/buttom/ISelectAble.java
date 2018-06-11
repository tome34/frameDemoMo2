package com.example.tome.component_base.dialog.buttom;

/**
 * Created by dun on 17/2/9.
 */

public interface ISelectAble {
    /**
     * 显示在栏目上的名字
     * */
    public String getName();

    /**
     * 用户设定的id，根据这个id，可以获取级栏目或者指定为最终栏目的id
     * */
    public int getId();

    /**
     * 自定义类型对象。
     * */
    public Object getArg();

}
