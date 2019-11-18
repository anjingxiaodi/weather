package com.ok100.weather.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.JsonElement;
import com.ok100.weather.MainActivity;
import com.ok100.weather.R;
import com.ok100.weather.adapter.MyCityAdapter1;
import com.ok100.weather.adapter.MyCityAdapter2;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.bean.DataBean;
import com.ok100.weather.bean.DefultGridViewBean;
import com.ok100.weather.bean.MyCityListBean;
import com.ok100.weather.bean.WeatherBean;
import com.ok100.weather.utils.ListDataSave;
import com.ok100.weather.utils.SharePreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCityActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener {


    @BindView(R.id.tv_my_city)
    TextView mTvMyCity;
    @BindView(R.id.edittext_search)
    EditText editText;
    @BindView(R.id.textview_quxiao)
    TextView mTextviewQuxiao;
    @BindView(R.id.recycleview1)
    RecyclerView mRecycleview1;
    @BindView(R.id.recycleview2)
    RecyclerView mRecycleview2;
    @BindView(R.id.recycleview3)
    RecyclerView mRecycleview3;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.ll_recycle1)
    LinearLayout mLlRecycle1;
    @BindView(R.id.ll_recycle2)
    LinearLayout mLlRecycle2;

    MyCityAdapter1 myCityAdapter1;
    @BindView(R.id.iv_add_city)
    ImageView mIvAddCity;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_choose_city)
    ImageView mIvChooseCity;

    @Override
    public int getLayoutID() {
        return R.layout.activity_my_city;
    }

    @Override
    public void InitView() {
        getDefultWeather();
        myCityAdapter1 = new MyCityAdapter1();
        myCityAdapter1.setOnItemChildClickListener(this);
        mRecycleview1.setAdapter(myCityAdapter1);
        myCityAdapter1.setNewData(generateData());
        mRecycleview1.setLayoutManager(new LinearLayoutManager(MyCityActivity.this));
//        myCityAdapter1.setNestedScrollingEnabled(false);//禁止滑动

        mRecycleview2.setLayoutManager(new GridLayoutManager(MyCityActivity.this, 3));
        MyCityAdapter2 myPindaoAdapter2 = new MyCityAdapter2();
        myPindaoAdapter2.setNewData(DataBean.getHotCicy());
        mRecycleview2.setAdapter(myPindaoAdapter2);
        mRecycleview2.setNestedScrollingEnabled(false);//禁止滑动
        myPindaoAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DefultGridViewBean cityName = (DefultGridViewBean) adapter.getData().get(position);
                stataActivity(cityName.getName());
            }
        });


        mRecycleview3.setLayoutManager(new GridLayoutManager(MyCityActivity.this, 3));
        MyCityAdapter2 myPindaoAdapter3 = new MyCityAdapter2();
        myPindaoAdapter3.setNewData(getAdapterData2());
        mRecycleview3.setAdapter(myPindaoAdapter3);
        mRecycleview3.setNestedScrollingEnabled(false);//禁止滑动
        myPindaoAdapter3.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DefultGridViewBean cityName = (DefultGridViewBean) adapter.getData().get(position);
                stataActivity(cityName.getName());
            }
        });


        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mRlTitle.setVisibility(View.GONE);
                    mTvMyCity.setVisibility(View.GONE);
                    mRecycleview1.setVisibility(View.GONE);
                    mTextviewQuxiao.setVisibility(View.VISIBLE);
                    mLlRecycle1.setVisibility(View.VISIBLE);
                    //热门景区
//                    mLlRecycle2.setVisibility(View.VISIBLE);
                    editText.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
                } else {
                    mRlTitle.setVisibility(View.VISIBLE);
                    mTvMyCity.setVisibility(View.VISIBLE);
                    mRecycleview1.setVisibility(View.VISIBLE);
                    mTextviewQuxiao.setVisibility(View.GONE);
                    mLlRecycle1.setVisibility(View.GONE);
                    //热门景区
                    mLlRecycle2.setVisibility(View.GONE);
                    editText.setGravity(Gravity.CENTER_VERTICAL);
                }
            }
        });

        mRecycleview1.setNestedScrollingEnabled(false);
    }



    private List<DefultGridViewBean> getAdapterData2() {

        ArrayList<DefultGridViewBean> defultGridViewBeans = new ArrayList<>();
        DefultGridViewBean defultGridViewBean = new DefultGridViewBean("天津");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBeans.add(defultGridViewBean);
        return defultGridViewBeans;
    }



    @Override
    public void initListener() {
        mIvAddCity.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mIvChooseCity.setOnClickListener(this);
        mTextviewQuxiao.setOnClickListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState, View contentView) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add_city:
                myCityAdapter1.setHide(!myCityAdapter1.isHide());
                myCityAdapter1.notifyDataSetChanged();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.textview_quxiao:
                fangjiaodian(v);
                break;
            case R.id.iv_choose_city:
                mRlTitle.setVisibility(View.GONE);
                mTvMyCity.setVisibility(View.GONE);
                mRecycleview1.setVisibility(View.GONE);
                mTextviewQuxiao.setVisibility(View.VISIBLE);
                mLlRecycle1.setVisibility(View.VISIBLE);
                mLlRecycle2.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void fangjiaodian(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            assert v != null;
            hideSoftKeyboard(MyCityActivity.this);
            if (editText != null) {
                editText.clearFocus();
            }
        }
    }

    //    使editText点击外部时候失去焦点
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            View v = getCurrentFocus();
//            if (isShouldHideInput(v, ev)) {//点击editText控件外部
//                fangjiaodian(v);
//            }
//            return super.dispatchTouchEvent(ev);
//        }
//        // 必不可少，否则所有的组件都不会有TouchEvent了
//        return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);
//    }


    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            editText = (EditText) v;
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    /**
     * 隐藏软键盘(只适用于Activity，不适用于Fragment)
     */
    public static void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.iv_delete:
                //删除
                adapter.getData().remove(position);
                adapter.notifyDataSetChanged();
                MainActivity.weatherBeanList.remove(position);
//                dataSave.setDataList("javaBean", MainActivity.weatherBeanList);
                Log.e("weatherBeanList",MainActivity.weatherBeanList.size()+"AAAAAA");

                delectCityListener.setdelectCity(position);
                break;
        }
    }

    public static int MyCityResult =10001;
    public void stataActivity(String cityName) {
        Intent intent = new Intent();
        intent.putExtra("cityName", cityName); //将计算的值回传回去
        //通过intent对象返回结果，必须要调用一个setResult方法，
        //setResult(resultCode, data);第一个参数表示结果返回码，一般只要大于1就可以，但是
        setResult(MyCityResult, intent);
        finish();
    }

//    List<WeatherBean> weatherBeanList = new ArrayList<>();

    ListDataSave dataSave;
    private void getDefultWeather() {
        dataSave = new ListDataSave(MyCityActivity.this, "weather");
        MainActivity.weatherBeanList = dataSave.getDataList("javaBean");

    }

    private List<MyCityListBean> generateData() {
        ArrayList<MyCityListBean> myCityAdapter1s = new ArrayList<>();
        for(int i=0;i<MainActivity.weatherBeanList.size();i++){

            String name = MainActivity.weatherBeanList.get(i).getName();
            MyCityListBean myCityListBean = new MyCityListBean(name);
            myCityAdapter1s.add(myCityListBean);
        }
        return myCityAdapter1s;

    }

    public void deleteCity(int position){
        MainActivity.weatherBeanList.remove(position);
        dataSave.setDataList("javaBean", MainActivity.weatherBeanList);

    }

    public interface delectCityListener{
        void setdelectCity(int cityPosition);
    }

    public static MyCityActivity.delectCityListener delectCityListener;

    public static void setDelectCityListener(MyCityActivity.delectCityListener delectCityListener){
        MyCityActivity.delectCityListener = delectCityListener;
    }
}
