package com.ok100.weather.gb.share;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class ImageAdapterHView extends ImageView {

    public ImageAdapterHView(Context context) {
        super(context);
    }

    public ImageAdapterHView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageAdapterHView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable d = getBackground();
        if (d != null) {
            int h = MeasureSpec.getSize(heightMeasureSpec);
            int w = h * d.getIntrinsicWidth() / d.getIntrinsicHeight();
            setMeasuredDimension(w, h);
        } else
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
