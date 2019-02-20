package com.freak.commonappframework.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.ColorInt;
import android.view.inputmethod.InputMethodManager;

import com.freak.commonappframework.app.App;


/**
 * 该类是用以保存一些和状态相关方法的工具类
 *
 * @author freak
 * @date 2019/2/19
 */

public class StateUtil {
    /**
     * 网络是否可连接
     *
     * @return 网络能否连接
     */
    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivity = (ConnectivityManager) App.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 关闭Activity所有编辑状态
     *
     * @param context 当前要关闭编辑状态的Activity
     */
    public static void endAllEdit(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(((Activity) context).getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    /**
     * 关闭Dialog所有编辑状态
     *
     * @param dialog 当前要关闭编辑状态的Dialog
     */
    public static void endAllEdit(Dialog dialog) {
        InputMethodManager inputMethodManager = (InputMethodManager) dialog.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(dialog.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public static void transStatusColor(Activity activity, @ColorInt int color) {
        if (Build.VERSION.SDK_INT > 21) {
            activity.getWindow().setStatusBarColor(color);
        }
    }

    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
