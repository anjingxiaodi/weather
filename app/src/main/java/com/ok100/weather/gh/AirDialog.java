package com.ok100.weather.gh;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.ok100.weather.R;
import com.ok100.weather.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author: gh
 * @description:
 * @date: 2019-11-07.
 * @from:
 */
public class AirDialog extends Dialog {

    @BindView(R.id.tablayout)
    CommonTabLayout tablayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private FragmentActivity activity;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] titlelist = new String[]{"周一", "周二", "周三", "周四", "周五"};

    public static void access(FragmentActivity context) {
        Dialog dialog = new AirDialog(context);
        //去掉标题线
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //背景透明
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //点击背景不消失
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER; // 居中位置
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.AnimBottom);  //添加动画
    }

    public AirDialog(@NonNull Context context) {
        super(context);
        activity = (BaseActivity) context;
    }

    public AirDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected AirDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }

    private ArrayList<Fragment> fragmentList;

    private void init() {
        setContentView(R.layout.gh_fragment_air);

        setCanceledOnTouchOutside(false); // 点击屏幕Dialog以外的地方是否消失

        fragmentList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
//            fragment = new NoticeMainFragment1();
//            Bundle args = new Bundle();
//            args.putString("departmentId", i + "");
//            fragment.setArguments(args);
            fragmentList.add(AirItemFragment.getInstance("", ""));
            fragmentList.add(AirItemFragment.getInstance("", ""));
            fragmentList.add(AirItemFragment.getInstance("", ""));
            fragmentList.add(AirItemFragment.getInstance("", ""));
            mTabEntities.add(new TabEntity(titlelist[i]));
        }


        tablayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewPager);
//      无需编写适配器，一行代码关联TabLayout与ViewPager
//        tablayout.setViewPager(viewPager, titlelist, activity, fragmentList);


        tablayout.setTabData(mTabEntities);

        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(activity.getSupportFragmentManager()) {
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
        tablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        viewPager.setCurrentItem(1);
    }

}
