package com.rain.mykotlin.viewmodel;

import android.util.Base64;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();

        Map<String, String> map = new HashMap();
        map.put("X-APP-TYPE",           "ANDROID");
//        map.put("X-APP-VERSION",        String.valueOf(BuildConfig.VERSION_CODE));
//        map.put("X-APP-PACKAGE-NAME",   BuildConfig.APPLICATION_ID);
//        map.put("X-APP-NAME",           BuildConfig.APP_NAME);
//        map.put("X-REFERRER",           CommonUtil.getInstallReferrer());   //渠道统计标识
//        map.put("X-ANDROID-ID",         CommonUtil.getAndroidId());         //传给后台的设备唯一标识符
        map.put("X-AUTH-TOKEN",         "eyJsb2NhbGUiOiJlbl9VUyIsImFsZyI6IkhTNTEyIn0.eyJzdWIiOiIwOTk1MDc1NDgwMSIsImV4cCI6MTYyOTkxNjM4M30.JF7TGGgVb4DnQnrKM_tulyrCZMVJ9TRogdepLm0Ij--cshy1Eq48jyFx52iIyvUIA3X5PCBXjOuv3fRcSbvHSQ");             //用户的token
//        Log.d("HRX", "token: " + CommonUtil.getToken());
//        if (!TextUtils.isEmpty(BuildConfig.CHANNEL_NAME)) {
//            map.put("X-APP-SOURCE",     BuildConfig.CHANNEL_NAME);
//        }
        String pragma = base64(new Gson().toJson(map).getBytes(), Base64.NO_WRAP);
        requestBuilder.header("Pragma", pragma);
        return chain.proceed(requestBuilder.build());
    }

    public String base64(byte[] bytes, int flag) {
        if (bytes != null && bytes.length > 0)
            return Base64.encodeToString(bytes, flag);
        else
            return "";
    }
}
