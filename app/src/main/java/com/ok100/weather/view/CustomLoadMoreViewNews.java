package com.ok100.weather.view;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.ok100.weather.R;


public class CustomLoadMoreViewNews extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.item_load_end_view_news;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
