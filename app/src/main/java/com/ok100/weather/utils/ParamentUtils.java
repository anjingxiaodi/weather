package com.ok100.weather.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-4-24.
 */

public class ParamentUtils<K, V> {
    private Map<K, V> map;

    public ParamentUtils() {
        map = new HashMap<>();
    }

    public void put(K key, V value) {
        if (value == null || "".equals(value) || "null".equals(value.toString().toLowerCase()))
            return;

        map.put(key, value);
    }

    public Map<K, V> getParams() {
        return map;
    }
}
