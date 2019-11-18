package com.ok100.weather.gh;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.ok100.weather.R;

import java.util.ArrayList;

/**
 * @author: gh
 * @description:
 * @date: 2019-11-07.
 * @from:
 */
public class AirDialogFragment extends DialogFragment {

    TabLayout tablayout;
    ViewPager viewPager;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] titlelist = new String[]{"周一\n11-8", "周二", "周三", "周四", "周五"};
    private ArrayList<Fragment> fragmentList;

    public static void access(FragmentManager fragmentManager) {
        AirDialogFragment dialog = new AirDialogFragment();
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


        View view = inflater.inflate(R.layout.gh_dialogfragment_air, container, false);
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
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.8), (int) (dm.heightPixels * 0.8));
        }
    }

    private void init(View view) {
        tablayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewPager);


        fragmentList = new ArrayList<>();

            fragmentList.add(AirFragment.getInstance("", ""));
            mTabEntities.add(new TabEntity(titlelist[0]));
            fragmentList.add(RankFragment.getInstance("", ""));
            mTabEntities.add(new TabEntity(titlelist[1]));

        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titlelist[position];
            }
        };
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.setupWithViewPager(viewPager, false);
        tablayout.getTabAt(0).setCustomView(R.layout.gh_tablayou_view_air);
        tablayout.getTabAt(1).setCustomView(R.layout.gh_tablayou_view_air);
        ((TextView) (tablayout.getTabAt(0).getCustomView())).setText("详细数据");
        ((TextView) (tablayout.getTabAt(1).getCustomView())).setText("实时监测点");
        tablayout.getTabAt(0).select();
    }

}
