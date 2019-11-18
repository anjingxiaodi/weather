package com.ok100.weather.utils;

import android.text.TextUtils;

/**
 * Created by Administrator on 2018/4/4.
 */

public class ReplaseUtils {
    public static String replase1(String string){
        if(TextUtils.isEmpty(string)){
            return "";
        }
        String replace = string.replace("-", "/");
        return replace;
    }
}
