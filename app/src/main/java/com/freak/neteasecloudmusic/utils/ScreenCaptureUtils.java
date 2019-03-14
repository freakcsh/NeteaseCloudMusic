package com.freak.neteasecloudmusic.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author freak
 * @date 2019/2/19
 */

public class ScreenCaptureUtils {
    /**
     * 截取页面，并分享
     *
     * @param context     Context
     * @param contentView 父容器，包含所以分享内容的容器
     * @param goneView    不需要显示的view
     */
    public void ScreenCapture(Context context, View contentView, View goneView) {
        try {
            goneView.setVisibility(View.INVISIBLE);
            Bitmap bitmap = Bitmap.createBitmap(contentView.getWidth(), contentView.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            contentView.draw(canvas);
            goneView.setVisibility(View.VISIBLE);
            File fileDir = context.getExternalFilesDir(null);
            fileDir.mkdirs();
            File file = new File(fileDir, "share.png");
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.close();
            ShareUtils.share(context, file);
        } catch (IOException e) {
            e.printStackTrace();
            ToastUtil.show("分享异常");
        }
    }

    /**
     * 截取页面，并分享
     *
     * @param context     Context
     * @param contentView 父容器，包含所以分享内容的容器
     */
    public void ScreenCapture(Context context, View contentView) {
        try {
            Bitmap bitmap = Bitmap.createBitmap(contentView.getWidth(), contentView.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            contentView.draw(canvas);
            File fileDir = context.getExternalFilesDir(null);
            fileDir.mkdirs();
            File file = new File(fileDir, "share.png");
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.close();
            ShareUtils.share(context, file);
        } catch (IOException e) {
            e.printStackTrace();
            ToastUtil.show("分享异常");
        }
    }
}
