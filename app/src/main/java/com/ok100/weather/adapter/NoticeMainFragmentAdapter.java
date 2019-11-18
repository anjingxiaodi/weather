package com.ok100.weather.adapter;


import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.ok100.weather.R;
import com.ok100.weather.bean.NoticeMainListBean;


public class NoticeMainFragmentAdapter extends BaseQuickAdapter<NoticeMainListBean, BaseViewHolder> {

    private String keyWord;
    private Context context;

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public NoticeMainFragmentAdapter(Context context) {
        super(null);
        this.context = context;
        setMultiTypeDelegate(new MultiTypeDelegate<NoticeMainListBean>() {
            @Override
            protected int getItemType(NoticeMainListBean itemBean) {
                int plateType = itemBean.getType();
                if (plateType == 1) {
                    plateType = 1;
                } else if (plateType == 2) {
                    plateType = 2;
                } else if (plateType == 3) {
                    plateType = 3;
                }else if (plateType == 4) {
                    plateType = 4;
                }else if (plateType == 5) {
                    plateType = 5;
                }
                return plateType;
            }
        });

        getMultiTypeDelegate()
                .registerItemType(1, R.layout.item_notice_main1_fragment1)
                .registerItemType(2, R.layout.item_notice_main1_fragment2)
                .registerItemType(3, R.layout.item_notice_main1_fragment3)
                .registerItemType(4, R.layout.item_notice_main1_fragment1_top)
                .registerItemType(5, R.layout.item_notice_main1_fragment3_top);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final NoticeMainListBean item) {

        switch (helper.getItemViewType()) {
            case 1:
//                setData(helper, item);
//                ImageView imageview1 = helper.getView(R.id.iv_notice_main_list_pic);
//                Glide.with(context)
////                        .load("http://192.168.23.1:8081/sp/client/86f2f332095d4fd7b67a55c84bf1fd7c/2fa657ee9ed947ceb9d4cac2520d1c0a.jpg")
//                        .load(item.getImageUrl())
//                        .centerCrop()
//                        .crossFade()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(imageview1);
//                if(item.getStick()==0){
//                    helper.setVisible(R.id.tv_notice_main_list_top ,false);
//                }else {
//                    helper.setVisible(R.id.tv_notice_main_list_top ,true);
//                }

                break;
            case 2:
//                setData(helper, item);
//                ImageView imageview2 = helper.getView(R.id.iv_notice_main_list_pic);
//                Glide.with(context)
////                        .load("http://192.168.23.1:8081/sp/client/86f2f332095d4fd7b67a55c84bf1fd7c/2fa657ee9ed947ceb9d4cac2520d1c0a.jpg")
//                        .load(item.getImageUrl())
//                        .centerCrop()
//                        .crossFade()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(imageview2);
                break;
            case 3:
//                setData(helper, item);
//                helper.setText(R.id.tv_notice_main_list_content, item.getSubtitle());
                break;
            case 4:

                break;
            case 5:

                break;
        }
    }



    private SpannableStringBuilder changeColor(String result) {
        int sTextLength = keyWord.length();
        int start = result.indexOf(keyWord);
        SpannableStringBuilder styledText = new SpannableStringBuilder(result);
        if (!result.contains(keyWord)) {
            return styledText;
        }
        styledText.setSpan(
                new ForegroundColorSpan(Color.parseColor("#5788ff")),
                start,
                start + sTextLength,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return styledText;
    }

    private String isNoString(String string) {
        String s = TextUtils.isEmpty(string) ? "暂无" : string;
        return s;
    }

}
