package com.thanatos.network.network;

/**
 * Created by wang.xiaolong5 on 2017/11/3.
 */

public class ResponseCode {
    /**
     * 业务正确
     */
    public static final int SUCCESS = 200;
    /**
     * 业务错误
     */
    public static final int BAD_REQUEST = 400;
    /**
     * 强制更新
     */
    public static final int UPDATE_FORCE = 400009000;
    /**
     * token过期 需要强制登录
     */
    public static final int ACCESS_TOKEN_INVALID = 400001045;
    public static final int TICKET_UNAVALIBLE = 400000023;//ticket无效的请求参数
    public static final int TICKET_UNAVALIBLE2 = 400000024;//ticket无效的请求参数
    public static final int TICKET_UNAVALIBLE3 = 400000002;//ticket不存在或者已经失效
    public static final int NO_LOGIN = 400001012;//接口未登录统一返这个code

}
