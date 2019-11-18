package com.ok100.weather.http;

/**
 * @author QianDongDong
 * @Description ${类描述}
 * @Time 2016-08-31 17:13
 */
public interface ReturnDataView<T> {
    void returnData(String responseCode, T t);

    void showError(String msg);
}
