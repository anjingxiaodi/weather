package com.ok100.weather.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @Description: This is MyNestedScrollView
 * @Author: QianDongDong
 * @Time: 2019/11/9 13:07
 * @Email: qdd2977@sina.com
 * @org: www.vanlian.cn 万科链家（北京）装饰有限公司
 */
public class MyNestedScrollView extends NestedScrollView {
    private boolean isLanjie =false;

    public boolean isLanjie() {
        return isLanjie;
    }

    public void setLanjie(boolean lanjie) {
        isLanjie = lanjie;
    }

    public MyNestedScrollView(Context context) {
        super(context);
    }

    public MyNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch(ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //必须返回false，否则子控件永远无法拿到焦点
                if(isLanjie) {
                    return false;
//                    return super.onInterceptTouchEvent(ev);
                } else {
                    return super.onInterceptTouchEvent(ev);
                }
            case MotionEvent.ACTION_MOVE:
                if(isLanjie) {
                    return false;
//                    return super.onInterceptTouchEvent(ev);
                } else {
                    return super.onInterceptTouchEvent(ev);
                }
            case MotionEvent.ACTION_UP:
                //必须返回false,否则子控件永远无法拿到焦点
                if(isLanjie) {
                    return false;
//                    return super.onInterceptTouchEvent(ev);
                } else {
                    return super.onInterceptTouchEvent(ev);
                }
            default:
                return super.onInterceptTouchEvent(ev);
        }

    }

}
