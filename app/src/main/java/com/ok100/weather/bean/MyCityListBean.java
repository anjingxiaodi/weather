package com.ok100.weather.bean;

/**
 * Created by Administrator on 2017/12/11.
 */

public class MyCityListBean {
    public String getTitleame() {
        return titleame;
    }

    public void setTitleame(String titleame) {
        this.titleame = titleame;
    }

    private String titleame ;
    public MyCityListBean(String titleame){
        this.titleame = titleame;
    }

}
