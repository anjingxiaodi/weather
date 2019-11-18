package com.ok100.weather.bean;

/**
 * Created by Administrator on 2017/12/11.
 */

public class DefultGridViewBean {
 private String name ;
 private String imageUlr ;
 private int imageUlrRes ;
 private boolean isClick ;

    public DefultGridViewBean(String name, int imageUlrRes) {
        this.name = name;
        this.imageUlrRes = imageUlrRes;
    }

    public int getImageUlrRes() {
        return imageUlrRes;
    }

    public void setImageUlrRes(int imageUlrRes) {
        this.imageUlrRes = imageUlrRes;
    }

    public DefultGridViewBean() {
    }

    public DefultGridViewBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUlr() {
        return imageUlr;
    }

    public void setImageUlr(String imageUlr) {
        this.imageUlr = imageUlr;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }
}
