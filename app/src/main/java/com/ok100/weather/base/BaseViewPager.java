package com.ok100.weather.base;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author fanzhijie
 * @Description ${类描述}
 * @Time 2016-10-12 14:32
 */
public class BaseViewPager extends ViewPager {
    private boolean isCanScroll;
    public BaseViewPager(Context context) {
        super(context);
    }


    public BaseViewPager(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public void setCanscroll(boolean isCanScroll) {

        this.isCanScroll = isCanScroll;
    }


    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        // TODO Auto-generated method stub
        if (isCanScroll) {
            return super.onTouchEvent(arg0);
        } else {
            return false;
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isCanScroll) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }
}
