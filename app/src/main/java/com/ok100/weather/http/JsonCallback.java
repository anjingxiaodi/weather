package com.ok100.weather.http;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.lzy.okhttputils.callback.AbsCallback;
import com.lzy.okhttputils.request.BaseRequest;
import com.ok100.weather.base.BaseApplication;
import com.ok100.weather.constant.ConstantCode;
import com.ok100.weather.utils.SharePreferencesUtil;


import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * ================================================
 * 作    者：廖子尧
 * 版    本：1.0
 * 创建日期：2016/1/14
 * 描    述：默认将返回的数据解析成需要的Bean,可以是 BaseBean，String，List，Map
 * 修订历史：
 * -
 * -
 * -
 * -
 * -我的注释都已经写的不能再多了,不要再来问我怎么获取数据对象,怎么解析集合数据了,你只要会 gson ,就会解析
 * -
 * -
 * -
 * ================================================
 */
public abstract class JsonCallback<T> extends AbsCallback<T> {

    private Class<T> clazz;
    private Type type;

    /**
     * 传class,直接返回解析生成的对象
     */
    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 对于需要返回集合类型的,可以传type
     * type = new TypeToken<List<你的数据类型>>(){}.getType()
     */
    public JsonCallback(Type type) {
        this.type = type;
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        //主要用于在所有请求之前添加公共的请求头或请求参数，例如登录授权的 token,使用的设备信息等,可以随意添加,也可以什么都不传
//        request.headers("token", "HeaderValue1");//
//                .params("params1", "ParamsValue1")//
//                .params("token", "3215sdf13ad1f65asd4f3ads1f");
        request.headers("token", (String) SharePreferencesUtil.get(BaseApplication.getApplication(), ConstantCode.TOKEN, ""));    //header不支持中文
    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Override
    public T parseNetworkResponse(Response response) throws Exception {
        String responseData = response.body().string();
        if (TextUtils.isEmpty(responseData)) return null;

        /**
         * 一般来说，服务器返回的响应码都包含 code，msg，data 三部分，在此根据自己的业务需要完成相应的逻辑判断
         * 以下只是一个示例，具体业务具体实现
         */
//        String decode = URLDecoder.decode(responseData, "UTF-8");
        JSONObject jsonObject = new JSONObject(responseData);
//        final String msg = jsonObject.optString("msg", "");
//        final int code = jsonObject.optInt("err", 0);

//        String data = jsonObject.optString("data", "");
//        L.e(data);
//        L.e(code+"");
//        String code = jsonObject.optString("returncode");
//
//        String msg = jsonObject.optString("message");
//        String data= jsonObject.getJSONArray("data").toString();


        Object data = jsonObject;
        String dataStr = "";
        if (data instanceof JSONObject) {
            dataStr = ((JSONObject) data).toString();
        }
//                Log.e("clazzzz",data.toString());
        if (data instanceof JSONArray)
            dataStr = ((JSONArray) data).toString();
        if (data instanceof String)
            dataStr = (String) data;
        if (data instanceof Integer && clazz == Integer.class)
            return (T) data;
        if (data instanceof Double && clazz == Double.class)
            return (T) data;
        if (clazz == String.class)
            return (T) dataStr;
        if (clazz != null)
            return new Gson().fromJson(dataStr, clazz);
        if (type != null) {
            if (type == String.class)
                return (T) dataStr;
            return new Gson().fromJson(dataStr, type);
        }


//        switch (code) {
//            case "0":
//                /**
//                 * 假如 code = 0 代表成功，这里默认实现了Gson解析,可以自己替换成fastjson等
//                 * clazz类型就是解析javaBean
//                 * type类型就是解析List<javaBean>
//                 */
//                Object data = jsonObject.get("result");
//                String dataStr = "";
//                if (data instanceof JSONObject) {
//                    dataStr = ((JSONObject) data).toString();
//                }
////                Log.e("clazzzz",data.toString());
//                if (data instanceof JSONArray)
//                    dataStr = ((JSONArray) data).toString();
//                if (data instanceof String)
//                    dataStr = (String) data;
//                if (data instanceof Integer && clazz == Integer.class)
//                    return (T) data;
//                if (data instanceof Double && clazz == Double.class)
//                    return (T) data;
//                if (clazz == String.class)
//                    return (T) dataStr;
//                if (clazz != null)
//                    return new Gson().fromJson(dataStr, clazz);
//                if (type != null) {
//                        if (type == String.class)
//                            return (T) dataStr;
//                    return new Gson().fromJson(dataStr, type);
//                }
//
//                break;
//            case "1":
//                //返回错误信息
//                throw new IllegalStateException(msg);
//            case "-2":
//                //比如：用户收取信息已过期，在此实现相应的逻辑，弹出对话或者跳转到其他页面等,该抛出错误，会在onError中回调。
//
//                throw new IllegalStateException(ConstantCode.NO_LOGIN);
//            default:
//                throw new IllegalStateException("错误代码：" + code + "，错误信息：");
//        }
        throw new IllegalStateException("数据解析错误");
    }
}