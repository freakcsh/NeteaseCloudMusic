package com.freak.commonappframework.utils;

import android.util.Log;

import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 *
 * @author Administrator
 * @date 2018/4/8
 */

public class LogUtils {

    public static void e(String text) {
        Logger.e(text);
    }

    public static void e(Map<String, String> map) {
        Logger.e("请求参数-->" + map);
    }

    public static void json(String logTip, String json) {
        Logger.json("{\"" + logTip + "\":" + json + "}");
    }

    public static void json(String apiServer,String logTip, String json) {
        Logger.json("{\"" + "APIServer地址" + "\":"+"\"" + "\\"+apiServer + "\","
                +"\"" + "LogTip" + "\":"+"\"" + logTip + "\","
                +"\"" + "接口返回数据" + "\":"+  json + "}");
    }

    public static void d(Object object) {
        Logger.d(object);
    }

    public static void d(String key, String value) {
        Log.d(key, value);
    }

}
