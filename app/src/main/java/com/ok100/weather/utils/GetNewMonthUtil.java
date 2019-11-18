package com.ok100.weather.utils;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/9/30.
 */

public class GetNewMonthUtil {
    public static String getMonth(){
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        String format = df.format(new Date());// new Date()为获取当前系统时间
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH )+1;
        return year+"-"+month;
    }
    public static int getOnlyMonth(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH )+1;
        return month ;
    }
    public static String getEndMonth(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH )+1;
        if(month!=12){
            return year+"-"+(month+1)+"-01 00:00:00";
        }else {
            return (year+1)+"-"+"01"+"-01 00:00:00";
        }

    }

    public static String getStartMonth(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH )+1;
        return year+"-"+month+"-01 00:00:00";
    }

}
