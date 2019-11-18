package com.ok100.weather.utils;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiandd on 2017-9-7.
 */

public class MapUtils {
    private static final String TAG = "MapUtils";
    /**
     * javaBean 转 Map
     *
     * @param object 需要转换的javabean
     * @return 转换结果map
     * @throws Exception
     */
    public static Map<String, String> beanToMap(Object object) throws Exception {
        Map<String, String> map = new HashMap<String, String>();

        Class cls = object.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            String params = "";
            field.setAccessible(true);
            if (field.getType() == Integer.class) {
                Integer mField = (Integer) field.get(object);
                if (mField == null) {
                    continue;
                } else {
                    params = String.valueOf(mField);
                }
            } else if (field.getType() == String.class) {
                String mField = (String) field.get(object);
                if (TextUtils.isEmpty(mField) || mField == "null") {
                    continue;
                }
                params = mField;
            }
            map.put(field.getName(), params);
        }
        return map;
    }

    /**
     * @param map 需要转换的map
     * @param cls 目标javaBean的类对象
     * @return 目标类object
     * @throws Exception
     */
    public static Object mapToBean(Map<String, Object> map, Class cls) throws Exception {
        Object object = cls.newInstance();
        for (String key : map.keySet()) {
            Field temFiels = cls.getDeclaredField(key);
            temFiels.setAccessible(true);
            temFiels.set(object, map.get(key));
        }
        return object;
    }
}
