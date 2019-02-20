package com.freak.commonappframework.utils.imagepick;

import java.io.File;
import java.util.List;

/**
 *
 * @author freak
 * @date 2019/1/14
 */

public interface ICompressImageResponse {
    /**
     * 压缩成功
     * @param images
     */
    void onSuccess(List<File> images);

    /**
     * 压缩中
     */
    void onMarch();

    /**
     * 压缩失败
     */
    void onFail();

    /**
     * 压缩结束
     */
    void onFinish();
}
