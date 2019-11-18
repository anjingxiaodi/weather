package com.ok100.weather.statistics;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;

import java.util.Map;

/**
 * Created by qiandd on 2017-5-8.
 * 统计接口
 */

public interface IStatistics {

    /**
     * //统计时长
     *
     * @param context
     */
    void onResume(Context context);

    void onPause(Context context);

    /**
     * 退出程序保存数据
     *
     * @param context
     */
    void onKillProcess(Context context);

    /**
     * 统计页面开始
     *
     * @param str 自定义页面名
     */
    void onPageStart(String str);

    /**
     * 统计页面结束
     *
     * @param str 自定义页面名
     */
    void onPageEnd(String str);

    /**
     * //禁止默认的页面统计方式，这样将不会再自动统计Activity。
     *
     * @param b false禁止默认的页面统计方式，这样将不会再自动统计Activity。
     */
    void openActivityDurationTrack(boolean b);


    /**
     * 日志加密设置
     *
     * @param b 如果enable为true，SDK会对日志进行加密。加密模式可以有效防止网络攻击，提高数据安全性。
     */
    void enableEncrypt(boolean b);//


    /**
     * @param context 指当前的Activity
     * @param eventId 为当前统计的事件ID
     */
    void onEvent(Context context, String eventId);

    /**
     * 统计点击行为各属性被触发的次数
     *
     * @param context 指当前的Activity
     * @param eventId 为当前统计的事件ID
     * @param map     为当前事件的属性和取值
     */
    void onEvent(Context context, String eventId, Map map);


    /**
     * @param b 是否开启debug模式 使用集成测试之后，所有测试数据不会进入应用正式的统计后台，只能在“管理--集成测试--实时日志”里查看，您不必再担心因为测试而导致的数据污染问题，让数据更加真实有效的反应用户使用情况。
     */
    void setDebugMode(boolean b);

    /**
     *
     *  新增场景类型设置接口
     * EScenarioType. E_UM_NORMAL　　普通统计场景类型
     * <p>
     * EScenarioType. E_UM_GAME     　　游戏场景类型
     * <p>
     * EScenarioType. E_UM_ANALYTICS_OEM  统计盒子场景类型
     * <p>
     * EScenarioType. E_UM_GAME_OEM      　 游戏盒子场景类型
     *
     * @param context
     * @param etype
     */
    void setScenarioType(Context context, MobclickAgent.EScenarioType etype);

    /**
     *
     * @param isOpen 是否开启错误统计 默认开启状态
     */
    void setCatchUncaughtExceptions(boolean isOpen);

}
