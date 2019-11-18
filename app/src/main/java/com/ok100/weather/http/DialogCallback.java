package com.ok100.weather.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Window;

import com.lzy.okhttputils.request.BaseRequest;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.constant.ConstantCode;
import com.ok100.weather.utils.NetWorkUtil;
import com.ok100.weather.utils.SharePreferencesUtil;
import com.ok100.weather.utils.ToastUtil;


import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;

/**
 * ================================================
 * 作    者：廖子尧
 * 版    本：1.0
 * 创建日期：2016/1/14
 * 描    述：对于网络请求是否需要弹出进度对话框
 * 修订历史：
 * ================================================
 */
public abstract class DialogCallback<T> extends JsonCallback<T> {
    private boolean isShow;
    private ProgressDialog dialog;
    private Context context;
    private boolean hintDialog;

    private void initDialog(Context activity) {
        this.context = activity;
        dialog = new ProgressDialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("请求网络中...");
    }

    public DialogCallback(Class<T> clazz) {
        super(clazz);
    }

    public DialogCallback(Context activity, Class<T> clazz) {
        super(clazz);
        initDialog(activity);
    }

    public DialogCallback(Type type, Context activity) {
        super(type);
    }

    public DialogCallback(Context activity, Type type) {
        super(type);
        initDialog(activity);
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        //网络请求前显示对话框
//        if (dialog != null && !dialog.isShowing() && !hintDialog) {
//            dialog.show();
//        }
    }

    @Override
    public void onAfter(@Nullable T t, @Nullable Exception e) {
        super.onAfter(t, e);
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing() && !hintDialog) {
            dialog.dismiss();
        }
    }

    @Override
    public void onError(Call call, Response response, Exception e) {
        super.onError(call, response, e);
/**
 * java.lang.NullPointerException: println needs a message
 * at com.vanlian.vanlianserver.http.DialogCallback.onError(DialogCallback.java:85)
 */
        if (e.getMessage() == null)
            return;

        Log.e("onError", e.getMessage());
        if (isShow) {
            if ("服务器数据异常!".equals(e.getMessage())) {
                ToastUtil.makeLongText(context, "网络异常！");
            } else {
                ToastUtil.makeLongText(context, e.getMessage());
            }

            if (!NetWorkUtil.isNetworkAvailable(context)) {
                ToastUtil.makeLongText(context, "请连接网络哦！");
                return;
            }
            //            ToastUtil.makeLongText(context, "网络请求失败");
        }
//        if (ConstantCode.NO_LOGIN.equals(e.getMessage())) {
//            //            ToastUtil.makeLongText(context,e.getMessage());
//            //            L.e(ConstantCode.NO_LOGIN,e.getMessage());
//            SharePreferencesUtil.put(context, ConstantCode.ISLOGIN, false);
//            SharePreferencesUtil.put(context, ConstantCode.TOKEN, "");
//            Intent intent = new Intent();
//            intent.setClass(context, LoginActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//
//            if (context instanceof BaseActivity) {
//                BaseActivity activity = (BaseActivity) context;
//                activity.exit(false);
//            }
//            context.startActivity(intent);
//            //            ((AbsBaseActivity)context).finishActivities(context.getClass());
//        }
    }

    public DialogCallback showErrorMsg() {
        isShow = true;
        return this;
    }

    public DialogCallback hintDialog() {
        hintDialog = true;
        return this;
    }


}
