package com.ok100.weather.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import android.view.View;

import com.ok100.weather.R;
import com.ok100.weather.adapter.PagerAdapter;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.base.BaseFragment;
import com.ok100.weather.base.BaseViewPager;
import com.ok100.weather.fragment.NoticeCenter1Fragment;
import com.ok100.weather.fragment.NoticeCenter2Fragment;
import com.ok100.weather.utils.ActivityBarSettingUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoticeCenterActivity extends BaseActivity {


    @BindView(R.id.tabLayout_dataStatistics)
    TabLayout tabLayout;
    @BindView(R.id.tablayout_bottmo_line)
    View mTablayoutBottmoLine;
    @BindView(R.id.baseViewPager_dataStatistics)
    BaseViewPager mBaseViewPager;

    private List<BaseFragment> fragments = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private PagerAdapter mPagerAdapter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_notice_center;
    }

    @Override
    public void InitView() {
        setTitle("消息中心", true, TITLE_TYPE_IMG, R.mipmap.back_left_hei, false, TITLE_TYPE_IMG, R.mipmap.ic_launcher);
        registerBack();
        titleList.add("我的消息");
        titleList.add("热门活动");
        //0为全部
        fragments.add(new NoticeCenter1Fragment());
        fragments.add( new NoticeCenter2Fragment());
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(1)));
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), fragments, titleList);
        mBaseViewPager.setCanscroll(true);
        mBaseViewPager.setAdapter(mPagerAdapter);
        mBaseViewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(mBaseViewPager);
        ActivityBarSettingUtils.setAndroidNativeLightStatusBar(NoticeCenterActivity.this ,true);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData(Bundle savedInstanceState, View contentView) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
