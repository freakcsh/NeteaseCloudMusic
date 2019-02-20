package com.freak.commonappframework.utils.imagepick.loader;

import android.app.Activity;
import android.widget.ImageView;

import com.freak.commonappframework.glide.GlideApp;
import com.lzy.imagepicker.loader.ImageLoader;


/**
 *
 * @author Administrator
 * @date 2018/4/10
 */

public class ImagePickerGlideLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        GlideApp.with(activity).load(path).thumbnail(0.1f).into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        GlideApp.with(activity).load(path).thumbnail(0.1f).into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
