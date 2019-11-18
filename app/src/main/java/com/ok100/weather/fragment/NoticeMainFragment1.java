package com.ok100.weather.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ok100.weather.R;
import com.ok100.weather.activity.NoticeDetatilActivity;
import com.ok100.weather.adapter.NoticeMainFragmentAdapter;
import com.ok100.weather.base.BaseFragment;
import com.ok100.weather.bean.NoticeMainListBean;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.presenter.NoticeMainListPresenterImpl;
import com.ok100.weather.view.CustomLoadMoreViewNews;
import com.ok100.weather.view.FullyLinearLayoutManager;
import com.ok100.weather.view.MyLinearLayoutManager;
import com.ok100.weather.view.MyLinearLayoutManager1;
import com.ok100.weather.view.MyRecyclerView;
import com.ok100.weather.view.MySwipeRefreshLayout;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class NoticeMainFragment1 extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemLongClickListener, ReturnDataView<Object>, View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener , View.OnTouchListener {

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

//    public void setRecyclerView(RecyclerView recyclerView) {
//        mRecyclerView = recyclerView;
//    }

    //    private SwipeRefreshLayout mSwipeRefreshLayout;
    public MyRecyclerView mRecyclerView;
    private NoticeMainFragmentAdapter noticeMainFragmentAdapter;

    private int page = 1;
    private int pageNubmer = 10;
    private String departmentId = "100";
    private NoticeMainListPresenterImpl noticeMainListPresenterImpl;
    private ArrayList<NoticeMainListBean> listBeenData = new ArrayList<>();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }


    public void setRecyclerViewScoll(boolean isscoll){
        if(mRecyclerView!=null){
            mRecyclerView.setNestedScrollingEnabled(isscoll);//禁止滑动
//            mRecyclerView.setHasFixedSize(!isscoll);
        }

    }

    @Override
    public void returnData(String responseCode, Object o) {
        NoticeMainListBean noticeMainListBean = (NoticeMainListBean) o;
        if (page == 1) {
            listBeenData.clear();
//            listBeenData.addAll(noticeMainListBean.getList());
//            noticeMainFragmentAdapter.setNewData(listBeenData);
//            mSwipeRefreshLayout.setRefreshing(false);
        } else {
//            listBeenData.addAll(noticeMainListBean.getList());
//            noticeMainFragmentAdapter.setNewData(listBeenData);
            noticeMainFragmentAdapter.loadMoreComplete();
        }

//        if (noticeMainListBean.getList().size() < pageNubmer) {
//            noticeMainFragmentAdapter.loadMoreEnd();
//            noticeMainFragmentAdapter.removeAllFooterView();
//        }

    }

    @Override
    public void showError(String msg) {
//        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_news_vanlian;
    }

    @Override
    protected void init(Bundle savedInstanceState, View contentView) {
        findView();
        initAdapter();
        recyvleViewScrollLister();
        noticeMainListPresenterImpl = new NoticeMainListPresenterImpl(this);


//        mGestureDetector = new GestureDetector(new gestureListener()); //使用派生自OnGestureListener
//
//        mRecyclerView.setOnTouchListener(this);
//        mRecyclerView.setFocusable(true);
//        mRecyclerView.setClickable(true);
//        mRecyclerView.setLongClickable(true);


    }


    private void http() {

        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("departmentId", departmentId);
//        stringStringHashMap.put("departmentId" ,"");
        stringStringHashMap.put("pageNum", page + "");
        stringStringHashMap.put("pageSize", pageNubmer + "");
        //TODO   这里不判断null会有问题，还需要检查
//        if (getActivity() != null) {
//            Log.e("11111" ,"11111");
//            noticeMainListPresenterImpl.getNoticeList(getActivity(), stringStringHashMap);
//        }

    }


    public boolean isTabVisible = true;
    private int totalDy = 0;
    private boolean setAnimation = true;

    private boolean isXiaoyulin = false;
    private void recyvleViewScrollLister() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE||newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!recyclerView.canScrollVertically(1)) {
//                        callback.onScrollToBottom();
                    }
                    if (!recyclerView.canScrollVertically(-1)) {
                        Log.e("stopScroll","stopScroll");
                        mRecyclerView.stopScroll();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                boolean b = mRecyclerView.canScrollVertically(-1);
                Log.e("bbbbbb",b+"");
                totalDy -= dy;
                Log.e("totalDy",totalDy+"");
                if(totalDy<0){
                    if(isXiaoyulin){
                        bootomVisibleListener.setBootomVisible(true);
                        isXiaoyulin = false;
                    }
//                    taybarVisibleListener.setTaybarVisible(false);
                }else {
                    isXiaoyulin = true;
                    //滑到顶部
//                    mRecyclerView.stopScroll();
                }


//                AlphaAnimation appearAnimation = new AlphaAnimation(0, 1);
//                appearAnimation.setDuration(500);
//                AlphaAnimation disappearAnimation = new AlphaAnimation(1, 0);
//                disappearAnimation.setDuration(500);
//                if (totalDy != 0) {
//                    if (setAnimation) {
//                        setAnimation = false;
//                    }
//                } else {
//                    setAnimation = true;
//                    disappearAnimation.setAnimationListener(new Animation.AnimationListener() {
//
//                        @Override
//                        public void onAnimationStart(Animation animation) {
//                        }
//
//                        @Override
//                        public void onAnimationRepeat(Animation animation) {
//                        }
//
//                        @Override
//                        public void onAnimationEnd(Animation animation) {
//
//
//                        }
//                    });
//                }

            }
        });


        mRecyclerView.setOnFlingListener(new RecyclerView.OnFlingListener() {
            @Override
            public boolean onFling(int velocityX, int velocityY) {
                Log.e("velocityY",velocityY+"");
                Log.e("velocityX",velocityX+"");
                return false;
            }
        });

//        mRecyclerView.fling()


    }
    MyLinearLayoutManager1 linearLayoutManager ;
    private void initAdapter() {
        linearLayoutManager = new MyLinearLayoutManager1(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        noticeMainFragmentAdapter = new NoticeMainFragmentAdapter(getActivity());
        noticeMainFragmentAdapter.setNewData(getArrayListData());
//        mRecyclerView.setNestedScrollingEnabled(false);//禁止滑动

        mRecyclerView.setAdapter(noticeMainFragmentAdapter);



        //设置加载
//        noticeMainFragmentAdapter.setEnableLoadMore(true);
//        mSwipeRefreshLayout.setOnRefreshListener(this);
//        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(87, 136, 255));
        noticeMainFragmentAdapter.setOnLoadMoreListener(this, mRecyclerView);
        noticeMainFragmentAdapter.disableLoadMoreIfNotFullPage();
        noticeMainFragmentAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        noticeMainFragmentAdapter.setOnItemClickListener(this);
        noticeMainFragmentAdapter.setOnItemChildClickListener(this);
        noticeMainFragmentAdapter.setOnItemLongClickListener(this);
        noticeMainFragmentAdapter.setLoadMoreView(new CustomLoadMoreViewNews());
//        noticeMainFragmentAdapter.setEmptyView(getEmptyView());
//        LinearLayout layout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.item_news_main_footview, null);
//        noticeMainFragmentAdapter.setFooterView(layout);

    }

    private List<NoticeMainListBean> getArrayListData() {
        ArrayList<NoticeMainListBean> noticeMainListBeans = new ArrayList<NoticeMainListBean>();
        NoticeMainListBean noticeMainListBean1 = new NoticeMainListBean(1);
        NoticeMainListBean noticeMainListBean2 = new NoticeMainListBean(2);
        NoticeMainListBean noticeMainListBean3 = new NoticeMainListBean(3);
        noticeMainListBeans.add(noticeMainListBean1);
        noticeMainListBeans.add(noticeMainListBean2);
        noticeMainListBeans.add(noticeMainListBean3);
        noticeMainListBeans.add(noticeMainListBean1);
        noticeMainListBeans.add(noticeMainListBean2);
        noticeMainListBeans.add(noticeMainListBean1);
        noticeMainListBeans.add(noticeMainListBean3);
        noticeMainListBeans.add(noticeMainListBean2);
        noticeMainListBeans.add(noticeMainListBean1);
        noticeMainListBeans.add(noticeMainListBean3);
        noticeMainListBeans.add(noticeMainListBean2);
        noticeMainListBeans.add(noticeMainListBean1);
        noticeMainListBeans.add(noticeMainListBean3);
        noticeMainListBeans.add(noticeMainListBean2);
        noticeMainListBeans.add(noticeMainListBean1);
        noticeMainListBeans.add(noticeMainListBean3);
        return noticeMainListBeans;
    }

    private void findView() {
        mRecyclerView = (MyRecyclerView) findViewById(R.id.recycle);
//        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout_vanlian_new);


        if (getArguments() != null) {
            departmentId = getArguments().getString("departmentId");
        }
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        onRefresh();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onRefresh() {
//        mSwipeRefreshLayout.setRefreshing(true);
        page = 1;
        http();
//        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onResume() {
        super.onResume();
//        onRefresh();
    }

//    private View getEmptyView() {
//        View empty = LayoutInflater.from(getActivity()).inflate(R.layout.list_empty_view, (ViewGroup) mRecyclerView.getParent(), false);
//        return empty;
//    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        http();

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        NoticeMainListBean.ListBean listBean = listBeenData.get(position);
        Intent intent = new Intent(getActivity(), NoticeDetatilActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("onStart", "onStart");
    }

    public MainFragment.BootomVisibleListener bootomVisibleListener;

    public void setBootomVisibleListener(MainFragment.BootomVisibleListener bootomVisibleListener){
        this.bootomVisibleListener = bootomVisibleListener;
    }
   public MainFragment.TaybarVisibleListener taybarVisibleListener;

    public void setTaybarVisibleListener(MainFragment.TaybarVisibleListener taybarVisibleListener){
        this.taybarVisibleListener = taybarVisibleListener;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private GestureDetector mGestureDetector;
    private class gestureListener implements GestureDetector.OnGestureListener{

        // 用户轻触触摸屏，由1个MotionEvent ACTION_DOWN触发
        public boolean onDown(MotionEvent e) {
            Log.e("MyGesture", "onDown");
            Toast.makeText(getActivity(), "onDown", Toast.LENGTH_SHORT).show();
            return false;
        }

        /*
         * 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发
         * 注意和onDown()的区别，强调的是没有松开或者拖动的状态
         *
         * 而onDown也是由一个MotionEventACTION_DOWN触发的，但是他没有任何限制，
         * 也就是说当用户点击的时候，首先MotionEventACTION_DOWN，onDown就会执行，
         * 如果在按下的瞬间没有松开或者是拖动的时候onShowPress就会执行，如果是按下的时间超过瞬间
         * （这块我也不太清楚瞬间的时间差是多少，一般情况下都会执行onShowPress），拖动了，就不执行onShowPress。
         */
        public void onShowPress(MotionEvent e) {
            Log.e("MyGesture", "onShowPress");
            Toast.makeText(getActivity(), "onShowPress", Toast.LENGTH_SHORT).show();
        }

        // 用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发
        ///轻击一下屏幕，立刻抬起来，才会有这个触发
        //从名子也可以看出,一次单独的轻击抬起操作,当然,如果除了Down以外还有其它操作,那就不再算是Single操作了,所以这个事件 就不再响应
        public boolean onSingleTapUp(MotionEvent e) {
            Log.e("MyGesture", "onSingleTapUp");
            Toast.makeText(getActivity(), "onSingleTapUp", Toast.LENGTH_SHORT).show();
            return true;
        }

        // 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
//            Log.e("MyGesture22", "onScroll:"+(e2.getX()-e1.getX()) +"   "+distanceX);
            Toast.makeText(getActivity(), "onScroll", Toast.LENGTH_LONG).show();

            return false;
        }

        // 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发
        public void onLongPress(MotionEvent e) {
            Log.e("MyGesture", "onLongPress");
            Toast.makeText(getActivity(), "onLongPress", Toast.LENGTH_LONG).show();
        }

        // 用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 1个ACTION_UP触发
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            Log.e("MyGesture", "onFling");
            Log.e("MyGesture", "velocityX=="+velocityX+"====velocityY"+velocityY);
            Toast.makeText(getActivity(), "onFling", Toast.LENGTH_LONG).show();
            float rawy1 = 0;
            float rewy2 = 0;
            float y1 = 0;
            float y2 = 0;
            if(e1!=null){
                rawy1 = e1.getRawY();
                y1 = e1.getY();
            }
            if(e2!=null){
                rewy2 = e2.getRawY();
                y2 = e2.getY();
            }

            Log.e("MyGesture",y1+"-----"+y2);
            Log.e("MyGesture",rawy1+"--raw---"+rewy2);
            int scollerY = mRecyclerView.getScollerY();
            Log.e("scollerY",scollerY+"----");
            return false;
        }

    };


    public void resateRecycle(){
        if(mRecyclerView!=null){
            Log.e("totalDytotalDy",totalDy+"");
            mRecyclerView.scrollBy(0 ,totalDy-100);
        }

    }

}
