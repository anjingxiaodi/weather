package com.ok100.weather.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;



import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016-10-24.
 */
public class StringUtil {
    private static Pattern mPattern;
    /**
     * 手机号码的正则表达式
     */
    public static final String REG_PHONE = "^(\\+)?(86)?0?(13[0-9]|14[0-9]|15[0-9]|18[0-9]|17[0-9]|16[0-9])[0-9]{8}$";

    public static final String REG_EMAIL = "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";
    /**
     * 邮政号码的正则表达式
     */
    public static final String REG_POSTALCODE = "[1-9]\\d{5}(?!\\d)";

    /**
     * 作用：判断是否是手机号码
     *
     * @param phoneNum
     * @return
     */
    public static boolean isPhoneNum(String phoneNum) {
        mPattern = Pattern.compile("^13[\\d]{9}$|^14[5,7]{1}\\d{8}$|^15[^4]{1}\\d{8}$|^17[0,3,6,7,8]{1}\\d{8}$|^18[\\d]{9}$");
        Matcher matcher = mPattern.matcher(phoneNum);
        return matcher.find();
    }

    /**
     * 作用：判断是否是邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        mPattern = Pattern.compile(REG_EMAIL);
        Matcher matcher = mPattern.matcher(email);
        return matcher.find();
    }

    /**
     * 作用：是否是 邮政编码
     *
     * @param postalcode
     * @return
     */
    public static boolean isPostalcode(String postalcode) {
        mPattern = Pattern.compile(REG_POSTALCODE);
        Matcher matcher = mPattern.matcher(postalcode);
        return matcher.find();
    }

    /**
     * 作用：判断字符串是否为空 （null、“null”、“”、空白行）
     *
     * @param str 需要做判断的字符串
     * @return
     */
    public static boolean isEmpty(String str) {
        return (str == null || "null".equalsIgnoreCase(str) || "".equals(str) || str.length() <= 0 || str.matches("^\\s*$"));
    }

    /**
     * 判断字符串是否包含中文
     *
     * @author lvliuyan
     */
    public static final boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否为空
     *
     * @author lvliuyan
     */
    public static final String isnull(String strName) {
        if ("".equals(strName) || "null".equals(strName) || null == strName) {
            return "";
        } else {
            return strName;
        }
    }

    private static final boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * String转int类型
     *
     * @param number
     * @return
     */
    public static int strToInt(String number) {
        int n = 0;
        try {
            n = Integer.valueOf(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return n;
    }

    /**
     * String转long类型
     *
     * @param number
     * @return
     */
    public static long strToLong(String number) {
        long n = 0;
        try {
            n = Long.valueOf(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return n;
    }

    /**
     * String转bigbecimal类型
     *
     * @param number
     * @return
     */
    public static BigDecimal strToBigDecimal(String number) {
        BigDecimal n = null;
        try {
            n = new BigDecimal(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return n;
    }

    /**
     * String转double类型
     *
     * @param number
     * @return
     */
    public static double strToDouble(String number) {
        double n = 0l;
        try {
            n = Double.valueOf(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return n;
    }

    /**
     * 跳转到浏览器
     *
     * @param context
     * @param url
     */
    public static void startUrl(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(it);
    }

    /**
     * list集合转string
     */
    public static String listtostr(ArrayList<String> strings) {
        String str = "null";
        for (int i = 0; i < strings.size(); i++) {
            str = str + "," + strings.get(i);
        }
        str = str.substring(4, str.length());
        return str;
    }

    public static String urlcode(String res) {
        try {
            res = java.net.URLDecoder.decode(res, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        return res;
    }

    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
