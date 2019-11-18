package com.ok100.weather.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Description: This is MyViewPager
 * @Author: QianDongDong
 * @Time: 2019/11/3 16:47
 * @Email: qdd2977@sina.com
 * @org: www.vanlian.cn 万科链家（北京）装饰有限公司
 */
public class MyViewPager1 extends ViewPager {

  public MyViewPager1(Context context) {
    super(context);
  }
 
   public MyViewPager1(Context context, AttributeSet attrs) {
    super(context, attrs);
  }
 
   @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


     //先测量获取子view的信息
     super.onMeasure(widthMeasureSpec, heightMeasureSpec);
     if(heightMeasureSpec<=0){
       int height = 0;
       //下面遍历所有child的高度
       for (int i = 0; i < getChildCount(); i++) {
         View child = getChildAt(i);
         child.measure(widthMeasureSpec,
                 MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
         int h = child.getMeasuredHeight();
         if (h > height) //采用最大的view的高度。
           height = h;
       }

       heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
               MeasureSpec.EXACTLY);
       super.onMeasure(widthMeasureSpec, heightMeasureSpec);
     }

   }


}

