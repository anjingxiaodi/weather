package com.ok100.weather.bean;

/**
 * Created by Administrator on 2017/12/11.
 */

public class SettingBean {
    private int type;
    private String title;

    public SettingBean(int type, String title) {
        this.type = type;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public SettingBean(int type) {
        this.type = type;
    }
}
