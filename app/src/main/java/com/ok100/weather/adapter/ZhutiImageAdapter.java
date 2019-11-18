package com.ok100.weather.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ok100.weather.R;
import com.ok100.weather.bean.DefultGridViewBean;
import com.ok100.weather.bean.NoticeCenter1Bean;

public class ZhutiImageAdapter extends BaseQuickAdapter<DefultGridViewBean, BaseViewHolder> {

    private boolean isHide = true;

    public boolean isHide() {
        return isHide;
    }

    public void setHide(boolean hide) {
        isHide = hide;
    }

    public ZhutiImageAdapter() {
        super(R.layout.zhuti_image_adapter);
    }


    @Override
    protected void convert(BaseViewHolder helper, DefultGridViewBean item) {
        helper.setBackgroundRes(R.id.iv_bg,item.getImageUlrRes());
        helper.setText(R.id.tv_name,item.getName());
    }
}
