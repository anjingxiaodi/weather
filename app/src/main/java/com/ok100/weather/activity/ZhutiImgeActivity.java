package com.ok100.weather.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ok100.weather.MainActivity;
import com.ok100.weather.R;
import com.ok100.weather.adapter.NoticeCenter1adapter;
import com.ok100.weather.adapter.ZhutiImageAdapter;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.bean.DataBean;
import com.ok100.weather.bean.DefultGridViewBean;
import com.ok100.weather.utils.ActivityBarSettingUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhutiImgeActivity extends BaseActivity {


    @BindView(R.id.recycleview)
    RecyclerView mRecycleview;

    public ZhutiImageAdapter mAdapter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_zhuti_imge;
    }

    @Override
    public void InitView() {
        setTitle("主题广场", true, TITLE_TYPE_IMG, R.mipmap.back_left_hei, false, TITLE_TYPE_IMG, R.mipmap.ic_launcher);
        registerBack();
        mRecycleview.setLayoutManager(new GridLayoutManager(ZhutiImgeActivity.this, 3));
//        mRecyclerView.setLayoutManager(manager);
        mAdapter = new ZhutiImageAdapter();
        mRecycleview.setAdapter(mAdapter);
        mAdapter.setNewData(DataBean.getZhutiImageAdapter());

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DefultGridViewBean bean  = (DefultGridViewBean) adapter.getData().get(position);
                mSetZhutiBgListener.zhutiBgListener(bean.getImageUlrRes());
                finish();
            }
        });

        ActivityBarSettingUtils.setAndroidNativeLightStatusBar(ZhutiImgeActivity.this,true);
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
    public static MainActivity.SetZhutiBgListener mSetZhutiBgListener;

}
