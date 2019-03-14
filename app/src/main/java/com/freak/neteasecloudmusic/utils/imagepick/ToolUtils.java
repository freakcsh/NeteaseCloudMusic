package com.freak.neteasecloudmusic.utils.imagepick;

import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;


/**
 *
 * @author freak
 * @date 2019/1/14
 */

public class ToolUtils {
    /**
     * PopupWindow居中。
     * 设置焦点后再调用该方法才能点击外部消失
     *
     * @param popup
     * @param parent
     * @param x
     * @param y
     */
    public static void popupWindowShowCenter(PopupWindow popup, View parent, int x, int y) {
        popup.showAtLocation(parent, Gravity.CENTER, 0, 0);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            popup.update();
        } else {
            popup.dismiss();
            popup.showAtLocation(parent, Gravity.CENTER, 0, 0);
        }

    }
}
