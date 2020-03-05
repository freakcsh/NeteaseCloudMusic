package com.freak.neteasecloudmusic.aop;

import android.os.SystemClock;
import android.view.View;

import com.freak.neteasecloudmusic.net.log.LogUtil;
import com.freak.neteasecloudmusic.utils.ToastUtil;


/**
 *
 * @author Administrator
 * @date 2019/4/23
 */

public class AopClickUtil {
    /**
     * 最近一次点击的时间
     */
    private static long mLastClickTime;
    /**
     * 最近一次点击的控件ID
     */
    private static int mLastClickViewId;

    /**
     * 是否是快速点击
     *
     * @param v  点击的控件
     * @param intervalMillis  时间间期（毫秒）
     * @return  true:是，false:不是
     */
    public static boolean isFastDoubleClick(View v, long intervalMillis) {
        int viewId = v.getId();
        LogUtil.e("执行判断是否是快速点击");
//        long time = System.currentTimeMillis();
        long time = SystemClock.elapsedRealtime();
        long timeInterval = Math.abs(time - mLastClickTime);
        if (timeInterval < intervalMillis && viewId == mLastClickViewId) {
            ToastUtil.shortShow("请勿快速重复点击");
            return true;
        } else {
            mLastClickTime = time;
            mLastClickViewId = viewId;
            return false;
        }
    }
}
