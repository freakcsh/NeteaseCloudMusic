package com.freak.commonappframework.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.freak.commonappframework.app.App;


/**
 *
 * @author Administrator
 * @date 2017/8/18
 * 该类是用以保存一些和长度、尺寸等内容相关的工具类
 */

public class SizeUtils {
    /**
     * 获取屏幕尺寸
     */
    public static DisplayMetrics screenSize() {
        DisplayMetrics displayMetrics = App.getInstance().getApplicationContext().getResources().getDisplayMetrics();
        return displayMetrics;
    }

    /**
     * 相对尺寸转换，将相对基准base下的长度width转换为相对当前屏幕宽的尺寸
     * @param width 相对尺寸
     * @param base 基准尺寸
     * @return  相对尺寸
     */
    public static int transRatio(float width, float base) {
        DisplayMetrics dm = App.getInstance().getApplicationContext().getResources().getDisplayMetrics();
        return (int) (width * dm.widthPixels / base);
    }

    /**
     * 相对字体大小转换，将相对基准base下的字体大小width转换为相对当前屏幕的字体大小
     * @param width 相对字体大小
     * @param base 基准尺寸
     * @return  相对字体大小
     */
    public static int transRationSp(float width, float base) {
        DisplayMetrics dm = App.getInstance().getApplicationContext().getResources().getDisplayMetrics();
        return  (int)(width * dm.widthPixels / (base * dm.scaledDensity));
    }

    public static void setDialogAttribute(Context context, Dialog dialog, double x, double y) {
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams params = dialogWindow.getAttributes();
        Point middlePoint = new Point();
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);

        Display display = windowManager.getDefaultDisplay();
        display.getSize(middlePoint);
        if (x != 0) {params.width = x > 1 ? transFromDip((int) x) : (int)(middlePoint.x * x);}
        if (y != 0) {params.height = y > 1 ? transFromDip((int)y) : (int)(middlePoint.y * y);}
        dialogWindow.setAttributes(params);
    }

    public static int transFromDip(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, App.getInstance().getApplicationContext().getResources().getDisplayMetrics());
    }


}
