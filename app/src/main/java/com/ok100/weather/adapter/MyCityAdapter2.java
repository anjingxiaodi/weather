package com.ok100.weather.adapter;
import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ok100.weather.R;
import com.ok100.weather.bean.DefultGridViewBean;
import com.ok100.weather.bean.MainSpotClickBean;

public class MyCityAdapter2 extends BaseQuickAdapter<DefultGridViewBean, BaseViewHolder> {

    private boolean isSubhead;

    public MyCityAdapter2() {
        super(R.layout.my_city_list2);
    }


    @Override
    protected void convert(BaseViewHolder helper, DefultGridViewBean item) {
        helper.setText(R.id.tv_name,item.getName());
//        helper.setVisible(R.id.tv_subhead_pop_project_item, isSubhead);
//        TextView textView = helper.getView(R.id.tv_name);
//        if(item.isClick()){
//            textView.setTextColor(Color.parseColor("#ffffff"));
//        }else {
//            textView.setTextColor(Color.parseColor("#000000"));
//        }



    }
}
