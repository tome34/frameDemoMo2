package com.example.tome.module_shop_mall.params;

/**
 * @author by TOME .
 * @data on      2018/6/26 11:52
 * @describe ${TODO}
 */

public class TestParams {
    //	页码	String		页码
    private int pageIndex;
    //每页显示数量	String		每页显示数量
    private int pageSize;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
