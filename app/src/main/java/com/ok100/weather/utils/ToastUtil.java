package com.ok100.weather.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ok100.weather.R;


/**
 * Toast类,防止连续多次调用toast时,toast超时显示. Created by zzk on 2014/7/21.
 */
public class ToastUtil {
    private static Toast t;
    private static int duration;

    private static void makeText(Context context, String msg, int duration) {
        if (ToastUtil.duration != duration) {
            if (t != null) {
                t.cancel();
            }
            t = Toast.makeText(context, msg, duration);
        } else {
            if (t == null) {
                t = Toast.makeText(context, msg, duration);
            } else {
                t.setText(msg);
            }
        }
        ToastUtil.duration = duration;
        t.show();
    }

    public static void makeText(Context context, int resId, int duration) {
        makeText(context, context.getResources().getString(resId), duration);
    }

    public static void makeShortText(Context context, String msg) {
        makeText(context, msg, Toast.LENGTH_SHORT);
    }

    public static void makeShortText(Context context, int resId) {
        makeText(context, resId, Toast.LENGTH_SHORT);
    }

    public static void makeLongText(Context context, String msg) {
        makeText(context, msg, Toast.LENGTH_LONG);
    }

    public static void makeLongText(Context context, int resId) {
        makeText(context, resId, Toast.LENGTH_LONG);
    }

    /**
     * toast提示
     *
     * @param context 上下文
     * @param text    提示的内容
     */
    public static void toast(Context context, String text) {
        try {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    /**
     * @param context 上下文
     * @param showMsg 显示的内容
     * @param icon    展示的icon R.mipmap.icon_comment_success  R.mipmap.icon_comment_failed
     */
    public static void showComment(Context context, String showMsg, int icon) {
        View view = LayoutInflater.from(context).inflate(R.layout.toast_comment, null);

        TextView textView = (TextView) view.findViewById(R.id.tv_content_toast_comment);
        textView.setText(showMsg);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_content_toast_comment);
        imageView.setImageResource(icon);
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * @param context 上下文
     * @param showMsg 显示的内容
     */
    public static void showWorker(Context context, String showMsg) {
        View view = LayoutInflater.from(context).inflate(R.layout.toast_worker, null);

        TextView textView = (TextView) view.findViewById(R.id.tv_content_toast);
        textView.setText(showMsg);
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


}
