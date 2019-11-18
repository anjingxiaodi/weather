package com.ok100.weather.utils;

import java.util.Calendar;

/**
 * Created by Administrator on 2018/1/23.
 */

public class TimeUtils {
    public static String getNowTime(){
        Calendar c = Calendar.getInstance();//
        int mYear = c.get(Calendar.YEAR); // 获取当前年份
        int mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        int  mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期


        return mYear+"-"+mMonth+"-"+mDay;
    }
}
