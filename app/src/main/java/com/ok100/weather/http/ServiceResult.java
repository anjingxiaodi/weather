package com.ok100.weather.http;

/**
 * Created by qdd2977 on 2016/7/18.
 */
public interface ServiceResult<T> {

     void onSuccess(T t);

     void onFailed(String msg);

}