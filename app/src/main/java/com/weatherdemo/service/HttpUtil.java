package com.weatherdemo.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 数据请求工具类
 * @author yejinmo
 */
public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback)
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

}
