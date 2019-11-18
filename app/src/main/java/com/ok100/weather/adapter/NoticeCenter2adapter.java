package com.ok100.weather.adapter;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ok100.weather.R;
import com.ok100.weather.bean.MyCityListBean;
import com.ok100.weather.bean.NoticeCenter2Bean;

public class NoticeCenter2adapter extends BaseQuickAdapter<NoticeCenter2Bean, BaseViewHolder> {

    private boolean isHide = true;

    public boolean isHide() {
        return isHide;
    }

    public void setHide(boolean hide) {
        isHide = hide;
    }

    public NoticeCenter2adapter() {
        super(R.layout.notice_center_list2);
    }


    @Override
    protected void convert(BaseViewHolder helper, NoticeCenter2Bean item) {



    }
}
