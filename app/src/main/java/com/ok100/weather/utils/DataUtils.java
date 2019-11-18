package com.ok100.weather.utils;

import com.ok100.weather.bean.WeatherBean;

import java.util.ArrayList;

/**
 * @Description: This is DataUtils
 * @Author: QianDongDong
 * @Time: 2019/11/12 10:57
 * @Email: qdd2977@sina.com
 * @org: www.vanlian.cn 万科链家（北京）装饰有限公司
 */
public class DataUtils {
    public static ArrayList<WeatherBean> getDefultWeather(){
        ArrayList<WeatherBean> weatherBeans = new ArrayList<>();

        WeatherBean weatherBean1 = new WeatherBean("北京");
        WeatherBean weatherBean2 = new WeatherBean("天津");
        WeatherBean weatherBean3 = new WeatherBean("上海");
        WeatherBean weatherBean4 = new WeatherBean("张家口");
        WeatherBean weatherBean5 = new WeatherBean("承德");
        weatherBeans.add(weatherBean1);
        weatherBeans.add(weatherBean2);
        weatherBeans.add(weatherBean3);
        weatherBeans.add(weatherBean4);
        weatherBeans.add(weatherBean5);
        return weatherBeans;
    }
}
