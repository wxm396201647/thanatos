package com.lib.network.request;

import com.lib.network.callback.RequestCallback;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.converter.GsonDiskConverter;
import com.zhouyou.http.cache.model.CacheMode;
import com.zhouyou.http.model.HttpHeaders;

import io.reactivex.disposables.Disposable;

/**
 * Created by wang.ming5 on 2018/4/3.
 */

public class HttpRequest {


    public static Disposable get(String url, RequestCallback callback) {
        return EasyHttp.get(url)
                .writeTimeOut(30*1000)//局部写超时30s,单位毫秒
                .readTimeOut(30*1000)//局部读超时30s,单位毫秒
                .connectTimeout(30*1000)//局部连接超时30s,单位毫秒
                .headers(new HttpHeaders("header1","header1Value"))//添加请求头参数
//                .cacheTime(300)//缓存300s 单位s
//                .cacheKey("cachekey")//缓存key
//                .cacheMode(CacheMode.CACHEANDREMOTE)//设置请求缓存模式
//                .okCache()//使用模式缓存模式时，走Okhttp缓存
//                .cacheDiskConverter(new GsonDiskConverter())//GSON-数据转换器
                .retryCount(5)//本次请求重试次数
                .retryDelay(500)//本次请求重试延迟时间500ms
                .timeStamp(false)//本次请求是否携带时间戳
                .sign(false)//本次请求是否需要签名
                .syncRequest(false)//是否是同步请求，默认异步请求。true:同步请求
                .execute(callback);
    }

    public static void cancelSubscription(Disposable disposable) {
        EasyHttp.cancelSubscription(disposable);
    }
}
