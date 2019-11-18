package com.ok100.weather.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * @Description: This is MySwipeRefreshLayout
 * @Author: QianDongDong
 * @Time: 2019/11/8 16:11
 * @Email: qdd2977@sina.com
 * @org: www.vanlian.cn 万科链家（北京）装饰有限公司
 */
public class MySwipeRefreshLayout2 extends SwipeRefreshLayout {

    public static boolean isRclanjie = false;
    public static boolean isSWlianjie = false;
    public static boolean ishuadong = false;

    public static boolean returndata = false;

    private float X1;
    private float X2;
    private float Y1;
    private float Y2;
    private float ComparedX;
    private float ComparedY;


    private boolean mIsVpDragger = false;
    private int mTouchSlop;

    private OnPreInterceptTouchEventDelegate mOnPreInterceptTouchEventDelegate;


    private MyScroller mScroller;

    public MySwipeRefreshLayout2(Context context) {
        super(context);
        if(mScroller ==null){
            setFocusable(true);
            mScroller = new MyScroller(context);
        }
    }

    public MySwipeRefreshLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        if(mScroller ==null){
            setFocusable(true);
            mScroller = new MyScroller(context);
        }
    }

    //重写这个方法，并且在方法里面请求所有的父控件都不要拦截他的事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float YYYY = MySwipeRefreshLayout2.this.getY();
        Log.e("YYYY",YYYY+"");
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //必须返回false，否则子控件永远无法拿到焦点
                X1 = ev.getX();//获取刚按下屏幕位置的X坐标的值
                Y1 = ev.getY();//获取刚按下屏幕位置的Y坐标的值
                Log.e("dispatchTouchEvent","ACTION_DOWN");
            case MotionEvent.ACTION_MOVE:
                Log.e("dispatchTouchEvent","ACTION_MOVE");
            case MotionEvent.ACTION_UP:
                Log.e("dispatchTouchEvent","ACTION_UP");
                //必须返回false,否则子控件永远无法拿到焦点
                X2 = ev.getX();//当手指抬起时，再次获取屏幕位置的X值
                Y2 = ev.getY();//同理
                ComparedX = X2 - X1;//第二次的X坐标的位置减去第一次X坐标的位置，代表X坐标上的变化情况
                ComparedY = Y2 - Y1;
//                if(Y1 - Y2 > 50) {
//                    //向上滑
//
//                } else if(Y2 - Y1 > 50) {
//                    //向下滑
//
//                } else if(X1 - X2 > 50) {
//                    //向左滑
//
//                } else if(X2 - X1 > 50) {
//                    //向右滑
//
//                }

                if (mIsVpDragger) {
//                    return ishuadong;
                } else {
                    mIsVpDragger = true;
                if (Math.abs(ComparedX) >= Math.abs(ComparedY)+5) {
                    //leader X
                    if (ComparedX > 0) {
                        Log.e("AAAAA", "向右滑动");
                        ishuadong = false;
                    } else {
                        Log.e("AAAAA", "向左滑动");
                        ishuadong = false;
                    }
                } else {
                    // leader Y
                    if (ComparedY > 0) {
                        Log.e("AAAAA", "向下滑动");
                        ishuadong = true;
                    } else {
                        Log.e("AAAAA", "向上滑动");
                        ishuadong = false;
                    }
                }
                X1 = ev.getX();//获取刚按下屏幕位置的X坐标的值
                Y1 = ev.getY();
                }
                // 初始化标记
                mIsVpDragger = false;

        }
        return super.dispatchTouchEvent(ev);
    }


    private float startY;
    private float startX;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("onInterceptTouchEvent","ACTION_DOWN");
                startY = ev.getY();
                startX = ev.getX();

                break;

                //必须返回false，否则子控件永远无法拿到焦点
//                if(islanjie) {
//                    return false;
//                return super.onInterceptTouchEvent(ev);
//                } else {
//                    return super.onInterceptTouchEvent(ev);
//                }



            case MotionEvent.ACTION_MOVE:
                Log.e("onInterceptTouchEvent","ACTION_MOVE");
                // 如果viewpager正在拖拽中，那么不拦截它的事件，直接return false；
//                if(mIsVpDragger) {
//                    Log.e("onInterceptTouchEvent","mIsVpDragger");
//                    return false;
//                }


                    // 获取当前手指位置
                    float endY = ev.getY();
                    float endX = ev.getX();
                    float distanceX = Math.abs(endX - startX);
                    float distanceY = Math.abs(endY - startY);
                    // 如果X轴位移大于Y轴位移，那么将事件交给viewPager处理。
//                    if( distanceX > distanceY) {
//                        Log.e("onInterceptTouchEvent","11111");
//                        return false;
//                    }else {
                        Log.e("onInterceptTouchEvent","22222");
                        if(isRclanjie){
                            Log.e("onInterceptTouchEvent","isRclanjie----true");
                        }

                        if(isSWlianjie){
                            Log.e("onInterceptTouchEvent","isSWlianjie----true");
                        }

                        if(recyclerView!=null){
                            isRclanjie = !recyclerView.canScrollVertically(-1);
                        }

                        if (isRclanjie&&isSWlianjie) {
                            if (ishuadong) {
                                Log.e("onInterceptTouchEvent","true");
                                return true;
                            }else {
                                Log.e("onInterceptTouchEvent","false");
                                return super.onInterceptTouchEvent(ev);
                            }

//                    return super.onInterceptTouchEvent(ev);
                        }
//                    }
                    startY = ev.getY();
                    startX = ev.getX();


                break;


            case MotionEvent.ACTION_UP:
                Log.e("onInterceptTouchEvent","ACTION_UP");

                //必须返回false,否则子控件永远无法拿到焦点
//                if(islanjie) {
//                    return false;
//                return super.onInterceptTouchEvent(ev);
//                } else {
//                    return super.onInterceptTouchEvent(ev);
//                }

            case MotionEvent.ACTION_CANCEL:
                // 初始化标记
                mIsVpDragger = false;
                break;

        }

        returndata = false;
        return super.onInterceptTouchEvent(ev);
    }

    public void setOnPreInterceptTouchEventDelegate(OnPreInterceptTouchEventDelegate listener) {
        mOnPreInterceptTouchEventDelegate = listener;
    }

    private RecyclerView recyclerView;

    public interface OnPreInterceptTouchEventDelegate {
        boolean shouldDisallowInterceptTouchEvent(MotionEvent ev);
    }

    public void setRecycleview(RecyclerView recyclerView){
     this.recyclerView = recyclerView;
    }

    public class MyScroller extends Scroller {

        public MyScroller(Context context) {
            super(context);
        }

        public MyScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }



        @Override
        public void fling(int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY) {
            Log.e("MyGesture", "velocityX=="+velocityX+"*******velocityY"+velocityY);
            super.fling(startX, startY, velocityX, velocityY, minX, maxX, minY, maxY);
        }

    }

}

