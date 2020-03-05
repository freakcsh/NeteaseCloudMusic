package com.freak.neteasecloudmusic.utils.imagepick.loader;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.loader.ImageLoader;


/**
 * @author freak
 * @date 2019/2/19
 */

public class ImagePickerGlideLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity).load(path).thumbnail(0.1f).into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity).load(path).thumbnail(0.1f).into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
