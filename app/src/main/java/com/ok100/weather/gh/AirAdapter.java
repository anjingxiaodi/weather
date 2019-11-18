package com.ok100.weather.gh;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ok100.weather.R;

public class AirAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public AirAdapter() {
        super(R.layout.gh_item_air);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_position, helper.getAdapterPosition() + "")
                .setText(R.id.tv_title, "重庆")
                .setText(R.id.tv_no, "19")
                .setText(R.id.tv_level, "优");
    }
}
