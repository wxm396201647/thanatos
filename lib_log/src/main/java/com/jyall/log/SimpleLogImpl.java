package com.jyall.log;

import android.util.Log;

/**
 * 简易 log 实现，可以在不需要详细Log信息的时候比如正式环境下使用,以提升性能
 */
public class SimpleLogImpl implements LogInterface {
    @Override
    public void v(String tag, String log) {
        Log.v(tag, log);
    }

    @Override
    public void d(String tag, String log) {
        Log.d(tag, log);
    }

    @Override
    public void i(String tag, String log) {
        Log.i(tag, log);
    }

    @Override
    public void w(String tag, String log) {
        Log.w(tag, log);
    }

    @Override
    public void e(String tag, String log) {
        Log.e(tag, log);
    }

    @Override
    public void e(String tag, Throwable e) {
        Log.e(tag, Log.getStackTraceString(e));
    }
}