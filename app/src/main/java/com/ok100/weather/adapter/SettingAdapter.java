package com.ok100.weather.adapter;
import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.ok100.weather.R;
import com.ok100.weather.bean.MainSpotClickBean;
import com.ok100.weather.bean.NoticeMainListBean;
import com.ok100.weather.bean.SettingBean;

public class SettingAdapter extends BaseQuickAdapter<SettingBean, BaseViewHolder> {

    private Context context;
    public SettingAdapter(Context context) {
        super(null);
        this.context = context;
        setMultiTypeDelegate(new MultiTypeDelegate<SettingBean>() {
            @Override
            protected int getItemType(SettingBean itemBean) {
                int plateType = itemBean.getType();
                if (plateType == 1) {
                    plateType = 1;
                } else if (plateType == 2) {
                    plateType = 2;
                }
                return plateType;
            }
        });

        getMultiTypeDelegate()
                .registerItemType(2, R.layout.item_setting_list2)
                .registerItemType(1, R.layout.item_setting_list1);

    }


    @Override
    protected void convert(BaseViewHolder helper, SettingBean item) {
        int plateType = item.getType();
        if (plateType == 1) {

        } else if (plateType == 2) {
            helper.setText(R.id.tv_name,item.getTitle());
        }
//        helper.setVisible(R.id.tv_subhead_pop_project_item, isSubhead);
//        ImageView textView = helper.getView(R.id.tv_name);
//        if(item.isClick()){
//            textView.setImageResource(R.drawable.shape_white_yuan);
//        }else {
//            textView.setImageResource(R.drawable.shape_gray_yuan);
//        }



    }
}
