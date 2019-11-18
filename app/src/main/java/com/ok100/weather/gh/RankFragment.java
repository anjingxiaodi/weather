package com.ok100.weather.gh;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ok100.weather.R;
import com.ok100.weather.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: gh
 * @description:
 * @date: 2019-11-12.
 * @from:
 */
public class RankFragment extends BaseFragment {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    Unbinder unbinder;

    public static RankFragment getInstance(String type, String cityId) {
        RankFragment fragment = new RankFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TYPE", type);
        bundle.putString("cityId", cityId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.gh_fragment_rank;
    }

    @Override
    protected void init(Bundle savedInstanceState, View view) {
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
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

}
