package com.freak.neteasecloudmusic.compress;

import java.io.File;

/**
 * 鲁班图片压缩回调
 *
 * @author freak
 * @date 2019/2/20
 */
public interface ICompressCallBack {
    /**
     * 压缩成功回调
     *
     * @param file 文件
     * @return
     */
    void CompressSuccess(File file);
}
