package com.freak.commonappframework.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author freak
 * @date 2019/2/19
 */

public class ShareUtils {

    /**
     * 单图片分享
     */
    public static void share(Context context, File imageFile) {
        Intent intent = new Intent();
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = getImageContentUri(context, imageFile);
        } else {
            uri = Uri.fromFile(imageFile);
        }
        //设置分享行为
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.setAction("android.intent.action.SEND");
        //设置分享内容的类型
        intent.setType("image/png");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(intent, "分享"));
    }

    /**
     * 多图片分享
     */
    public static void share(Context context, List<File> imageFiles) {
        Intent share_intent = new Intent();
        List<Uri> uris = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            for (File file : imageFiles) {
                Uri uri = getImageContentUri(context, file);
                uris.add(uri);
            }
        } else {
            for (File file : imageFiles) {
                Uri uri = Uri.fromFile(file);
                uris.add(uri);
            }
        }
        //设置分享行为
        share_intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        share_intent.setAction("android.intent.action.SEND");
        //设置分享内容的类型
        share_intent.setType("image/png");
        share_intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, (ArrayList<? extends Parcelable>) uris);
        context.startActivity(Intent.createChooser(share_intent, "分享"));
    }

    private static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

}
