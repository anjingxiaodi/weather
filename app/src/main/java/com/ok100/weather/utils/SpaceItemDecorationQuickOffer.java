package com.ok100.weather.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by qiandd on 2017-12-8.
 */

public class SpaceItemDecorationQuickOffer extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceItemDecorationQuickOffer(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            outRect.top = space;
//        } else {
//            outRect.left = 0;
//            outRect.right = 0;
//            outRect.bottom = 0;
//            outRect.top = 0;
//        }
    }
}
