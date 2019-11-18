package com.ok100.weather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ok100.weather.R;
import com.ok100.weather.adapter.SmallToolsAdapter;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.bean.DataBean;
import com.ok100.weather.bean.DefultGridViewBean;
import com.ok100.weather.gh.MineCenterActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInofActivity extends BaseActivity {


    @BindView(R.id.recycleview)
    RecyclerView mRecycleview;
    @BindView(R.id.recycleview1)
    RecyclerView mRecycleview1;
    @BindView(R.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R.id.iv_qiandao)
    TextView mIvQiandao;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_xiaoxi)
    TextView mTvXiaoxi;
    @BindView(R.id.tv_zhuti)
    TextView mTvZhuti;
    @BindView(R.id.tv_yijian)
    TextView mTvYijian;
    @BindView(R.id.ll_notice_more)
    LinearLayout mLlNoticeMore;
    @BindView(R.id.iv_goto_maincenter)
    ImageView mIvGotoMaincenter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_user_inof;
    }

    @Override
    public void InitView() {

    }

    @Override
    public void initListener() {
        mIvQiandao.setOnClickListener(this);
        mIvSetting.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mTvXiaoxi.setOnClickListener(this);
        mTvZhuti.setOnClickListener(this);
        mTvYijian.setOnClickListener(this);
        mLlNoticeMore.setOnClickListener(this);
        mIvGotoMaincenter.setOnClickListener(this);

    }

    @Override
    public void initData(Bundle savedInstanceState, View contentView) {
        mRecycleview.setLayoutManager(new GridLayoutManager(UserInofActivity.this, 4));
        SmallToolsAdapter myPindaoAdapter = new SmallToolsAdapter();
        myPindaoAdapter.setNewData(DataBean.getUserAdapter());
        mRecycleview.setAdapter(myPindaoAdapter);
        mRecycleview.setNestedScrollingEnabled(false);//禁止滑动

        mRecycleview1.setLayoutManager(new GridLayoutManager(UserInofActivity.this, 4));
        SmallToolsAdapter myPindaoAdapter1 = new SmallToolsAdapter();
        myPindaoAdapter1.setNewData(DataBean.getUserAdapter1());
        mRecycleview1.setAdapter(myPindaoAdapter);
        mRecycleview1.setNestedScrollingEnabled(false);//禁止滑动
    }



    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.iv_qiandao:
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_setting:
                intent = new Intent(UserInofActivity.this, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_xiaoxi:
                intent = new Intent(UserInofActivity.this, NoticeCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_zhuti:
                intent = new Intent(UserInofActivity.this, ZhutiImgeActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_yijian:
                intent = new Intent(UserInofActivity.this, NoticeCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_notice_more:
                intent = new Intent(UserInofActivity.this, NoticeCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_goto_maincenter:
                intent = new Intent(UserInofActivity.this, MineCenterActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
