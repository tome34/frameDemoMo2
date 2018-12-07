package com.example.tome.core.util;

import android.graphics.Color;
import java.util.Random;

/**
 * @author tome
 * @date 2018/7/11  16:58
 * @describe ${其他的工具}
 */
public class OtherUtils {

    /**
     * 获取随机rgb颜色值
     */
    public static int randomColor(){
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

    public static int randomTagColor() {
        int randomNum = new Random().nextInt();
        int position = randomNum % TAB_COLORS.length;
        if (position < 0) {
            position = -position;
        }
        return TAB_COLORS[position];
    }

    /**
     * Tab colors
     */
    public static final int[] TAB_COLORS = new int[]{
        Color.parseColor("#90C5F0"),
        Color.parseColor("#91CED5"),
        Color.parseColor("#F88F55"),
        Color.parseColor("#C0AFD0"),
        Color.parseColor("#E78F8F"),
        Color.parseColor("#67CCB7"),
        Color.parseColor("#F6BC7E")
    };
}
