package com.example.tome.component_base.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author by TOME .
 * @data on      2018/6/25 14:52
 * @describe ${字符串相关}
 */

public class StringUtils {

    /**
     * 去掉"-"号
     * @param str
     * @return
     */
    public static String toAbs(String str) {

        return str.replaceAll("-", "");
    }

    /**
     * 功能描述：分割字符串
     *
     * @param str       String 原始字符串
     * @param splitsign String 分隔符
     * @return String[] 分割后的字符串数组
     */
    @SuppressWarnings("unchecked")
    public static String[] split(String str, String splitsign) {
        int index;
        if (str == null || splitsign == null) {
            return null;
        }
        ArrayList al = new ArrayList();
        while ((index = str.indexOf(splitsign)) != -1) {
            al.add(str.substring(0, index));
            str = str.substring(index + splitsign.length());
        }
        al.add(str);
        return (String[]) al.toArray(new String[0]);
    }

    /**
     * 功能描述：替换字符串
     *
     * @param from   String 原始字符串
     * @param to     String 目标字符串
     * @param source String 母字符串
     * @return String 替换后的字符串
     */
    public static String replace(String from, String to, String source) {
        if (source == null || from == null || to == null) {
            return null;
        }
        StringBuffer str = new StringBuffer("");
        int index = -1;
        while ((index = source.indexOf(from)) != -1) {
            str.append(source.substring(0, index) + to);
            source = source.substring(index + from.length());
            index = source.indexOf(from);
        }
        str.append(source);
        return str.toString();
    }

    /**
     * 保存文字到剪贴板
     * @param context context
     * @param text text
     */
    public static void copyToClipBoard(Context context, String text) {
        ClipData clipData = ClipData.newPlainText("url", text);
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        assert manager != null;
        manager.setPrimaryClip(clipData);
        Toast.makeText(context, "已复制到剪贴板", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取随机rgb颜色值
     */
    public static int intrandomColor(){
        Random random = new Random();
        //0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        int red =random.nextInt(150);
        //0-190
        int green =random.nextInt(150);
        //0-190
        int blue =random.nextInt(150);
        //使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red,green, blue);
    }
}
