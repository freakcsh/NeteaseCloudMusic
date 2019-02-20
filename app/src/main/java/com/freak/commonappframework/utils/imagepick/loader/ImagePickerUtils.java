package com.freak.commonappframework.utils.imagepick.loader;

import android.app.Activity;
import android.content.Intent;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择图片工具类
 *
 * @author freak
 * @date 2019/2/20
 */

public class ImagePickerUtils {
    private final int RESULT_CODE_IMAGE = 1001;

    /**
     * 打开imagePicker
     *
     * @param activity Activity
     * @param num      可选择多少张图片
     */
    public void openImagePicker(Activity activity, int num) {
        ImagePicker.getInstance().setSelectLimit(num);
        Intent intent = new Intent(activity, ImageGridActivity.class);
        activity.startActivityForResult(intent, RESULT_CODE_IMAGE);
    }

    /**
     * 选择图片的回调结果，在onActivityResult方法中调用
     *
     * @param requestCode 请求码
     * @param resultCode  结果码
     * @param data        数据
     * @return
     */
    public List<ImageItem> selectImageResult(int requestCode, int resultCode, Intent data) {
        List<ImageItem> images = new ArrayList<>();
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == RESULT_CODE_IMAGE) {
                images = (List<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                return images;
            }
        }
        return images;
    }
}
