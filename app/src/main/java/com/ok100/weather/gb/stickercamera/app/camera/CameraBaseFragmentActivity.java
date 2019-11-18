package com.ok100.weather.gb.stickercamera.app.camera;

import android.os.Bundle;

import com.ok100.weather.gb.stickercamera.base.BaseFragmentActivity;




public class CameraBaseFragmentActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CameraManager.getInst().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CameraManager.getInst().removeActivity(this);
    }
}
