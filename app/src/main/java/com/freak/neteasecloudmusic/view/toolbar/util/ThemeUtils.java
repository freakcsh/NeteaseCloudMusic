package com.freak.neteasecloudmusic.view.toolbar.util;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;

/**
 * Created by Administrator on 2019/3/14.
 */

public class ThemeUtils {
    public static SwitchColor mSwitchColor;

    /**
     * 通过res替换颜色
     *
     * @param context
     * @param color
     * @return
     */
    @ColorInt
    static int replaceColorById(Context context, @ColorRes int color) {
        return mSwitchColor == null ? Color.TRANSPARENT : mSwitchColor.replaceColorById(context, color);
    }

    /**
     * 通过color 色值改变颜色
     *
     * @param context
     * @param color
     * @return
     */
    @ColorInt
    static int replaceColor(Context context, @ColorInt int color) {
        return mSwitchColor == null ? Color.TRANSPARENT : mSwitchColor.replaceColor(context, color);
    }

    public interface SwitchColor {
        /**
         * 通过color id 替换颜色
         *
         * @param context
         * @param colorId color res
         * @return
         */
        @ColorInt
        int replaceColorById(Context context, @ColorRes int colorId);

        /**
         * 通过color id 替换颜色
         *
         * @param context
         * @param color   color id
         * @return
         */
        @ColorInt
        int replaceColor(Context context, @ColorInt int color);
    }
}
