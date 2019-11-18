package com.ok100.weather.utils;

import java.math.BigDecimal;

/**
 * @Description: This is DoubleUtils
 * @Author: QianDongDong
 * @Time: 2018/6/11 10:36
 * @Email: qdd2977@sina.com
 * @org: www.vanlian.cn 万科链家（北京）装饰有限公司
 */
public class DoubleUtils {
    private DoubleUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 两double相乘
     *
     * @param d1
     * @param d2
     * @return double
     */
    public static double multiply(double d1, double d2) {
        BigDecimal bigDecimal = new BigDecimal(d1);
        BigDecimal bigDecimal2 = new BigDecimal(d2);
        return bigDecimal.multiply(bigDecimal2).doubleValue();
    }

    /**
     * 两double相乘
     * @param s1
     * @param s2
     * @return
     */
    public static double multiply(String s1, String s2) {
        BigDecimal bigDecimal = new BigDecimal(s1);
        BigDecimal bigDecimal2 = new BigDecimal(s2);
        return bigDecimal.multiply(bigDecimal2).doubleValue();
    }


    /**
     * 保留小数
     *
     * @param d1    需要保留小数的原数据
     * @param scale 需要保留几位小数
     * @return
     */
    public static double keepDecimals(double d1, int scale) {
        BigDecimal bigDecimal = new BigDecimal(d1);
        return bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }

    /**
     * 保留两位小数
     *
     * @param d1
     * @return
     */
    public static double keepTwoDecimals(double d1) {
        return keepDecimals(d1, 2);
    }

}
