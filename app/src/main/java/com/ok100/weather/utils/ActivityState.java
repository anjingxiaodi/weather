package com.ok100.weather.utils;

import android.app.Activity;

/**
 * Created by qdd on 2017/12/27.
 */

public interface ActivityState {
    /**
     * 得到当前Activity
     * @return
     */
    Activity current();

    /**
     * 任务栈中Activity的总数
     * @return
     */
    int count();
    /**
     * 判断应用是否处于前台，即是否可见
     * @return
     */
    boolean isFront();
}
