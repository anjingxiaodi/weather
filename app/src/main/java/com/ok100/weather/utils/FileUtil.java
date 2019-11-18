package com.ok100.weather.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/26.
 */

public class FileUtil {
    private static final Map<String, String> typeMap = new HashMap<>();
    static {
        typeMap.put(".3gp", "video/3gpp");
        typeMap.put(".apk", "application/vnd.android.package-archive");
        typeMap.put(".asf", "video/x-ms-asf");
        typeMap.put(".avi", "video/x-msvideo");
        typeMap.put(".bin", "application/octet-stream");
        typeMap.put(".bmp", "image/bmp");
        typeMap.put(".c", "text/plain");
        typeMap.put(".class", "application/octet-stream");
        typeMap.put(".conf", "text/plain");
        typeMap.put(".cpp", "text/plain");
        typeMap.put(".doc", "application/msword");
        typeMap.put(".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        typeMap.put(".xls", "application/vnd.ms-excel");
        typeMap.put(".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        typeMap.put(".exe", "application/octet-stream");
        typeMap.put(".gif", "image/gif");
        typeMap.put(".gtar", "application/x-gtar");
        typeMap.put(".gz", "application/x-gzip");
        typeMap.put(".h", "text/plain");
        typeMap.put(".htm", "text/html");
        typeMap.put(".html", "text/html");
        typeMap.put(".jar", "application/java-archive");
        typeMap.put(".java", "text/plain");
        typeMap.put(".jpeg", "image/jpeg");
        typeMap.put(".jpg", "image/jpeg");
        typeMap.put(".js", "application/x-javascript");
        typeMap.put(".log", "text/plain");
        typeMap.put(".m3u", "audio/x-mpegurl");
        typeMap.put(".m4a", "audio/mp4a-latm");
        typeMap.put(".m4b", "audio/mp4a-latm");
        typeMap.put(".m4p", "audio/mp4a-latm");
        typeMap.put(".m4u", "video/vnd.mpegurl");
        typeMap.put(".m4v", "video/x-m4v");
        typeMap.put(".mov", "video/quicktime");
        typeMap.put(".mp2", "audio/x-mpeg");
        typeMap.put(".mp3", "audio/x-mpeg");
        typeMap.put(".mp4", "video/mp4");
        typeMap.put(".mpc", "application/vnd.mpohun.certificate");
        typeMap.put(".mpe", "video/mpeg");
        typeMap.put(".mpeg", "video/mpeg");
        typeMap.put(".mpg", "video/mpeg");
        typeMap.put(".mpg4", "video/mp4");
        typeMap.put(".mpga", "audio/mpeg");
        typeMap.put(".msg", "application/vnd.ms-outlook");
        typeMap.put(".ogg", "audio/ogg");
        typeMap.put(".pdf", "application/pdf");
        typeMap.put(".png", "image/png");
        typeMap.put(".pps", "application/vnd.ms-powerpoint");
        typeMap.put(".ppt", "application/vnd.ms-powerpoint");
        typeMap.put(".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        typeMap.put(".prop", "text/plain");
        typeMap.put(".rc", "text/plain");
        typeMap.put(".rmvb", "audio/x-pn-realaudio");
        typeMap.put(".rtf", "application/rtf");
        typeMap.put(".sh", "text/plain");
        typeMap.put(".tar", "application/x-tar");
        typeMap.put(".tgz", "application/x-compressed");
        typeMap.put(".txt", "text/plain");
        typeMap.put(".wav", "audio/x-wav");
        typeMap.put(".wma", "audio/x-ms-wma");
        typeMap.put(".wmv", "audio/x-ms-wmv");
        typeMap.put(".wps", "application/vnd.ms-works");
        typeMap.put(".xml", "text/plain");
        typeMap.put(".z", "application/x-compress");
        typeMap.put(".zip", "application/x-zip-compressed");
        typeMap.put(".dwg", "image/vnd.dwg");
        typeMap.put("", "*/*");
    }

    private static String getFileType(File file) {
        String type = "*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
    /* 获取文件的后缀名*/
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if ("".equals(end)) return type;
        String typeFound = typeMap.get(end);
        return typeFound == null ? type : typeFound;
    }

    public static void openFile(Context context, File file) {
        Uri uri = null ;
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //设置intent的Action属性
        intent.setAction(Intent.ACTION_VIEW);
        //获取文件file的类型
        String type = getFileType(file);
        //设置intent的data和Type属性。
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context, "com.vanlian.vanlianserver.FileProvider",file);
            Log.e("uri",uri.toString());
        } else {
            uri = Uri.fromFile(file);
        }
        intent.setDataAndType(uri, type);
        //跳转
        context.startActivity(intent);
    }
}
