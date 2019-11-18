package com.ok100.weather.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ok100.weather.R;
import com.ok100.weather.adapter.MainTodaySuggestAdapter;
import com.ok100.weather.adapter.MyPindaoAdapter;
import com.ok100.weather.bean.DepartmentListBean;
import com.ok100.weather.bean.MainSpotClickBean;
import com.ok100.weather.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;

public class SelectPicPopupWindowActivity extends Activity implements View.OnClickListener {

    private TextView tv_edit;
    private TextView iv_back;
    private RecyclerView recycleview_pindao;
    MyPindaoAdapter myPindaoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_pic_pop_dialog);
        tv_edit = (TextView) this.findViewById(R.id.tv_edit);
        recycleview_pindao = (RecyclerView) this.findViewById(R.id.recycleview_pindao);
        iv_back = (TextView) this.findViewById(R.id.iv_back);

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.gravity = Gravity.CENTER;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;//宽高可设置具体大小
         lp.height = WindowManager.LayoutParams.MATCH_PARENT-100;
        // lp.width  = 400;
//        lp.height = 400;
        getWindow().setAttributes(lp);


        //添加按钮监听
        tv_edit.setOnClickListener(this);
        iv_back.setOnClickListener(this);

        recycleview_pindao.setLayoutManager(new GridLayoutManager(SelectPicPopupWindowActivity.this, 4));

         myPindaoAdapter = new MyPindaoAdapter();
        myPindaoAdapter.setNewData(getAdapterData());
        recycleview_pindao.setAdapter(myPindaoAdapter);
        recycleview_pindao.setNestedScrollingEnabled(false);//禁止滑动

        myPindaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                itemTabListener.setItemPosition(position);
                finish();
            }
        });

    }

    private List<MainSpotClickBean> getAdapterData() {
        ArrayList<String> listdata = getIntent().getStringArrayListExtra("listdata");
        ArrayList<MainSpotClickBean> mainSpotClickBeans = new ArrayList<>();
        for(int i= 0;i<listdata.size();i++){
            MainSpotClickBean mainSpotClickBean = new MainSpotClickBean(listdata.get(i));
            mainSpotClickBeans.add(mainSpotClickBean);
        }
        return mainSpotClickBeans;
    }

    //实现onTouchEvent触屏函数但点击屏幕时销毁本Activity
    @Override
    public boolean onTouchEvent(MotionEvent event){
//        finish();
        return true;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_edit:
                break;
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
//        finish();
    }


   public static MainFragment.setItemTabListener itemTabListener;
    public void setImteTabListener(MainFragment.setItemTabListener itemTabListener){
        this.itemTabListener=itemTabListener;
    }

}