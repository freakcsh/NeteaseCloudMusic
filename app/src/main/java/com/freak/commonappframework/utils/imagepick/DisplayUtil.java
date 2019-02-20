package com.freak.commonappframework.utils.imagepick;

import android.content.Context;

/**
 * @author freak
 * @date 2019/1/14
 */
public class DisplayUtil {
    /**
     * 屏幕宽 px
     */
    public static int screenWidthPx;
    /**
     * 屏幕高 px
     */
    public static int screenHeightPx;
    /**
     * 屏幕密度
     */
    public static float density;
    /**
     * 屏幕密度
     */
    public static int densityDPI;
    /**
     * dp单位
     */
    public static float screenWidthDip;
    /**
     * dp单位
     */
    public static float screenHeightDip;


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
