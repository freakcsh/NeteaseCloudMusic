package com.freak.neteasecloudmusic.utils;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;


/**
 * @author freak
 * @date 2019/2/19
 */
public class TintUtils {

    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }

    public static GradientDrawable createColorShape(int color, float topLeft, float topRight, float bottomRight, float bottomLeft) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadii(new float[]{topLeft, topLeft, topRight, topRight, bottomRight, bottomRight, bottomLeft, bottomLeft});
        drawable.setColor(color);
        return drawable;
    }
}
