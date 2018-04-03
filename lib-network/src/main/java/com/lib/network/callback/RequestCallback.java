package com.lib.network.callback;

import com.zhouyou.http.callback.CallBack;
import com.zhouyou.http.exception.ApiException;

/**
 * Created by wang.ming5 on 2018/4/3.
 *
 */

public abstract class RequestCallback<T> extends CallBack<T>{
    @Override
    public void onStart() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(ApiException e) {

    }

    @Override
    public void onSuccess(T t) {

    }
}
