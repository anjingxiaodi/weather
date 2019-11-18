package com.ok100.weather.contract;

import android.content.Context;


import com.ok100.weather.bean.WeatherTotalBean;
import com.ok100.weather.http.ServiceResult;

import java.util.Map;

/**
 * Created by qiandd on 2017-7-27.
 */

public class NoticeMainListContract {


    public interface View {
    }

    public interface Presenter {
        void getNoticeList(Context context, Map<String, String> map);
        void getTotalWeather(Context context, Map<String, String> map);
    }

    public interface Model {
        void getNoticeList(Context context, Map<String, String> map, ServiceResult<String> serviceResult);
        void getTotalWeather(Context context, Map<String, String> map, ServiceResult<WeatherTotalBean> serviceResult);

    }


}