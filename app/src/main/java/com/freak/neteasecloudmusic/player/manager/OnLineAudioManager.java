package com.freak.neteasecloudmusic.player.manager;

/**
 * 在线音频管理
 *
 * @author Freak
 * @date 2019/5/27.
 */

public class OnLineAudioManager {
//
//    /**
//     * 子线程用于执行耗时任务
//     */
//    private Handler mWorkerHandler;
//    //创建异步HandlerThread
//    private HandlerThread mHandlerThread;
//
//    /**
//     * 线程个数
//     */
//    public static final int mThreadNum = 1;
//    /**
//     * 当前任务id
//     */
//    private String mCurTaskId = "-1";
//
//    /**
//     * 下载管理器
//     */
//    private static DownloadTaskManager mDownloadTaskManager;
//
//    /**
//     *
//     */
//    private static Context mContext;
//
//    public OnLineAudioManager(Context context) {
//
//        this.mContext = context;
//        //创建异步HandlerThread
//        mHandlerThread = new HandlerThread("onlineDownloadAudioTaskThread", Process.THREAD_PRIORITY_BACKGROUND);
//        //必须先开启线程
//        mHandlerThread.start();
//        //子线程Handler
//        mWorkerHandler = new Handler(mHandlerThread.getLooper());
//
//        mDownloadTaskManager = new DownloadTaskManager(context, "onlineDownloadAudioTaskManager", new IDownloadTaskEvent() {
//            @Override
//            public void taskWaiting(DownloadTask task) {
//
//            }
//
//            @Override
//            public void taskDownloading(DownloadTask task, int downloadedSize) {
//
//                ZLog.d(new CodeLineUtil().getCodeLineInfo(), "task taskDownloading ->" + task.getTaskName() + " " + downloadedSize);
//                //更新在线缓存进度
//                AudioBroadcastReceiver.sendDownloadingOnlineSongReceiver(mContext, task);
//                //处理下载歌曲
//                handleDondloadNetSong(task, downloadedSize);
//
//            }
//
//            @Override
//            public void taskPause(DownloadTask task, int downloadedSize) {
//
//                ZLog.d(new CodeLineUtil().getCodeLineInfo(), "task taskPause ->" + task.getTaskName() + " " + downloadedSize);
//            }
//
//            @Override
//            public void taskCancel(DownloadTask task) {
//                ZLog.d(new CodeLineUtil().getCodeLineInfo(), "task taskCancel ->" + task.getTaskName());
//            }
//
//            @Override
//            public void taskFinish(DownloadTask task, int downloadedSize) {
//
//                if (mCurTaskId.equals(task.getTaskId())) {
//                    //任务完成后，重置任务id
//                    mCurTaskId = "-1";
//                }
//                //更新在线缓存进度
//                AudioBroadcastReceiver.sendDownloadingOnlineSongReceiver(mContext, task);
//                ZLog.d(new CodeLineUtil().getCodeLineInfo(), "task taskFinish ->" + task.getTaskName() + " " + downloadedSize);
//                //完成
//                AudioBroadcastReceiver.sendDownloadedOnlineSongReceiver(mContext, task);
//            }
//
//            @Override
//            public void taskError(DownloadTask task, String msg) {
//                if (mCurTaskId.equals(task.getTaskId())) {
//                    //任务完成后，重置任务id
//                    mCurTaskId = "-1";
//                }
//                ZLog.d(new CodeLineUtil().getCodeLineInfo(), "task taskError ->" + task.getTaskName());
//                AppSystemReceiver.sendToastErrorMsgReceiver(mContext, msg);
//                AudioBroadcastReceiver.sendStopReceiver(mContext);
//            }
//
//            @Override
//            public boolean getAskWifi() {
//                ConfigInfo configInfo = ConfigInfo.obtain();
//                return configInfo.isWifi();
//            }
//
//            @Override
//            public int getTaskThreadDownloadedSize(DownloadTask task, int threadId) {
//                if (DownloadThreadInfoDB.isExists(mContext, task.getTaskId(), mThreadNum, threadId)) {
//                    //任务存在
//                    DownloadThreadInfo downloadThreadInfo = DownloadThreadInfoDB.getDownloadThreadInfo(mContext, task.getTaskId(), mThreadNum, threadId);
//                    if (downloadThreadInfo != null) {
//                        ZLog.d(new CodeLineUtil().getCodeLineInfo(), "task getTaskThreadDownloadedSize -> 在线播放任务名称：" + task.getTaskName() + " 子任务线程id: " + threadId + " 已下载大小：" + downloadThreadInfo.getDownloadedSize());
//                        return downloadThreadInfo.getDownloadedSize();
//                    }
//                }
//                return 0;
//            }
//
//            @Override
//            public void taskThreadDownloading(DownloadTask task, int threadId, int downloadedSize) {
//
//                DownloadThreadInfo downloadThreadInfo = new DownloadThreadInfo();
//                downloadThreadInfo.setDownloadedSize(downloadedSize);
//                downloadThreadInfo.setThreadId(threadId);
//                downloadThreadInfo.setTaskId(task.getTaskId());
//                downloadThreadInfo.setThreadNum(mThreadNum);
//
//                if (DownloadThreadInfoDB.isExists(mContext, task.getTaskId(), mThreadNum, threadId)) {
//                    //任务存在
//                    DownloadThreadInfoDB.update(mContext, task.getTaskId(), mThreadNum, threadId, downloadedSize);
//                } else {
//                    //任务不存在
//                    DownloadThreadInfoDB.add(mContext, downloadThreadInfo);
//                }
//            }
//
//            @Override
//            public void taskThreadPause(DownloadTask task, int threadId, int downloadedSize) {
//
//            }
//
//            @Override
//            public void taskThreadFinish(DownloadTask task, int threadId, int downloadedSize) {
//
//                //防止有些比较小的歌曲在1s内下载完成，没有调用downloading的接口
//
//                DownloadThreadInfo downloadThreadInfo = new DownloadThreadInfo();
//                downloadThreadInfo.setDownloadedSize(downloadedSize);
//                downloadThreadInfo.setThreadId(threadId);
//                downloadThreadInfo.setTaskId(task.getTaskId());
//                downloadThreadInfo.setThreadNum(mThreadNum);
//                if (DownloadThreadInfoDB.isExists(mContext, task.getTaskId(), mThreadNum, threadId)) {
//                    //任务存在
//                    DownloadThreadInfoDB.update(mContext, task.getTaskId(), mThreadNum, threadId, downloadedSize);
//                } else {
//                    //任务不存在
//                    DownloadThreadInfoDB.add(mContext, downloadThreadInfo);
//                }
//                //处理下载歌曲
//                handleDondloadNetSong(task, downloadedSize);
//            }
//
//            @Override
//            public void taskThreadError(DownloadTask task, int threadId, String msg) {
//
//            }
//        });
//    }
//
//    /**
//     * 处理下载歌曲
//     *
//     * @param task
//     * @param downloadedSize
//     */
//    private void handleDondloadNetSong(DownloadTask task, int downloadedSize) {
//        int playStatus = AudioPlayerManager.getInstance(mContext).getPlayStatus();
//        if (playStatus == AudioPlayerManager.PLAYINGNET && downloadedSize > 1024 * 500) {
//            //开始播放音频歌曲
//            AudioInfo audioInfo = AudioPlayerManager.getInstance(mContext).getCurSong(task.getTaskId());
//            if (audioInfo != null) {
//                AudioPlayerManager.getInstance(mContext).playDownloadingNetSong(audioInfo);
//            }
//        }
//    }
//
//
//    /**
//     * 添加任务
//     *
//     * @param audioInfo
//     */
//    public synchronized void addDownloadTask(final AudioInfo audioInfo) {
//        //暂停旧的任务
//        pauseTask();
//
//        mCurTaskId = audioInfo.getHash();
//        //异步下载
//        mWorkerHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                addTask(audioInfo);
//            }
//        });
//
//
//    }
//
//    /**
//     * @param audioInfo
//     */
//    private void addTask(AudioInfo audioInfo) {
//
//        APIHttpClient apiHttpClient = HttpUtil.getHttpClient();
//        ConfigInfo configInfo = ConfigInfo.obtain();
//
//        apiHttpClient.getSongInfo(mContext, audioInfo.getHash(), audioInfo, configInfo.isWifi());
//
//        DownloadTask downloadTask = new DownloadTask();
//        downloadTask.setTaskName(audioInfo.getTitle());
//        downloadTask.setTaskExt(audioInfo.getFileExt());
//        downloadTask.setTaskId(audioInfo.getHash());
//
//        String fileName = audioInfo.getTitle();
//        //String taskPath = ResourceUtil.getFilePath(mContext, ResourceConstants.PATH_AUDIO, fileName + "." + downloadTask.getTaskExt());
//        String taskTempPath = ResourceUtil.getFilePath(mContext, ResourceConstants.PATH_CACHE_AUDIO, audioInfo.getHash() + ".temp");
//
////        downloadTask.setTaskPath(taskPath);
//        downloadTask.setTaskTempPath(taskTempPath);
//        downloadTask.setTaskUrl(audioInfo.getDownloadUrl());
//        downloadTask.setThreadNum(mThreadNum);
//        downloadTask.setCreateTime(new Date());
//
//        mDownloadTaskManager.addDownloadTask(downloadTask);
//    }
//
//    /**
//     * 暂停任务
//     *
//     * @param
//     */
//    public synchronized void pauseTask() {
//        //暂停旧的任务
//        if (!mCurTaskId.equals("-1")) {
//            mDownloadTaskManager.pauseDownloadTask(mCurTaskId);
//        }
//    }
//
//
//    /**
//     * 释放
//     */
//    public void release() {
//
//        mDownloadTaskManager.release();
//
//        //移除队列任务
//        if (mWorkerHandler != null) {
//            mWorkerHandler.removeCallbacksAndMessages(null);
//        }
//
//        //关闭线程
//        if (mHandlerThread != null)
//            mHandlerThread.quit();
//    }
}
