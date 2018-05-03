package com.thanatos.network;

import java.util.HashMap;

/**
 * 网络请求工具类
 */
public class CommonHeaders {

    /**
     * 通用的http请求头 header
     */
    private static HashMap<String, String> sCommonHeaders;

    public static void setsCommonHeaders(HashMap<String, String> map) {
        sCommonHeaders = new HashMap<>(map);
    }

    /**
     * 设置通用的http请求的header
     */
    public static HashMap<String, String> getCommonHeaders() {
        HashMap<String, String> signMap = new HashMap<>();
        signMap.putAll(sCommonHeaders);
        signMap.put("timestamp", System.currentTimeMillis() + "");//当前时间
        return signMap;
    }
}
