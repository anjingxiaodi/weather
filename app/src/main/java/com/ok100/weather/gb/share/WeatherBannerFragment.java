package com.ok100.weather.gb.share;

import android.graphics.Bitmap;
import android.view.View;

import com.ok100.weather.R;


public class WeatherBannerFragment extends BaseFragment {
    private Bitmap url;
    private ImageAdapterHView pic_img;

    public static WeatherBannerFragment getInstance(Bitmap url) {
        WeatherBannerFragment weatherBannerFragment = new WeatherBannerFragment();
        weatherBannerFragment.url = url;
        return weatherBannerFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_weather_banner_layout;
    }

    @Override
    public void init(View view) {
        pic_img = view.findViewById(R.id.pic_img);
//        ImageUtils.loadImageMemory(mContext, url, pic_img);
        pic_img.setImageBitmap(url);
    }
}
