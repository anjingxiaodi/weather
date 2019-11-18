package com.ok100.weather.gh;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.ok100.weather.R;
import com.ok100.weather.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author: gh
 * @description:
 * @date: 2019-11-12.
 * @from:
 */
public class AirFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    @BindView(R.id.ll_national)
    LinearLayout llNational;
    @BindView(R.id.tv_switch_1)
    TextView tvSwitch1;
    @BindView(R.id.tv_switch_2)
    TextView tvSwitch2;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] titlelist = new String[]{"周一\n11-8", "周二", "周三", "周四", "周五"};
    private ArrayList<Fragment> fragmentList;

    public static AirFragment getInstance(String type, String cityId) {
        AirFragment fragment = new AirFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TYPE", type);
        bundle.putString("cityId", cityId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.gh_fragment_air;
    }

    @Override
    protected void init(Bundle savedInstanceState, View view) {
        tablayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewPager);

        fragmentList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            fragmentList.add(AirItemFragment.getInstance("", ""));
            mTabEntities.add(new TabEntity(titlelist[i]));
        }

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
        tablayout.getTabAt(0).setCustomView(R.layout.tablayou_view_housepk_left);
        tablayout.getTabAt(1).setCustomView(R.layout.tablayou_view_housepk_left);
        tablayout.getTabAt(2).setCustomView(R.layout.tablayou_view_housepk_left);
        tablayout.getTabAt(3).setCustomView(R.layout.tablayou_view_housepk_left);
        tablayout.getTabAt(4).setCustomView(R.layout.tablayou_view_housepk_left);
        tablayout.getTabAt(0).select();

        rvList.setLayoutManager(new LinearLayoutManager(getActivity()) );
        AirAdapter airAdapter = new AirAdapter();
        rvList.setAdapter(airAdapter);

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("");
        }

        airAdapter.setNewData(list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.tv_switch_1, R.id.tv_switch_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_switch_1:
                llNational.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_switch_2:
                llNational.setVisibility(View.GONE);
                break;
        }
    }
}
