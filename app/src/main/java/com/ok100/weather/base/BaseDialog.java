package com.ok100.weather.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * @author QianDongDong
 * @Description ${类描述}
 * @Time 2016-09-01 15:24
 * @email qdd2977@sina.com
 */
public abstract class BaseDialog extends DialogFragment {
    private View mContentView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (cancelable()) {
            getDialog().setCanceledOnTouchOutside(false);//点击外部不消失
        } else {
//            setCancelable(false);点击返回键也不能退出
            getDialog().setCanceledOnTouchOutside(true);//点击外部消失
        }
        mContentView = inflater.inflate(getLayoutID(), null, false);
//        ButterKnife.bind(this, mContentView);

        return mContentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init(savedInstanceState, mContentView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ButterKnife.unbind(this);
    }


    public boolean isShowing() {
        if (getDialog() == null) {
            return false;
        }
        return getDialog().isShowing();
    }

    protected void setAnimID(int animID) {
        getDialog().getWindow().getAttributes().windowAnimations = animID;
    }
    protected View findViewById(int id){
        return mContentView.findViewById(id);
    }

    /**
     * 设置点击右下角返回键能不能删除
     *
     * @return
     */
    protected abstract boolean cancelable();

    protected abstract int getLayoutID();

    protected abstract void init(Bundle savedInstanceState, View content);

}
