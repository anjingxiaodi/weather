package com.ok100.weather.gh;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ok100.weather.R;

/**
 * @author: gh
 * @description:
 * @date: 2019-11-14.
 * @from:
 */
public class GH_DefaultDialogFragment extends DialogFragment {

    public static void access(FragmentManager fragmentManager) {
        GH_DefaultDialogFragment dialog = new GH_DefaultDialogFragment();
        dialog.show(fragmentManager,"tag");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        //背景透明
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //点击背景不消失
        getDialog().setCanceledOnTouchOutside(false);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER; // 居中位置
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.AnimBottom);  //添加动画

        View view = inflater.inflate(R.layout.gh_dialogfragment_default, container, false);
        init(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.gravity = Gravity.CENTER;//对齐方式
            dialog.getWindow().setAttributes(attributes);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.6), (int) (dm.heightPixels * 0.4));
        }
    }

    private void init(View view) {
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_desc = view.findViewById(R.id.tv_desc);
        TextView tv_ok = view.findViewById(R.id.tv_ok);

        tv_title.setBackground(getResources().getDrawable(R.mipmap.life_suggest1));
        tv_title.setBackground(getResources().getDrawable(R.mipmap.life_suggest2));
        tv_title.setBackground(getResources().getDrawable(R.mipmap.life_suggest3));
        tv_title.setBackground(getResources().getDrawable(R.mipmap.life_suggest4));

        tv_ok.setBackground(getResources().getDrawable(R.drawable.gh_shape_dialogdefault_1));
        tv_ok.setBackground(getResources().getDrawable(R.drawable.gh_shape_dialogdefault_2));
        tv_ok.setBackground(getResources().getDrawable(R.drawable.gh_shape_dialogdefault_3));
        tv_ok.setBackground(getResources().getDrawable(R.drawable.gh_shape_dialogdefault_4));
    }

}
