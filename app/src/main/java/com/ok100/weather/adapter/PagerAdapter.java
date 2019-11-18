package com.ok100.weather.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;


import com.ok100.weather.base.BaseFragment;

import java.util.List;

/**
 * Created by qiandd on 2017-8-9.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    private  List<BaseFragment> mFList;
    private List<String> mTitle;


    public PagerAdapter(FragmentManager fm, List<BaseFragment> list, List<String> mTitle) {
        super(fm);
        mFList = list;
        this.mTitle = mTitle;
    }


    @Override
    public BaseFragment getItem(int position) {

        return mFList.get(position);
    }

    @Override
    public int getCount() {
        return mFList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {

        return mTitle.get(position % mTitle.size());
    }
}
