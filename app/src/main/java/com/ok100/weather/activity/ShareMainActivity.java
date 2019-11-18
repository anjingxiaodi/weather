package com.ok100.weather.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;

import com.ok100.weather.R;
import com.ok100.weather.adapter.MainTodaySuggestAdapter;
import com.ok100.weather.adapter.Weather15MianAdapter;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.view.MyLinearLayoutManager;
import com.ok100.weather.view.MyNestedScrollView;
import com.ok100.weather.view.RecyclerScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhouzhuo.zzweatherview.AirLevel;
import me.zhouzhuo.zzweatherview.WeatherModel;

public class ShareMainActivity extends BaseActivity {

    @BindView(R.id.recycle)
    RecyclerView mRecycle;
    @BindView(R.id.scrollview)
    ScrollView mScrollview;

    @Override
    public int getLayoutID() {
        return R.layout.activity_share_main;
    }

    @Override
    public void InitView() {
        MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(ShareMainActivity.this);
        myLinearLayoutManager.setScrollEnabled(false);
        mRecycle.setLayoutManager(myLinearLayoutManager);

        MainTodaySuggestAdapter mainTodaySuggestAdapter = new MainTodaySuggestAdapter();
        mainTodaySuggestAdapter.setNewData(generateData());
        mRecycle.setAdapter(mainTodaySuggestAdapter);
//        mRecycle.setNestedScrollingEnabled(false);
//        mScrollview.setEnabled(false);
    }


    private List<WeatherModel> generateData() {
        ArrayList<WeatherModel> weatherModels = new ArrayList<>();

        //数据源
        WeatherModel model = new WeatherModel();
        model.setDate("12/07");//日期
        model.setWeek("昨天");  //星期
        model.setDayWeather("大雪"); //白天天气
        model.setDayTemp(11); //白天温度
        model.setNightTemp(5); //夜晚温度
        model.setNightWeather("晴"); //夜晚天气
        model.setWindOrientation("西南风"); //风向
        model.setWindLevel("3级"); //风级
        model.setAirLevel(AirLevel.EXCELLENT); //空气质量
        model.setDayPic(R.mipmap.ic_launcher); //白天天气图标(默认提供常用天气图标)
        model.setNightPic(R.mipmap.ic_launcher_round); //晚上天气图标(默认提供常用天气图标)
        weatherModels.add(model);

        WeatherModel mode2 = new WeatherModel();
        mode2.setDayTemp(11); //白天温度
        setDataWeather(mode2);
        WeatherModel mode3 = new WeatherModel();
        mode3.setDayTemp(27); //白天温度
        setDataWeather(mode3);
        WeatherModel mode4 = new WeatherModel();
        mode4.setDayTemp(34); //白天温度
        setDataWeather(mode4);
        WeatherModel mode5 = new WeatherModel();
        mode5.setDayTemp(21); //白天温度
        setDataWeather(mode5);
        WeatherModel mode6 = new WeatherModel();
        mode6.setDayTemp(-10); //白天温度
        setDataWeather(mode6);
        WeatherModel mode7 = new WeatherModel();
        mode7.setDayTemp(11); //白天温度
        setDataWeather(mode7);
        weatherModels.add(mode2);
        weatherModels.add(mode3);
        weatherModels.add(mode4);
        weatherModels.add(mode5);
        weatherModels.add(mode6);
        weatherModels.add(mode7);
        weatherModels.add(mode2);
        weatherModels.add(mode2);
        weatherModels.add(mode2);
        weatherModels.add(mode2);
        weatherModels.add(mode2);
        weatherModels.add(mode2);
        return weatherModels;
    }

    public void setDataWeather(WeatherModel model) {
        model.setDate("12/07");//日期
        model.setWeek("昨天");  //星期
        model.setDayWeather("大雪"); //白天天气
        model.setNightTemp(5); //夜晚温度
        model.setNightWeather("晴"); //夜晚天气
        model.setWindOrientation("西南风"); //风向
        model.setWindLevel("3级"); //风级
        model.setAirLevel(AirLevel.EXCELLENT); //空气质量
        model.setDayPic(R.mipmap.ic_launcher); //白天天气图标(默认提供常用天气图标)
        model.setNightPic(R.mipmap.ic_launcher_round); //晚上天气图标(默认提供常用天气图标)
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
