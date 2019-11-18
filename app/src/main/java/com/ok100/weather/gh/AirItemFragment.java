package com.ok100.weather.gh;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ok100.weather.R;
import com.ok100.weather.base.BaseFragment;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: gh
 * @description:
 * @date: 2019-11-08.
 * @from:
 */
public class AirItemFragment extends BaseFragment {

    @BindView(R.id.sesame_view)
    NewCreditSesameView sesameView;
    Unbinder unbinder;

    private String type;
    private String cityId;

    private Random random = new Random();

    public static AirItemFragment getInstance(String type, String cityId) {
        AirItemFragment fragment = new AirItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TYPE", type);
        bundle.putString("cityId", cityId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.gh_fragment_air_item;
    }

    @Override
    protected void init(Bundle savedInstanceState, View contentView) {
        initBundle();
        initView();
        initData();
    }

    private void initBundle() {
        Bundle args = getArguments();
        if (args != null) {
            type = args.getString("TYPE");
            cityId = args.getString("cityId");
        }
    }

    private void initView() {

    }

    private void initData() {

    }

    @Override
    protected void lazyLoad() {
        setRefresh(true);
        int i = random.nextInt(950-351);
        sesameView.setSesameValues(i+351);
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
}
