package com.ok100.weather.adapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ok100.weather.R;
import com.ok100.weather.bean.DefultGridViewBean;
import com.ok100.weather.bean.MainSpotClickBean;

public class SmallToolsAdapter extends BaseQuickAdapter<DefultGridViewBean, BaseViewHolder> {

    private boolean isSubhead;

    public SmallToolsAdapter() {
        super(R.layout.small_tools_list);
    }


    @Override
    protected void convert(BaseViewHolder helper, DefultGridViewBean item) {
        helper.setText(R.id.tv_title ,item.getName());
//        helper.setVisible(R.id.tv_subhead_pop_project_item, isSubhead);
//        helper.setBackgroundColor(R.id.tv_subhead_pop_project_item, item.isCheck() ? 0xFFFAFAFA : 0xFFFFFFFF);
//
//        helper.setText(R.id.tv_notice_main_choose_name ,item.getName());
//        if(item.isClick()){
//            helper.setBackgroundRes(R.id.iv_notice_main_choose_state ,R.mipmap.notice_main_choose_true);
//        }else {
//            helper.setBackgroundRes(R.id.iv_notice_main_choose_state ,R.mipmap.notice_main_choose_false);
//        }

    }
}
