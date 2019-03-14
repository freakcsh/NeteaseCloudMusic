package com.freak.neteasecloudmusic.utils.imagepick;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author freak
 * @date 2019/1/14
 */

public class BitmapUtil {
    /**
     * 压缩图片
     */
    public static void compressFiles(List<File> files, ICompressImageResponse response) {

        CompressImageCacheTask cacheTask = new CompressImageCacheTask(files, response);
        cacheTask.execute(100);
    }

    /**
     * 压缩图片，处理某些手机拍照角度旋转的问题
     *
     * @param filePath 文件路径
     * @param fileName 文件名
     * @param q
     * @return
     * @throws FileNotFoundException
     */
    public static String compressImage(String filePath, String fileName, int q)
            throws FileNotFoundException {

        Bitmap bm = getSmallBitmap(filePath);

        int degree = readPictureDegree(filePath);
        // 旋转照片角度
        if (degree != 0) {
            bm = rotateBitmap(bm, degree);
        }
        File imageDir = ImageSelector.getInstance().getTakePhotoCacheDir();

        if (!imageDir.exists()) {
            imageDir.mkdirs();
        }

        File outputFile = new File(imageDir, fileName);

        FileOutputStream out = new FileOutputStream(outputFile);

        bm.compress(Bitmap.CompressFormat.JPEG, q, out);
        bm.recycle();

        return outputFile.getPath();
    }

    /**
     * 根据路径获得图片并压缩，返回bitmap用于显示
     *
     * @param filePath
     * @return
     */
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 读取图片角度
     *
     * @param path
     * @return
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转图片
     * @param bitmap
     * @param degree
     * @return
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, int degree) {
        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(degree);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), m, true);
            return bitmap;
        }
        return bitmap;
    }

    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
}
