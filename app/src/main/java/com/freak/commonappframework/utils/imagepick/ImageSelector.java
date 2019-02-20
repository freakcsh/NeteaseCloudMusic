package com.freak.commonappframework.utils.imagepick;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.freak.commonappframework.BuildConfig;

import java.io.File;

/**
 *
 * @author freak
 * @date 2019/1/14
 */

public class ImageSelector {

    /**
     * 拍照
     */
    public static final int GET_PICTURE_TAKE_PHOTO = 10001;
    /**
     * 选择手机内的图片
     */
    public static final int GET_PICTURE_SELECT_PHOTO = 10002;
    /**
     * 裁剪图片
     */
    public static final int CUT_PHOTO = 10003;
    private static ImageSelector mInstance;
    /**
     * 缓存拍照图片路径
     */
    public  File takePhotoCacheDir = null;
    private Uri mUri;

    public Uri getUri() {
        return mUri;
    }

    public void setUri(Uri uri) {
        mUri = uri;
    }

    public static ImageSelector getInstance() {
        if (mInstance == null) {
            synchronized (ImageSelector.class) {
                if (mInstance == null) {
                    mInstance = new ImageSelector();
                }
            }
        }
        return mInstance;
    }
    /**
     * 获取图片存储路径
     * @return
     */
    public  File getTakePhotoCacheDir() {
        return takePhotoCacheDir;
    }

    /**
     * 设置图片存储路径
     * @param cacheAddress
     */
    public void setTakePhotoCacheDir(String cacheAddress) {
        takePhotoCacheDir = CachePathUtil.getCachePathFile(cacheAddress);
    }

    /**
     * 初始化尺寸工具
     * @param context
     */
    public void initDisplayOpinion(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        DisplayUtil.density = dm.density;
        DisplayUtil.densityDPI = dm.densityDpi;
        DisplayUtil.screenWidthPx = dm.widthPixels;
        DisplayUtil.screenHeightPx = dm.heightPixels;
        DisplayUtil.screenWidthDip = DisplayUtil.px2dip(context, dm.widthPixels);
        DisplayUtil.screenHeightDip = DisplayUtil.px2dip(context, dm.heightPixels);
    }

    /**
     * 发起拍照
     */
    public static File takePicture(Context mContext, int requestCode) {
        String imgUrl = "";
        File f = null;
        try {
            File dir = ImageSelector.getInstance().getTakePhotoCacheDir();
            String key = String.valueOf(System.currentTimeMillis());
            if (!dir.exists()) {
                dir.mkdir();
            }
            f = new File(dir, key + ".jpg");
            imgUrl = f.getAbsolutePath();
            Uri u = toURI(mContext, f);
            Intent intent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, u);
            intent.putExtra("imgurl", imgUrl);
            ((Activity) mContext).startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    /**
     * 相册选择图片
     *
     * @return
     */
    public static void photoPick(Context mContext, int requestCode) {
        try {
            Intent getAlbum = new Intent(Intent.ACTION_PICK);
            getAlbum.setType("image/*");
            ((Activity) mContext).startActivityForResult(getAlbum, requestCode);
        } catch (Exception e) {

        }
    }

    /***
     * 从intent获取图片
     * @param data
     * @param mContext
     * @return
     */
    public static File getPhotoFromIntent(Intent data, Context mContext) {
        if (data == null) {
            return null;
        }
        try {
            File file = null;
            String path = "";
            if (data.getDataString().contains("content")) {
                Uri originalUri = data.getData();
                Cursor cursor = mContext.getContentResolver().query(originalUri,
                        null, null, null, null);
                // Source is Dropbox or other similar
                if (cursor == null) {
                    // local file path
                    path = originalUri.getPath();
                } else {
                    cursor.moveToFirst();
                    int idx = cursor
                            .getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    path = cursor.getString(idx);
                    cursor.close();
                }
            } else {
                path = data.getDataString().replace("file://", "");
            }
            if (!TextUtils.isEmpty(path)) {
                file = new File(path);
                return file;
            } else {
                return null;
            }
        } catch (Exception e) {
            Toast.makeText(mContext, "获取图片失败", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    /**
     * 发起裁剪
     */
    public static File cutPicture(Context mContext, File selectPhoto) {
        try {
            if (selectPhoto == null) {
                return null;
            }

            File dir = ImageSelector.getInstance().getTakePhotoCacheDir();
            String key = String.valueOf(System.currentTimeMillis());
            if (!dir.exists()) {
                dir.mkdir();
            }
            File outfile = new File(dir, key + ".jpg");
            Uri uri = toURI(mContext, selectPhoto);
            Uri outUri = Uri.fromFile(outfile);
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            cropIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            // 图片来源
            cropIntent.setDataAndType(uri, "image/*");
            // 设置剪裁剪属性
            cropIntent.putExtra("crop", "true");
            //    cropIntent.putExtra("aspectX", 1);
            //   cropIntent.putExtra("aspectY", 3);
            // 输出的坐标
            cropIntent.putExtra("outputX", 500);
            cropIntent.putExtra("outputY", 500);
            // 返回剪裁的图片数据
            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, outUri);
            cropIntent.putExtra("return-data", false);
            ((Activity) mContext).startActivityForResult(cropIntent,
                    CUT_PHOTO);
            return outfile;
        } catch (Exception e) {
            return selectPhoto;
        }
    }

    public static Uri toURI(Context mContext, File file) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return Uri.fromFile(file);
        } else {
            return FileProvider.getUriForFile(
                    mContext,
                    BuildConfig.APPLICATION_ID + ".fileProvider",
                    file);
        }
    }

}
