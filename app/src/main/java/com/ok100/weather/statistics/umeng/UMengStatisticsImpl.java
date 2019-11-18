package com.ok100.weather.statistics.umeng;

import android.content.Context;

import com.ok100.weather.statistics.IStatistics;
import com.umeng.analytics.MobclickAgent;


import java.util.Map;

/**
 * Created by qiandd on 2017-5-8.
 */

public class UMengStatisticsImpl implements IStatistics {

    @Override
    public void onResume(Context context) {
        MobclickAgent.onResume(context);
    }

    @Override
    public void onPause(Context context) {
        MobclickAgent.onPause(context);
    }

    @Override
    public void onKillProcess(Context context) {
        MobclickAgent.onKillProcess(context);
    }


    @Override
    public void onPageStart(String str) {
        MobclickAgent.onPageStart(str);
    }

    @Override
    public void onPageEnd(String str) {
        MobclickAgent.onPageEnd(str);
    }

    @Override
    public void openActivityDurationTrack(boolean b) {
        MobclickAgent.openActivityDurationTrack(b);
    }

    @Override
    public void enableEncrypt(boolean b) {
        MobclickAgent.enableEncrypt(b);
    }

    @Override
    public void onEvent(Context context, String eventId) {
        MobclickAgent.onEvent(context, eventId);
    }

    @Override
    public void onEvent(Context context, String eventId, Map map) {
        MobclickAgent.onEvent(context, eventId, map);
    }

    @Override
    public void setDebugMode(boolean b) {
        MobclickAgent.setDebugMode(b);
    }

    @Override
    public void setScenarioType(Context context, MobclickAgent.EScenarioType etype) {
        MobclickAgent.setScenarioType(context, etype);
    }

    @Override
    public void setCatchUncaughtExceptions(boolean isOpen) {
        MobclickAgent.setCatchUncaughtExceptions(isOpen);
    }
}
