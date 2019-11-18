package com.ok100.weather.bean;

import java.io.Serializable;

/**
 * @Description: This is WeatherBean
 * @Author: QianDongDong
 * @Time: 2019/11/12 10:52
 * @Email: qdd2977@sina.com
 * @org: www.vanlian.cn 万科链家（北京）装饰有限公司
 */
public class WeatherBean implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeatherBean(String name) {
        this.name = name;
    }

    public WeatherBean() {
    }

    @Override
    public String toString() {
        return "WeatherBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
