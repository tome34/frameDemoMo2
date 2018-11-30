package com.example.tome.module_common.uiHelper;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.example.tome.projectCore.helper.LocaleHelper;
import com.example.tome.module_common.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * https://github.com/Bigkoo/Android-PickerView
 * 时间控件
 * Created by gonghuiyuan on 2016/9/5.
 */
public class PickerViewUtils {
    private OptionsPickerView pvOptions;
    private TimePickerView timePickerView;
    private boolean isSetTime = false;//是否设置时间的区间

    private boolean[] time = {true, true, true, true, true, true};
    public ArrayList<String> roomList = new ArrayList<>();//室

    public boolean[] getTime() {
        return time;
    }

    public void setTime(boolean[] time) {
        this.time = time;
    }

    public void setSetTime(boolean setTime) {
        isSetTime = setTime;
    }

    private Context context;

    public static PickerViewUtils newInstance(Context context) {

        return new PickerViewUtils(context);
    }

    public PickerViewUtils(Context context) {
        this.context = context;

        Map<String, String> maps = new HashMap<>();
        maps.put("0", "1");
        maps.put("1", "2");
        maps.put("2", "3");
        maps.put("3", "4");
        maps.put("4", "5");
        maps.put("5", "6");
        maps.put("6", "7");
        maps.put("7", "8");
        maps.put("8", "9");
        for (int i = 0; i < 9; i++) {
            roomList.add(maps.get(i + ""));
        }
    }


    public void timePickerView(TimePickerView.OnTimeSelectListener timeSelectListener, String title) {
        Calendar selectedDate = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
        if (isSetTime) {
            calendar.set(2007, 0, 1);
            endDate.set(2027, 11, 31);
        }

        //正确设置方式 原因：注意事项有说明
        timePickerView = new TimePickerView.Builder(context, timeSelectListener)
                .setTitleText(title)

                .setLabel(context.getString(R.string.otherui_pickerview_year),
                        context.getString(R.string.otherui_pickerview_month),
                        context.getString(R.string.otherui_pickerview_day),
                        context.getString(R.string.otherui_pickerview_hour),
                        context.getString(R.string.otherui_pickerview_minute),
                        context.getString(R.string.otherui_pickerview_second))
                .setSubmitColor(context.getResources().getColor(R.color.otherui_color_black))
                .setCancelColor(context.getResources().getColor(R.color.otherui_color_33))
                .setContentSize(14)//滚轮文字大小
                .setDate(selectedDate)
                .setRangDate(calendar, endDate)//起始终止年月日设定
                .setLayoutRes(R.layout.otherui_pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView ivCancel = (TextView) v.findViewById(R.id.tv_cancel);


                        //因为适配阿拉伯语言做的修改
                        if (isArabic()) {
                            ivCancel.setGravity(Gravity.RIGHT);
                        } else {
                            ivCancel.setGravity(Gravity.LEFT);
                        }





                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timePickerView.returnData();
                                timePickerView.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timePickerView.dismiss();
                            }
                        });
                    }
                })
                .setDividerColor(Color.BLACK)
                .setType(time)
                .build();
        timePickerView.show();
    }


    public void roomPickerView(OptionsPickerView.OnOptionsSelectListener listener) {
        pvOptions = new OptionsPickerView.Builder(context, listener)
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText("房型选择")//标题
                .setSubCalSize(18)//确定和取消文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setContentTextSize(18)//滚轮文字大小
                .setLinkage(false)//设置是否联动，默认true
                .setLabels("室", "厅", "卫")//设置选择的三级单位
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(1, 1, 1)  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
                .build();

        pvOptions.setNPicker(roomList, roomList, roomList);//添加数据源
        pvOptions.show();
    }

    public void dataPickerView(OptionsPickerView.OnOptionsSelectListener listener, List<String> datas) {
        if (pvOptions == null) {
            pvOptions = new OptionsPickerView.Builder(context, listener)
                    .setSubmitText("确定")//确定按钮文字
                    .setCancelText("取消")//取消按钮文字
                    .setTitleText("职业资格证")//标题
                    .setSubCalSize(18)//确定和取消文字大小
                    .setTitleSize(20)//标题文字大小
                    .setTitleColor(Color.BLACK)//标题文字颜色
                    .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                    .setCancelColor(Color.BLUE)//取消按钮文字颜色
                    .setContentTextSize(18)//滚轮文字大小
                    .setLinkage(false)//设置是否联动，默认true
                    .setCyclic(false, false, false)//循环与否
                    .setSelectOptions(1, 1, 1)  //设置默认选中项
                    .setOutSideCancelable(false)//点击外部dismiss default true
                    .build();

            pvOptions.setPicker(datas);//添加数据源
        }
        pvOptions.show();
    }


    public String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public String getTimeAll(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 是否是阿拉伯语言
     * */
    public boolean isArabic(){
        Locale language = LocaleHelper.getLanguage(context);
        return "ar".equals(language.getLanguage());
    }
}
