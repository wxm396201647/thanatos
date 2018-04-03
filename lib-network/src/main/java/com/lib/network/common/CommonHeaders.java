package com.lib.network.common;

import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * Created by wang.ming5 on 2018/4/3.
 */

public class CommonHeaders {

    /**
     * 通用的http请求头 header
     */
    private static HashMap<String, String> sCommonHeaders;

    static {
        sCommonHeaders = Maps.newHashMap();
        //此处可以添加通用头

//        sCommonHeaders.put("deviceid", MyDeviceInfo.getDeviceId(BaseContext.getInstance()));//设备id
//
//        sCommonHeaders.put("devicebrand", MyDeviceInfo.getDeviceName());//设备型号
//        sCommonHeaders.put("systembrand", MyDeviceInfo.getOsVersion());//操作系统版本
//        sCommonHeaders.put("version", SysUtils.getVersionName(BaseContext.getInstance()));//应用版本
//        sCommonHeaders.put("APPkey", Constants.APPKEY);//应用名称
    }

    /**
     * 设置通用的http请求的header
     */
    public static HashMap<String, String> getCommonHeaders() {
        HashMap<String, String> signMap = Maps.newHashMap();
        signMap.putAll(sCommonHeaders);
        signMap.put("timestamp", System.currentTimeMillis() + "");//当前时间

//        signMap.put("appid", Constants.APPKEY);

        return signMap;
    }
}
