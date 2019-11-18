package com.ok100.weather.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ok100.weather.R;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.utils.ActivityBarSettingUtils;

public class NoticeDetatilActivity extends BaseActivity {


    @Override
    public int getLayoutID() {
        return R.layout.activity_notice_detatil;
    }

    @Override
    public void InitView() {
        setTitle("新闻详情", true, TITLE_TYPE_IMG, R.mipmap.back_left_hei, false, TITLE_TYPE_IMG, R.mipmap.ic_launcher);
        registerBack();
        ActivityBarSettingUtils.setAndroidNativeLightStatusBar(NoticeDetatilActivity.this ,true);
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
}
