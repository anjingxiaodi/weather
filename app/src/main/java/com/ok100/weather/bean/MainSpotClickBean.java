package com.ok100.weather.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/11.
 */

public class MainSpotClickBean {
    private String name ;
    private boolean isClick;

    public MainSpotClickBean(String name) {
        this.name = name;
    }

    public MainSpotClickBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }
}
