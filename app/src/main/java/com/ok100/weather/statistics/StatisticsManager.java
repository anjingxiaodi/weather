package com.ok100.weather.statistics;


import com.ok100.weather.statistics.umeng.UMengStatisticsImpl;

/**
 * description: 统计管理类
 * @autour: QianDongdong
 * date: 2017-5-8 14:41 
 * update: 2017-5-8
 * version: 1.0
*/
public class StatisticsManager {

    private static IStatistics sIStatistics;


    public static IStatistics getInstance() {
        if (sIStatistics == null) {
            sIStatistics = new UMengStatisticsImpl();
        }
        return sIStatistics;
    }

}
