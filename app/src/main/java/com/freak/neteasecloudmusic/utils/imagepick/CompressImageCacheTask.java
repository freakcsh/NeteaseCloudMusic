package com.freak.neteasecloudmusic.utils.imagepick;

import android.os.AsyncTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author freak
 * @date 2019/1/14
 */

public class CompressImageCacheTask extends AsyncTask<Integer, Integer, Boolean> {
    // 后面尖括号内分别是参数（例子里是线程休息时间），进度(publishProgress用到)，返回值 类型

    public ICompressImageResponse response;
    public List<File> files;
    public List<File> resultFile;

    public CompressImageCacheTask(List<File> files, ICompressImageResponse response) {
        this.files = files;
        this.response = response;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Integer... params) {
        publishProgress(0);
        resultFile = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {

            File of = files.get(i);
            try {
                String key = String.valueOf(System.currentTimeMillis());
                File file = new File(BitmapUtil.compressImage(of.getAbsolutePath(),
                        key + "_image.jpg", 50));
                resultFile.add(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        // 这个函数在doInBackground调用publishProgress时触发，虽然调用时只有一个参数
        // 但是这里取到的是一个数组,所以要用progesss[0]来取值
        // 第n个参数就用progress[n]来取值
        response.onMarch();
        super.onProgressUpdate(progress);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        // doInBackground返回时触发，换句话说，就是doInBackground执行完后触发
        // 这里的result就是上面doInBackground执行后的返回值，所以这里是"执行完毕"
        if (result) {
            response.onSuccess(resultFile);
        } else {
            response.onFail();
        }

        response.onFinish();

        super.onPostExecute(result);
    }
}
