package com.thanatos.network.network;

/**
 * Created by wang.xiaolong5 on 2017/6/2.
 */

public class ErrorResponseBean<T> {
    public Integer code;
    public String message;
    public String detail;
    public T data;
}
