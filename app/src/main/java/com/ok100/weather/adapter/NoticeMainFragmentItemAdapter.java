package com.ok100.weather.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ok100.weather.R;
import com.ok100.weather.bean.NoticeMainChooseBean;


public class NoticeMainFragmentItemAdapter extends BaseQuickAdapter<NoticeMainChooseBean, BaseViewHolder> {

    private boolean isSubhead;

    public NoticeMainFragmentItemAdapter() {
        super(R.layout.item_notice_main_chooset);
    }


    @Override
    protected void convert(BaseViewHolder helper, NoticeMainChooseBean item) {
//        helper.setVisible(R.id.tv_subhead_pop_project_item, isSubhead);
//        helper.setBackgroundColor(R.id.tv_subhead_pop_project_item, item.isCheck() ? 0xFFFAFAFA : 0xFFFFFFFF);

    }
}
