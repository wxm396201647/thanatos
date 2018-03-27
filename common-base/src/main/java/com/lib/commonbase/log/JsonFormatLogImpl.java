package com.lib.commonbase.log;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 格式化 json 的打印实现
 */
public class JsonFormatLogImpl implements LogInterface {
    public static final int VERBOSE = 0;
    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int WARNING = 3;
    public static final int ERROR = 4;
    private static final String LINE_SEPARATOR = ",";
    private static final String headerDesc = "Log 打印";

    @Override
    public void v(String tag, String log) {
        printJson(VERBOSE, tag, tag);
    }

    @Override
    public void d(String tag, String log) {
        printJson(DEBUG, tag, tag);
    }

    @Override
    public void i(String tag, String log) {
        printJson(INFO, tag, tag);
    }

    @Override
    public void w(String tag, String log) {
        printJson(WARNING, tag, tag);
    }

    @Override
    public void e(String tag, String log) {
        printJson(ERROR, tag, log);
    }

    @Override
    public void e(String tag, Throwable e) {
        printJson(ERROR, tag, Log.getStackTraceString(e));
    }

    public void printLine(int level, String tag, boolean isTop) {
        if (isTop) {
            printLog(level, tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            printLog(level, tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

    public void printJson(int level, String tag, String msg) {
        String message;
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                // 最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
                message = jsonObject.toString(4);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(4);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        printLine(level, tag, true);
        message = headerDesc + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            printLog(level, tag, line);
        }
        printLine(level, tag, false);
    }


    private void printLog(int logType, String tag, String line) {
        switch (logType) {
            case VERBOSE:
                Log.v(tag, line);
                break;
            case DEBUG:
                Log.d(tag, line);
                break;
            case INFO:
                Log.i(tag, line);
                break;
            case WARNING:
                Log.w(tag, line);
                break;
            case ERROR:
                Log.e(tag, line);
                break;
            default:
                break;
        }
    }
}
