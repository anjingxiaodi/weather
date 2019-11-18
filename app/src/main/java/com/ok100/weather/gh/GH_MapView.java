package com.ok100.weather.gh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.MapView;

/**
 * @author: gh
 * @description:
 * @date: 2019/11/16.
 * @from:
 */
public class GH_MapView extends MapView {
    public GH_MapView(Context context) {
        super(context);
    }

    public GH_MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GH_MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public GH_MapView(Context context, AMapOptions aMapOptions) {
        super(context, aMapOptions);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
         super.dispatchTouchEvent(ev);
        return true;
    }
}
