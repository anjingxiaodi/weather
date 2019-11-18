package com.ok100.weather.gb.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.ok100.weather.R;
import com.ok100.weather.gb.share.CommonFragmentAdapter;
import com.ok100.weather.gb.share.UIUtil;
import com.ok100.weather.gb.share.WeatherBannerFragment;
import com.ok100.weather.gb.share.transformer.ScaleInTransformer;
import com.ok100.weather.gb.stickercamera.app.camera.CameraManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;



public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Bitmap> urlBeanList = new ArrayList<>();
    private List<Fragment> mFragment = new ArrayList<>();
    private ViewPager share_vp;
    private CommonFragmentAdapter commonFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    private void initView() {
        findViewById(R.id.cance_rela).setOnClickListener(this);
        findViewById(R.id.exchange_album_rela).setOnClickListener(this);
        share_vp = findViewById(R.id.share_vp);
    }

    private void initData() {

             /*  urlBeanList.add(create("https://b-ssl.duitang.com/uploads/item/201704/05/20170405163026_dGBSi.thumb.700_0.jpeg"));
               urlBeanList.add(returnBitMap("https://b-ssl.duitang.com/uploads/item/201704/05/20170405163026_dGBSi.thumb.700_0.jpeg"));
               urlBeanList.add(returnBitMap("https://b-ssl.duitang.com/uploads/item/201704/05/20170405163026_dGBSi.thumb.700_0.jpeg"));
               urlBeanList.add(returnBitMap("https://b-ssl.duitang.com/uploads/item/201703/29/20170329143154_CfJta.jpeg"));
               urlBeanList.add(returnBitMap("https://b-ssl.duitang.com/uploads/blog/201512/09/20151209173914_v4Enx.jpeg"));
               urlBeanList.add(returnBitMap("https://b-ssl.duitang.com/uploads/item/201704/05/20170405163026_dGBSi.thumb.700_0.jpeg")); */


        Bitmap bmp1=BitmapFactory.decodeResource(getResources(), R.mipmap.main_bg);
        Bitmap bmp2=BitmapFactory.decodeResource(getResources(), R.mipmap.main_bg1);
        Bitmap bmp3=BitmapFactory.decodeResource(getResources(), R.mipmap.main_bg2);
        urlBeanList.add(bmp1);
        urlBeanList.add(bmp2);
        urlBeanList.add(bmp3);
        urlBeanList.add(bmp1);
        urlBeanList.add(bmp2);
        urlBeanList.add(bmp3);


//             urlBeanList.add(create("/storage/emulated/0/Download/2008315204223450_2..jpg"));
//             urlBeanList.add(create("/storage/emulated/0/Download/2008315204223450_2..jpg"));
//             urlBeanList.add(create("/storage/emulated/0/Download/2008315204223450_2..jpg"));
//             urlBeanList.add(create("/storage/emulated/0/Download/2008315204223450_2..jpg"));
//             urlBeanList.add(create("/storage/emulated/0/Download/2008315204223450_2..jpg"));
//             urlBeanList.add(create("/storage/emulated/0/Download/2008315204223450_2..jpg"));
        for (Bitmap s : urlBeanList) {
            mFragment.add(WeatherBannerFragment.getInstance(s));
        }
        setPagerMargin(UIUtil.dip2px(this, 35),
                UIUtil.dip2px(this, 10));
        share_vp.setPageTransformer(true, new ScaleInTransformer());
        commonFragmentAdapter = new CommonFragmentAdapter(mFragment, this.getSupportFragmentManager(), share_vp);
        commonFragmentAdapter.setOnExtraPageChangeListener(new CommonFragmentAdapter.OnExtraPageChangeListener() {
            public void onExtraPageSelected(int position) {
            }
        });
    }

    public void setPagerMargin(int viewMargin, int pagerMargin) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) share_vp.getLayoutParams();
        layoutParams.setMargins(viewMargin, 0, viewMargin, 0);
        share_vp.setPageMargin(pagerMargin);
    }

    public static Bitmap create(String path){
        Bitmap bitmap = BitmapFactory.decodeFile( path);
        return bitmap;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.cance_rela:
                finish();
                break;

            case R.id.exchange_album_rela:
                CameraManager.getInst().openCamera(HomeActivity.this);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(Bitmap bitmap) {
        Log.e("bst==","---------------------------------1111111111");
        commonFragmentAdapter.addFragment(WeatherBannerFragment.getInstance(bitmap));
    }
}
