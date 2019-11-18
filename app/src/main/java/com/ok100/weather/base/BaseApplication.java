package com.ok100.weather.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;

import android.support.multidex.MultiDex;
import android.support.v4.app.NotificationCompat;
import android.telecom.Call;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;


import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheEntity;
import com.lzy.okhttputils.cache.CacheMode;
import com.ok100.weather.BuildConfig;
import com.ok100.weather.MainActivity;
import com.ok100.weather.R;
import com.ok100.weather.statistics.StatisticsManager;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


//import com.puti.paylib.PayReactPackage;


/**
 * Created by Administrator on 2016-11-19.
 */

public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";
    private static Context context;

    private static BaseApplication application;

    public List<Activity> activities = new ArrayList<Activity>();

//    private static DaoSession sDaoSession;//数据库
    private AlertDialog.Builder builder;
    private AlertDialog dialog;


    public static Context applicationContext;
    private String myuri = Environment.getExternalStorageDirectory().getAbsolutePath() + "";

//    {
//        ShareManager.getInstance();
//        ShareManager.config(SharePlatform.QQZONE, SharePlatform.WEIBO, SharePlatform.WEIXIN);
//    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);//分包配置
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        applicationContext = this;
        context = this;
        //注册生命周期


//        UMConfigure.init(context, "58bfb7bc677baa4a5f000b82", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "1ee4c477a7c6801989a1ea7684218378");
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "1ee4c477a7c6801989a1ea7684218378");
        UMConfigure.setLogEnabled(true);
        //分享

//        setDatabase();

        initOkhttp();
        statistics();//统计

       PushAgent mPushAgent = PushAgent.getInstance(this);
        //默认情况下，同一台设备在1分钟内收到同一个应用的多条通知时，不会重复提醒，同时在通知栏里新的通知会替换掉旧的通知。可以通过如下方法来设置冷却时间：
        mPushAgent.setMuteDurationSeconds(10);
        //通知栏可以设置最多显示通知的条数，当有新通知到达时，会把旧的通知隐藏。
        mPushAgent.setDisplayNotificationNumber(10);
        mPushAgent.setPushIntentServiceClass(null);
//        mPushAgent.setDebugMode(true);
        //sdk开启通知声音
        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
        mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
        mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
        //应用在显示推送
        mPushAgent.setNotificaitonOnForeground(true);
//        UmengNotificationClickHandler mUmengNotificationClickHandler = new UmengNotificationClickHandler() {
//            @Override
//            public void dealWithCustomAction(Context context, UMessage uMessage) {
//                super.dealWithCustomAction(context, uMessage);
//                Log.w(TAG, "点击");
//            }
//        };
//        mPushAgent.setNotificationClickHandler(mUmengNotificationClickHandler);



        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.w(TAG, "deviceToken::::::::" + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.w(TAG, "s::::::::" + s);
                Log.w(TAG, "s1::::::::" + s1);
            }
        });


    }



    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public static BaseApplication getApplication() {
        return application;
    }

    public static void setApplication(BaseApplication application) {
        BaseApplication.application = application;
    }

    public static Context getContext() {
        return context;
    }



    void initOkhttp() {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.put("token", (String) SharePreferencesUtil.get(application, "token", ""));    //header不支持中文
//        Log.e("Apptoken",(String) SharePreferencesUtil.get(application, "token", ""));

        //必须调用初始化
        OkHttpUtils.init(this);

        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        //好处是全局参数统一,特定请求可以特别定制参数
        try {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkHttpUtils.getInstance()

                    //打开该调试开关,控制台会使用 红色error 级别打印log,并不是错误,是为了显眼,不需要就不要加入该行
                    .debug("OkHttpUtils")

                    //如果使用默认的 60秒,以下三行也不需要传
                    .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                    .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                    .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)    //全局的写入超时时间

                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy0216/
                    .setCacheMode(CacheMode.NO_CACHE)

                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE);
//                    .addCommonHeaders(headers);                                      //设置全局公共头
            //如果不想让框架管理cookie,以下不需要
//                .setCookieStore(new MemoryCookieStore())                //cookie使用内存缓存（app退出后，cookie消失）
//                    .setCookieStore(new PersistentCookieStore());     //cookie持久化存储，如果cookie不过期，则一直有效

            //可以设置https的证书,以下几种方案根据需要自己设置
//                    .setCertificates()                                  //方法一：信任所有证书（选一种即可）
//                    .setCertificates(getAssets().open("srca.cer"))      //方法二：也可以自己设置https证书（选一种即可）
//                    .setCertificates(getAssets().open("aaaa.bks"), "123456", getAssets().open("srca.cer"))//方法三：传入bks证书,密码,和cer证书,支持双向加密

            //可以添加全局拦截器,不会用的千万不要传,错误写法直接导致任何回调不执行
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        return chain.proceed(chain.request());
//                    }
//                })

            //这两行同上,不需要就不要传

//                    .addCommonParams(params);                                          //设置全局公共参数
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    //配置greendao
//    private void setDatabase() {
//        DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(this, "vss-db", null);
//        SQLiteDatabase db = mHelper.getWritableDatabase();
//        DaoMaster daoMaster = new DaoMaster(db);
//        sDaoSession = daoMaster.newSession();
//    }
//
//    public static DaoSession getDaoSession() {
//
//        return sDaoSession;
//    }

    private void statistics() {
        //设置统计场景为 普通统计场景类型
        StatisticsManager.getInstance().setScenarioType(getApplicationContext(), MobclickAgent.EScenarioType.E_UM_NORMAL);
        //是否开启友盟统计debug模式
        StatisticsManager.getInstance().setDebugMode(BuildConfig.DEBUG);
        StatisticsManager.getInstance().openActivityDurationTrack(false);//禁止默认的页面统计方式 这样将不会再自动统计Activity。
        StatisticsManager.getInstance().setCatchUncaughtExceptions(false);//不开启错误统计
    }

    public boolean isExitActivity(String  activity) {

        Intent intent = new Intent();
        intent.setClassName(getPackageName(), activity);
        ResolveInfo resolveInfo = getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo != null) {

            //activity found
            return true;
        } else {
            //activity not found
            return false;
        }

//        Intent intent = new Intent(this, activity);
//        ComponentName cmpName = intent.resolveActivity(getPackageManager());
//        boolean flag = false;
//        if (cmpName != null) { // 说明系统中存在这个activity
//            ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);  //获取从栈顶开始往下查找的10个activity
//            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
//                if (taskInfo.baseActivity.equals(cmpName)) { // 说明它已经启动了
//                    flag = true;
//                    break;  //跳出循环，优化效率
//                }
//            }
//        }
//        return flag;
    }


    public class MainHandler extends Handler {
        private MainHandler() {
            super(Looper.getMainLooper());
        }
    }



}
