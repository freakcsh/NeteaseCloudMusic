package com.freak.commonappframework.compress;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;

import com.freak.commonappframework.BuildConfig;
import com.freak.commonappframework.utils.LogUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import top.zibin.luban.OnRenameListener;

/**
 * 鲁班图片压缩工具类
 *
 * @author freak
 * @date 2019/2/20
 */

public class LuBanCompressUtils {
    private static CompositeDisposable mDisposable;
    private static LuBanCompressUtils sLuBanCompressUtils;
    private Context mContext;
    private static final String TAG = "LuBanCompressUtils";
    private ICompressCallBack mICompressCallBack;

    public LuBanCompressUtils setICompressCallBack(ICompressCallBack ICompressCallBack) {
        mICompressCallBack = ICompressCallBack;
        return this;
    }

    public static LuBanCompressUtils getInstance() {
        if (sLuBanCompressUtils == null) {
            synchronized (LuBanCompressUtils.class) {
                sLuBanCompressUtils = new LuBanCompressUtils();
                if (mDisposable == null) {
                    mDisposable = new CompositeDisposable();
                }
            }
        }
        return sLuBanCompressUtils;
    }

    /**
     * 为了减少每次调用方法都需要传入context，所以这个方法要固定调用
     *
     * @param context Context
     * @return
     */
    public LuBanCompressUtils setContext(Context context) {
        mContext = context;
        return this;
    }

    /**
     * 异步压缩
     *
     * @param photos
     * @param <T>
     */
    public  <T> void withAsynchronization(final List<T> photos) {
        mDisposable.add(Flowable.just(photos)
                .observeOn(Schedulers.io())
                .map(new Function<List<T>, List<File>>() {
                    @Override
                    public List<File> apply(@NonNull List<T> list) throws Exception {
                        return Luban.with(mContext)
                                .setTargetDir(getPath())
                                .load(list)
                                .get();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Log.e(TAG, throwable.getMessage());
                    }
                })
                .onErrorResumeNext(Flowable.<List<File>>empty())
                .subscribe(new Consumer<List<File>>() {
                    @Override
                    public void accept(@NonNull List<File> list) {
                        for (File file : list) {
                            Log.i(TAG, file.getAbsolutePath());
                            showResult(file, false);
                            if (mICompressCallBack != null) {
                                mICompressCallBack.CompressSuccess(file);
                            }
                        }
                    }
                }));
    }

    /**
     * 同步压缩
     *
     * @param photos
     * @param <T>
     */
    public <T> void withSynchronized(final List<T> photos) {
        Luban.with(mContext)
                .load(photos)
                .ignoreBy(100)
                .setTargetDir(getPath())
                .setFocusAlpha(false)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setRenameListener(new OnRenameListener() {
                    @Override
                    public String rename(String filePath) {
                        try {
                            MessageDigest md = MessageDigest.getInstance("MD5");
                            md.update(filePath.getBytes());
                            return new BigInteger(1, md.digest()).toString(32);
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                        return "";
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {
                        Log.i(TAG, file.getAbsolutePath());
                        showResult(file, false);
                        if (mICompressCallBack != null) {
                            mICompressCallBack.CompressSuccess(file);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                }).launch();
    }

    /**
     * 显示原图与压缩后的图片大小
     *
     * @param file     文件
     * @param isOrigin 是否是原图
     */
    private void showResult(File file, boolean isOrigin) {
        if (isOrigin) {
            int[] originSize = computeSize(file);
            String originArg = String.format(Locale.CHINA, "原图参数：%d*%d, %dk", originSize[0], originSize[1], file.length() >> 10);
            LogUtils.e(originArg);
        } else {
            int[] thumbSize = computeSize(file);
            String thumbArg = String.format(Locale.CHINA, "压缩后参数：%d*%d, %dk", thumbSize[0], thumbSize[1], file.length() >> 10);
            LogUtils.e(thumbArg);
        }
    }

    /**
     * 获取缓存路径
     *
     * @return
     */
    private String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    /**
     * 计算图片大小
     *
     * @param srcImg
     * @return
     */
    private int[] computeSize(File srcImg) {
        int[] size = new int[2];

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;

        BitmapFactory.decodeFile(srcImg.getAbsolutePath(), options);
        size[0] = options.outWidth;
        size[1] = options.outHeight;

        return size;
    }

    /**
     * assets文件夹下的文件路径转换为file
     *
     * @param assetsFileName
     * @return
     */
    public List<File> assetsToFiles(List<String> assetsFileName) {
        final List<File> files = new ArrayList<>();

        for (int i = 0; i < assetsFileName.size(); i++) {
            try {
                InputStream is = mContext.getResources().getAssets().open(assetsFileName.get(i));
                File file = new File(mContext.getExternalFilesDir(null), "test_" + i);
                FileOutputStream fos = new FileOutputStream(file);

                byte[] buffer = new byte[4096];
                int len = is.read(buffer);
                while (len > 0) {
                    fos.write(buffer, 0, len);
                    len = is.read(buffer);
                }
                fos.close();
                is.close();

                files.add(file);
                showResult(file, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return files;
    }

    /**
     * assets文件夹下的文件路径转换为uri
     *
     * @param assetsFileName assetsFileName
     * @return
     */
    public List<Uri> assetsToUri(List<String> assetsFileName) {
        final List<Uri> uris = new ArrayList<>();
        final List<File> files = assetsToFiles(assetsFileName);
        Uri uri;
        for (int i = 0; i < assetsFileName.size(); i++) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                uri = Uri.fromFile(files.get(i));
            } else {
                uri = FileProvider.getUriForFile(
                        mContext,
                        BuildConfig.APPLICATION_ID + ".fileProvider",
                        files.get(i));
            }
            uris.add(uri);
        }

        return uris;
    }

    /**
     * 文件转uri
     *
     * @param mContext Context
     * @param file     文件
     * @return
     */
    public  Uri toURI(Context mContext, File file) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return Uri.fromFile(file);
        } else {
            return FileProvider.getUriForFile(
                    mContext,
                    BuildConfig.APPLICATION_ID + ".fileProvider",
                    file);
        }
    }

    /**
     * 压缩完成一定要调用
     */
    public void clearDisposable() {
        mDisposable.clear();
    }
}
