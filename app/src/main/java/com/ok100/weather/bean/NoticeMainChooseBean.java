package com.ok100.weather.bean;

/**
 * Created by Administrator on 2017-2-15.
 */

public class NoticeMainChooseBean {
    private String name;
    private Boolean state;

    public NoticeMainChooseBean(String name, Boolean state) {
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "NoticeMainChooseBean{" +
                "name='" + name + '\'' +
                ", state=" + state +
                '}';
    }
}
