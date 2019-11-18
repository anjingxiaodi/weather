package com.ok100.weather.utils;

import com.lzy.imagepicker.bean.ImageItem;

import java.util.List;
import java.util.Map;

/**
 * Created by qiandd on 2017-2-23.
 */

public class ConvertUtils {
    /**
     *
     * @param userId
     * @param list
     * @return 根据用户编号取用户名称
     */
    public static String getUserNameById(String userId, List<Map<String, String>> list) {
        for (Map<String, String> m : list) {
            if (m.get("userId").equals(userId))
                return m.get("userName");
        }
        return "";
    }

    /**
     *
     * @param list
     * @return 拼接图片url
     */
    public static String getUrlbyString(List<Map<String, String>> list) {
        String urls = "";
        for (Map<String, String> m : list) {
            if (m.containsKey("url"))
                urls += ("," + m.get("url"));
        }
        if (urls.length() > 1)
            return urls.substring(1);
        return urls;
    }

    /**
     *
     * @param list
     * @return 拼接图片url
     */
    public static String getItembyString(List<String> list ) {
        String urls = "";
        for (String s : list) {
            urls += ("," + s);
        }
            if (urls.length() > 1)
                return urls.substring(1);
            return urls;
        }


    /**
     *
     * @param array
     * @return 拼接图片url
     */
    public static String getItembyString(String [] array) {
        String urls = "";
        for (String s : array) {
            urls += ("," + s);
        }
        if (urls.length() > 1)
            return urls.substring(1);
        return urls;
    }
    /**
     *
     * @param list
     * @return 拼接图片url
     */
    public static String getImageItembyString(List<ImageItem> list ) {
        String urls = "";
        for (ImageItem item : list) {
            urls += ("," + item.path);
        }
        if (urls.length() > 1)
            return urls.substring(1);
        return urls;
    }
    }
