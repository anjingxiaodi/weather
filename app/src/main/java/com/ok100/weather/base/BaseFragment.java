package com.ok100.weather.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * @author fanzhijie
 * @Description ${类描述}
 * @Time 2016-10-12 13:51
 */
public abstract class BaseFragment extends Fragment {

    private View mContentView;
    //是否可见标志
    protected boolean isVisible;
    //初始化完成标志
    protected boolean isPrepare;

    /**
     * 获取当前Activity 的布局文件ID
     *
     * @return 布局文件ID
     */
    protected abstract int   getLayoutID();
    /**
     * Fragment 初始化
     *
     * @param savedInstanceState 状态存储Bundle 源生
     * @param contentView        当前fragment的布局View
     */
    protected abstract void init(Bundle savedInstanceState, View contentView);



    public Context getContext() {
        return ((BaseActivity) getActivity()).getContext();
    }

    protected View getContentView(){

        return mContentView;
    }

    protected View findViewById(int id){
        return mContentView.findViewById(id);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init(savedInstanceState, mContentView);
        isPrepare = true;
        isLazyLoad();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(getLayoutID(), null, false);
        ButterKnife.bind(this, mContentView);
        return mContentView;
    }


    @Override
    public void onDestroyView() {
        if(mContentView != null){
            mContentView = null;
        }
        super.onDestroyView();
    }

    /**
     * 如需懒加载，子类重写该方法
     */
    protected void lazyLoad(){}

    private void isLazyLoad(){
        //当可见时，并且初始化完成时，加载数据
        if(isPrepare && isVisible){
            //默认只加载一次
            isPrepare = false;
            lazyLoad();
        }
    }

    /**
     * 在这里实现Fragment数据的缓加载.
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }
    protected void onVisible(){
        isLazyLoad();
    }
    protected void onInvisible(){}

    /**
     * 设置再次进入是否需要刷新
     * @param isRefresh 刷新标志
     */
    protected void setRefresh(boolean isRefresh){
        isPrepare = isRefresh;
    }

    //显示dialog
    public void showDialog(BaseDialog dialog, String tag) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if (!dialog.isAdded()) {
            transaction.add(dialog, tag);
            transaction.commit();
        }
    }
}
