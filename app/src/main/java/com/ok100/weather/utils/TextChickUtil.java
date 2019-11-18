package com.ok100.weather.utils;

import android.text.TextUtils;


/**
 * Created by Administrator on 2017/7/7.
 */

public class TextChickUtil {
    public static String isEmpty(String string){
        String s = TextUtils.isEmpty(string) ? "" : string;
        if(!TextUtils.isEmpty(s)){
            if(string.indexOf("null")>-1){
                string = "" ;
            }
        }
        return string;
    }
}
