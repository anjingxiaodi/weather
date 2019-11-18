package com.ok100.weather.adapter;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ok100.weather.R;
import com.ok100.weather.bean.MainSpotClickBean;

import me.zhouzhuo.zzweatherview.WeatherModel;

public class MianSpotAdapter extends BaseQuickAdapter<MainSpotClickBean, BaseViewHolder> {

    private boolean isSubhead;

    public MianSpotAdapter() {
        super(R.layout.main_spot_list);
    }


    @Override
    protected void convert(BaseViewHolder helper, MainSpotClickBean item) {
//        helper.setVisible(R.id.tv_subhead_pop_project_item, isSubhead);
        ImageView textView = helper.getView(R.id.tv_name);
        if(item.isClick()){
            textView.setImageResource(R.drawable.shape_white_yuan);
        }else {
            textView.setImageResource(R.drawable.shape_gray_yuan);
        }



    }
}
