package com.ok100.weather.adapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ok100.weather.R;
import com.ok100.weather.bean.MainSpotClickBean;

import me.zhouzhuo.zzweatherview.WeatherModel;

public class MyPindaoAdapter extends BaseQuickAdapter<MainSpotClickBean, BaseViewHolder> {

    private boolean isSubhead;

    public MyPindaoAdapter() {
        super(R.layout.my_pindao_list);
    }


    @Override
    protected void convert(BaseViewHolder helper, MainSpotClickBean item) {
//        helper.setVisible(R.id.tv_subhead_pop_project_item, isSubhead);
//        helper.setBackgroundColor(R.id.tv_subhead_pop_project_item, item.isCheck() ? 0xFFFAFAFA : 0xFFFFFFFF);
//
        helper.setText(R.id.tv_title ,item.getName());
//        if(item.isClick()){
//            helper.setBackgroundRes(R.id.iv_notice_main_choose_state ,R.mipmap.notice_main_choose_true);
//        }else {
//            helper.setBackgroundRes(R.id.iv_notice_main_choose_state ,R.mipmap.notice_main_choose_false);
//        }

    }
}
