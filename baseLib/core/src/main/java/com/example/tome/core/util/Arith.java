package com.example.tome.core.util;

import java.math.BigDecimal;

/**
 * @author liubp
 * 运算工具
 */
public class Arith {
    //默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;

    //这个类不能实例化
    private Arith() {
    }

    /**
     * 判断2个对象是否相等
     *
     * @param a Object a
     * @param b Object b
     * @return isEqual
     */
    public static boolean isEquals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和(保留两位小数，4舍5入)
     */
    public static String add(String v1, String v2) {
        try {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);

            return round(b1.add(b2).toString(), 2);
        } catch (Exception e) {
            e.printStackTrace();
            return "0.00";
        }
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static String sub(String v1, String v2) {
        try {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            return round(b1.subtract(b2).toString(), 2);
        } catch (Exception e) {
            e.printStackTrace();
            return "0.00";
        }
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积(保留两位小数，4舍5入)
     */
    public static String mul(String v1, String v2) {
        try {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            return round(b1.multiply(b2).toString(), 2);
        } catch (Exception e) {
            e.printStackTrace();
            return "0.00";
        }
    }


    /**
     * 两个值进行比较
     *
     * @param v1 比较值1
     * @param v2 比较值2
     * @return 返回值： －1:比较值1小 v1；  0：两个数值相等； 1：比较值1大 v1；2 ：传入内容错误
     */
    public static int compareTo(String v1, String v2) {
        try {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            return b1.compareTo(b2);
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商(保留两位小数，4舍5入)
     */
    public static String div(String v1, String v2) {
        return round(div(v1, v2, DEF_DIV_SCALE), 2);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static String div(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        try {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "0.00";
        }
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static String round(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        try {
            BigDecimal b = new BigDecimal(v);
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "0.00";
        }
    }

    /**
     * 四舍五入保留获取2位小数。
     *
     * @param v 需要四舍五入的数字
     * @return 四舍五入后的结果
     */
    public static String get2Decimal(String v) {
        try {
            if (v.contains(",")){
                v = v.replace(",","");
            }
            BigDecimal b = new BigDecimal(v);
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, 2, BigDecimal.ROUND_HALF_UP).toString();
        } catch (Exception e) {
            L.i(e.toString());
            return "0.00";
        }
    }

    /**
     * 四舍五入取整。
     *
     * @param v 需要四舍五入的数字
     * @return 四舍五入后的结果
     */
    public static String get0Decimal(String v) {

        try {
            BigDecimal b = new BigDecimal(v);
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, 0, BigDecimal.ROUND_HALF_UP).toString();
        } catch (Exception e) {
            L.i(e.toString());
            return "0";
        }
    }  /**
     * 四舍五入取整。
     *
     * @param v 需要四舍五入的数字
     * @return 四舍五入后的结果
     */
    public static String get1Decimal(String v) {

        try {
            BigDecimal b = new BigDecimal(v);
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, 1, BigDecimal.ROUND_HALF_UP).toString();
        } catch (Exception e) {
            L.i(e.toString());
            return "0";
        }
    }
}

