package com.ok100.weather.utils;

import android.text.TextUtils;

/**
 * Created by Administrator on 2018/3/12.
 */

public class TextUtilsCheck {
    private String backZanWu(String string) {
        String s = TextUtils.isEmpty(string) ? "暂无" : string;
        return s;
    }
    private String backKongZiFu(String string) {
        String s = TextUtils.isEmpty(string) ? "" : string;
        return s;
    }
}
