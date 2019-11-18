package com.ok100.weather.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import android.widget.Scroller;

import com.ok100.weather.fragment.MainFragment;
import com.ok100.weather.fragment.NoticeMainFragment1;

/**
 * @Description: This is MyRecyclerView
 * @Author: QianDongDong
 * @Time: 2019/11/8 16:14
 * @Email: qdd2977@sina.com
 * @org: www.vanlian.cn 万科链家（北京）装饰有限公司
 */
public class MyRecyclerView extends RecyclerView {

    private static final String TAG = "Scroller";

    private MyScroller mScroller;

    public MyRecyclerView(Context context) {
        super(context);
        if(mScroller ==null){
            setFocusable(true);
            mScroller = new MyScroller(context);
        }

    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if(mScroller ==null){
            setFocusable(true);
            mScroller = new MyScroller(context);
        }
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if(mScroller ==null){
            setFocusable(true);
            mScroller = new MyScroller(context);
        }

    }



//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        //先测量获取子view的信息
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        if(heightMeasureSpec<=0) {
//            int height = 0;
//            //下面遍历所有child的高度
//            for (int i = 0; i < getChildCount(); i++) {
//                View child = getChildAt(i);
//                child.measure(widthMeasureSpec,
//                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
//                int h = child.getMeasuredHeight();
//                if (h > height) //采用最大的view的高度。
//                    height = h;
//            }
//
//            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
//                    MeasureSpec.EXACTLY);
//            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//        }
//    }


    //调用此方法滚动到目标位置
    public void smoothScrollTo(int fx, int fy) {
        int dx = fx - mScroller.getFinalX();
        int dy = fy - mScroller.getFinalY();
        smoothScrollBy(dx, dy);
    }

    //调用此方法设置滚动的相对偏移
    public void smoothScrollBy(int dx, int dy) {

        //设置mScroller的滚动偏移量
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy);
        invalidate();//这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
    }

    @Override
    public void computeScroll() {


//        //先判断mScroller滚动是否完成
        if (mScroller.computeScrollOffset()) {
            Log.e("MyGesture","computeScroll");
            //这里调用View的scrollTo()完成实际的滚动
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
//            Log.e("scollerY",mScroller.getCurrX()+"---"+mScroller.getCurrY());
//            Log.e("scollerY",mScroller.getFinalX()+"--***-"+mScroller.getFinalY());
            //必须调用该方法，否则不一定能看到滚动效果

            postInvalidate();
        }

        super.computeScroll();
    }


    public int getScollerY(){
        if(mScroller==null){
            return 111;
        }
                int finalY = mScroller.getFinalY();
//        int finalY = mScroller.timePassed();
        return finalY;
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

    /**
     * 最小移动像素说
     */
    private int mTouchSlop;
    /**
     * 手指按下时的屏幕坐标
     */
    private float mDownX;
    /**
     * 手机当时所处的屏幕坐标
     */
    private float mMoveY;
    /**
     * 上次触发ACTION_MOVE事件时的屏幕坐标
     */
    private float mYLastMove;
    /**
     * 界面可滚动的左边界
     */
    private int leftBorder;
    /**
     * 界面可滚动的右边界
     */
    private int rightBorder;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        mScroller.startScroll(0,getScollerY(),0,(int)e.getY());
//        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), 500, 500, 3000);
//        Log.e("scollerYYY",mScroller.getFinalX()+"---"+mScroller.getFinalY());
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                //getRawX ：表示相对于屏幕左上角的x坐标值
                //getX ：是表示Widget相对于自身左上角的x坐标,
                mMoveY = event.getY();
                Log.e("mMoveY",mMoveY+"--++--");
                int scollerY = (int) (mMoveY - mYLastMove);
                Log.e("scollerY",scollerY+"");
//                if (getScollerY() + scollerY < leftBorder) {
//                    //边界保护，，防止用户拖出边界
//                    scrollTo(0, leftBorder);
//                    return true;
//                } else if (getScrollY() + getHeight() + scollerY > rightBorder) {
//                    scrollTo(rightBorder - getWidth(), 0);
//                    return true;
//                }


                break;
            case MotionEvent.ACTION_UP:
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
//                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
//                int dx = targetIndex * getWidth() - getScrollX();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面

//                mScroller.startScroll(getScrollX(), getScollerY(), 0, 100);
                invalidate();
                break;
        }
//        if(MySwipeRefreshLayout.isSWlianjie ==true){
//            return true;
//        }else {
            return super.onTouchEvent(event);
//        }

    }

    @Override
    public void onDraw(Canvas c) {
        int[] position = new int[2];
        getLocationInWindow(position);
        Log.e("111111111",position[1]+"");

        super.onDraw(c);
    }
}
