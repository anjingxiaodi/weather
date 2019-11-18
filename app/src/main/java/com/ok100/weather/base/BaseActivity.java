package com.ok100.weather.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.ok100.weather.MainActivity;
import com.ok100.weather.R;
import com.ok100.weather.receiver.NetReceiver;
import com.ok100.weather.statistics.StatisticsManager;
import com.ok100.weather.utils.ToastUtil;

import java.util.Stack;


import butterknife.ButterKnife;
import flyn.Eyes;


/**
 * Created by Administrator on 2016-10-25.
 * NetReceiver 网络 无线4G、3G、2G判断
 */
public abstract class BaseActivity extends AppCompatActivity implements NetReceiver.NetEventHandle,View.OnClickListener {
    private static final String TAG = "BaseActivity";

    public static final int NO_LAYOUT = 0;

    private static Stack<BaseActivity> mActivities;

    /**
     * 标记标题左右两边的类型:文字
     */
    protected final int TITLE_TYPE_TEXT = 0;

    /**
     * 标记标题左右两边的类型:图片
     */
    protected final int TITLE_TYPE_IMG = 1;

    private View contentView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
//        StatusBarCompat.compat(this, ContextCompat.getColor(this, R.color.themecolor));

        if (mActivities == null) {
            mActivities = new Stack<BaseActivity>();
        }
        mActivities.push(this);

        int id = getLayoutID();

        if (id != NO_LAYOUT) {

            //R.layout.activity_base
            contentView = getLayoutInflater().inflate(id, null);
            ButterKnife.bind(this, contentView);
            setContentView(contentView);
//            if(this instanceof MainActivity){
            if(0==0){
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = this.getWindow();
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.TRANSPARENT);
                    window.setNavigationBarColor(Color.TRANSPARENT);
                } else
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Window window =this.getWindow();
                    window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                }


            }else {
                Eyes.setStatusBarLightMode(this, Color.WHITE);
            }

            InitView();
            // initstatusbar();

            initData(savedInstanceState, contentView);
        } else {
            Log.e(TAG, "contentView is Null!");
        }


        //添加activity到applocation的activity集合中，方便一次性关闭
        BaseApplication.getApplication().activities.add(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        initListener();

    }


    /**
     * 获取当前Application的context
     *
     * @return context
     */
    public Context getContext() {
        return getApplicationContext();
    }





    /**
     * 获取当前activity 对应的layout R.layout id
     *
     * @return id
     */
    public abstract int getLayoutID();

    /**
     * 在setContentView前初始化的东西
     */
    public abstract void InitView();

    /**
     * onclick 监听事件方法
     */
    public abstract void initListener();

    /**
     * 类似onCreate()方法
     */
    public abstract void initData(Bundle savedInstanceState, View contentView);

    /**
     * 需在setContentView方法之后调用. 设置后,如果不对左侧的事件进行监听,默认的点击事件是结束当前界面.
     * <p>
     * 标题传资源id和字符串皆可.
     * <p>
     * 如果某一侧显示的是图片,则那一侧只能传对应的图片资源id.如果是文字,则资源id和字符串皆可.
     *
     * @param title     标题
     * @param left      是否显示左侧的部分
     * @param leftType  左侧的类型
     * @param l         左侧部分内容
     * @param right     是否显示右侧的部分
     * @param rightType 右侧的类型
     * @param r         右侧部分的内容
     */
    protected void setTitle(Object title, boolean left, int leftType, Object l, boolean right, int rightType, Object r) {
        try {

            TextView tvTitle = (TextView) findViewById(R.id.tv_title);
            TextView tvLeft = (TextView) findViewById(R.id.tv_title_left);
            LinearLayout llLeft = (LinearLayout) findViewById(R.id.ll_title_left);
            ImageView ivLeft = (ImageView) findViewById(R.id.iv_title_left);
            TextView tvRight = (TextView) findViewById(R.id.tv_title_right);
            ImageView ivRight = (ImageView) findViewById(R.id.iv_title_right);
            LinearLayout llRight = (LinearLayout) findViewById(R.id.ll_title_right);
            if (title != null && title instanceof String) {
                if (!TextUtils.isEmpty((String) title)) {
                    tvTitle.setVisibility(View.VISIBLE);
                    tvTitle.setText((String) title);
                    this.title = (String) title;
                } else {
                    tvTitle.setVisibility(View.INVISIBLE);
                }
            } else if (title != null && title instanceof Integer) {
                if (((Integer) title) > 0) {
                    tvTitle.setVisibility(View.VISIBLE);
                    tvTitle.setText((Integer) title);
                } else {
                    tvTitle.setVisibility(View.INVISIBLE);
                }
            }
            if (left) {
                llLeft.setVisibility(View.VISIBLE);
                if (leftType == TITLE_TYPE_TEXT) {
                    ivLeft.setVisibility(View.GONE);
                    tvLeft.setVisibility(View.VISIBLE);
                    if (l instanceof String) {
                        tvLeft.setText((String) l);
                    } else if (l instanceof Integer) {
                        tvLeft.setText((Integer) l);
                    }
                } else if (leftType == TITLE_TYPE_IMG) {
                    ivLeft.setVisibility(View.VISIBLE);
                    tvLeft.setVisibility(View.GONE);
                    if (l instanceof Integer) {
                        ivLeft.setImageResource((Integer) l);
                    }

                }
            } else {
                llLeft.setVisibility(View.INVISIBLE);
            }
            if (right) {
                llRight.setVisibility(View.VISIBLE);
                if (rightType == TITLE_TYPE_TEXT) {
                    ivRight.setVisibility(View.GONE);
                    tvRight.setVisibility(View.VISIBLE);
                    if (r instanceof String) {
                        tvRight.setText((String) r);
                    } else if (r instanceof Integer) {
                        tvRight.setText((Integer) r);
                    }
                } else if (rightType == TITLE_TYPE_IMG) {
                    ivRight.setVisibility(View.VISIBLE);
                    tvRight.setVisibility(View.GONE);
                    if (r instanceof Integer) {
                        ivRight.setImageResource((Integer) r);
                    }
                }
            } else {
                llRight.setVisibility(View.INVISIBLE);
            }

        } catch (Exception e) {

        }
    }

    /**
     * 设置点击左上角的返回事件.默认是finish界面
     */
    protected void registerBack() {
        LinearLayout llLeft = (LinearLayout) findViewById(R.id.ll_title_left);
        llLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leftDoWhat();
            }
        });
    }

    /**
     * 设置点击右上角的返回事件.
     */
    protected View rightDo() {
        LinearLayout llRight = (LinearLayout) findViewById(R.id.ll_title_right);
        llRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightDoWhat();
            }
        });
        return llRight;
    }

    protected void rightDoWhat() {

    }

    protected void leftDoWhat() {
        finishActivities(this.getClass());
    }

    /**
     * 关闭所有指定tag的activity
     * activity 的 tag 可重载getTAG进行设置;
     *
     * @param cls 需要关闭的tag值
     */
    public void finishActivities(Class<? extends BaseActivity> cls) {
        if (mActivities.empty()) {
            return;
        }
        Stack<BaseActivity> temp = new Stack();

        for (BaseActivity activity : mActivities) {
            if (activity != null && activity.getClass() == cls) {
                temp.push(activity);
            }
        }

        for (BaseActivity activity : temp) {
            mActivities.remove(activity);
            activity.finish();
        }
        temp = null;

    }

    /**
     * 关闭客户端
     *
     * @param isAppExit 标示要不要退出app
     *                  true 标示退出app
     *                  false 标示清空activity堆栈
     */
    public void exit(boolean isAppExit) {
        if (mActivities.empty()) {
            return;
        }
        for (BaseActivity activity : mActivities) {
            if (activity != null && !activity.isFinishing())
            {
                if(isAppExit ||(!(activity instanceof MainActivity))){
                    activity.finish();
                }
            }
        }
        mActivities.clear();
        if (isAppExit) {
            System.exit(0);
        }
    }

    @Override
    public void netState(NetReceiver.NetState netCode) {
        switch (netCode) {
            case NET_NO:
                ToastUtil.makeShortText(BaseActivity.this, "没有网络连接");
                break;
            case NET_2G:
                ToastUtil.makeShortText(BaseActivity.this, "2g网络");
                break;
            case NET_3G:
                ToastUtil.makeShortText(BaseActivity.this, "3g网络");
                break;
            case NET_4G:
                ToastUtil.makeShortText(BaseActivity.this, "4g网络");
                break;
            case NET_WIFI:
                ToastUtil.makeShortText(BaseActivity.this, "WIFI网络");
                break;
            case NET_UNKNOWN:
                ToastUtil.makeShortText(BaseActivity.this, "未知网络");
                break;
            default:
                ToastUtil.makeShortText(BaseActivity.this, "不知道什么情况~>_<~");
        }
    }

//    public void callPhone(String phone) {
//        if (!phone.equals("电话：00000000000") && phone != "电话：00000000000" && "电话：00000000000" != phone && !"电话：00000000000".equals(phone)) {
//            Uri uri = Uri.parse("tel:" + phone);
//            Intent intentUri = new Intent(Intent.ACTION_DIAL, uri);
//            startActivity(intentUri);
//        }
//    }

    @Override
    protected void onResume() {
        super.onResume();
        //统计全部时长
        StatisticsManager.getInstance().onResume(this);
        StatisticsManager.getInstance().onPageStart(title==null?"":title);
    }


    @Override
    protected void onPause() {
        super.onPause();
        //统计全部时长
        StatisticsManager.getInstance().onPause(this);
        StatisticsManager.getInstance().onPageEnd(title==null?"":title);
    }

    @Override
    protected void onDestroy() {
        //这里不知道有没有问题
//        ButterKnife.unbind(this);
        if (contentView != null) {
            contentView = null;
            setContentView(R.layout.view_null);
        }
        super.onDestroy();
    }

}
