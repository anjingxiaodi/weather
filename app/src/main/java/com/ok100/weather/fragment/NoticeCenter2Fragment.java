package com.ok100.weather.fragment;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.imagepicker.view.GridSpacingItemDecoration;
import com.ok100.weather.R;
import com.ok100.weather.adapter.NoticeCenter1adapter;
import com.ok100.weather.adapter.NoticeCenter2adapter;
import com.ok100.weather.base.BaseFragment;
import com.ok100.weather.bean.MyCityListBean;
import com.ok100.weather.bean.NoticeCenter1Bean;
import com.ok100.weather.bean.NoticeCenter2Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * description: 岗位助
 *
 * @autour: QianDongdong
 * date: 2017-12-6 16:43
 * update: 2017-12-6
 * version:
 */
public class NoticeCenter2Fragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {

    List<Map<String, String>> mlist = new ArrayList<>();
    @BindView(R.id.recycle_post_assistant)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    NoticeCenter2adapter mAdapter;

    public NoticeCenter2Fragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_postassistant;
    }

    @Override
    protected void init(Bundle savedInstanceState, View contentView) {
        mAdapter = new NoticeCenter2adapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.setLayoutManager(manager);
        int spacing = (int) getResources().getDimension(R.dimen.x30);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacing, true));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        initData();
    }

    private void initData() {
        mAdapter.setNewData(getListData());
    }

    private List<NoticeCenter2Bean> getListData() {
        List<NoticeCenter2Bean> noticeCenter2Beans = new ArrayList<>();
        NoticeCenter2Bean noticeCenter2Bean = new NoticeCenter2Bean();
        noticeCenter2Beans.add(noticeCenter2Bean);
        noticeCenter2Beans.add(noticeCenter2Bean);
        noticeCenter2Beans.add(noticeCenter2Bean);
        noticeCenter2Beans.add(noticeCenter2Bean);
        return noticeCenter2Beans;
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        ToastUtil.makeShortText(getActivity(), "不知道跳到哪儿");
//        Intent intent = new Intent(getActivity(), PostAssistantListActivity.class);
//        intent.putExtra("positionId", position + 1);
//        Map<String, String> map = mAdapter.getData().get(position);
//        intent.putExtra("title", map.get("name"));
//        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
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
