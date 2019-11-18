package com.ok100.weather.bean;

/**
 * Created by Administrator on 2017/12/11.
 */

public class NoticeCenter1Bean {
    public NoticeCenter1Bean() {
    }

    public String getTitleame() {
        return titleame;
    }

    public void setTitleame(String titleame) {
        this.titleame = titleame;
    }

    private String titleame ;
    public NoticeCenter1Bean(String titleame){
        this.titleame = titleame;
    }

}
