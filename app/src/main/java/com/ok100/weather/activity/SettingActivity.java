package com.ok100.weather.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ok100.weather.MainActivity;
import com.ok100.weather.R;
import com.ok100.weather.adapter.MianSpotAdapter;
import com.ok100.weather.adapter.SettingAdapter;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.bean.DataBean;
import com.ok100.weather.bean.SettingBean;
import com.ok100.weather.utils.ActivityBarSettingUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity {


    @BindView(R.id.recycleview)
    RecyclerView mRecycleview;

    private SettingAdapter settingAdapter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_setting;
    }

    @Override
    public void InitView() {
        setTitle("设置", true, TITLE_TYPE_IMG, R.mipmap.back_left_hei, false, TITLE_TYPE_IMG, R.mipmap.ic_launcher);
        registerBack();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SettingActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleview.setLayoutManager(linearLayoutManager);
        settingAdapter = new SettingAdapter(SettingActivity.this);
        settingAdapter.setNewData(DataBean.getSettingData());
        mRecycleview.setAdapter(settingAdapter);
        ActivityBarSettingUtils.setAndroidNativeLightStatusBar(SettingActivity.this ,true);
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
