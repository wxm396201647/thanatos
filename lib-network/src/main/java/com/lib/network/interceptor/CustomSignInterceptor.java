package com.lib.network.interceptor;

import com.lib.network.common.CommonHeaders;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wang.ming5 on 2018/4/3.
 */

public class CustomSignInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        // 1 生成一个新的 request 增加拦截器，用以加入公用头部
//        Request.Builder builder = chain.request().newBuilder();
//        builder.headers(Headers.of(CommonHeaders.getCommonHeaders()));
//        Request request = builder.build();
        // 2 使用默认的 request
        Request request = chain.request();
        return chain.proceed(request);
    }
}
