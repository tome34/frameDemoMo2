package com.example.tome.module_shop_cart.base;

import com.example.tome.module_shop_cart.bean.LoginBean;

/**
 * @author tome
 * @date 2018/8/10  14:56
 * @describe ${公共数据}
 */
public class BaseData {
    public final static int DISTRIBUTOR = 0;//0分销商
    public final static int ENTERPRISE = 1;//   1企业端
    public final static int SHOPASSISTANT = 2;//   2店员端

    public static int IdentityType = DISTRIBUTOR;//会员身份
    public static LoginBean loginBean;
    public static boolean isLogin;

    public static String UNIT = "EGP ";
    public static String COUNT_PIECE_UNIT = "件";
}
