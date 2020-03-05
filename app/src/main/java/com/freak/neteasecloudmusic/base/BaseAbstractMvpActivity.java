package com.freak.neteasecloudmusic.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.freak.httphelper.BasePresenter;
import com.freak.httphelper.RxBaseView;
import com.freak.httphelper.log.LogUtil;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.app.App;
import com.freak.neteasecloudmusic.handler.WeakRefHandler;
import com.freak.neteasecloudmusic.modules.controls.QuickControlsFragment;
import com.freak.neteasecloudmusic.net.status.NetStateChangeReceiver;
import com.freak.neteasecloudmusic.player.manager.AudioPlayerManager;
import com.freak.neteasecloudmusic.player.manager.entity.AudioInfo;
import com.freak.neteasecloudmusic.receiver.AudioBroadcastReceiver;
import com.freak.neteasecloudmusic.service.AudioPlayerService;
import com.freak.neteasecloudmusic.view.seekbar.CircleSeekBar;

import butterknife.ButterKnife;

import static com.freak.neteasecloudmusic.app.App.DESIGN_WIDTH;


/**
 * @author freak
 * @date 2019/2/29
 * MVP activity基类
 */

public abstract class BaseAbstractMvpActivity<T extends BasePresenter> extends BaseActivity implements RxBaseView {
    protected T mPresenter;
    protected AppCompatActivity mActivity;
    private QuickControlsFragment mFragment;
    private View mFloatView;
    private FrameLayout mContentContainer;
    /**
     * 音频广播
     */
    private AudioBroadcastReceiver mAudioBroadcastReceiver;
    //创建异步HandlerThread
    private HandlerThread mHandlerThread;
    /**
     * 子线程用于执行耗时任务
     */
    public WeakRefHandler mWorkerHandler;
    /**
     * 处理ui任务
     */
    public WeakRefHandler mUIHandler;
    private FrameLayout.LayoutParams mLayoutParams;
    /**
     * 播放列表
     */
    private ImageView mImageViewControllerSongList;
    /**
     * 暂停播放
     */
    private ImageView mImageViewControllerStartOrStop;
    /**
     * 音乐背景
     */
    private ImageView mImageViewControllerBg;
    /**
     * 进度
     */
    private CircleSeekBar mCircleSeekBarControllerRate;
    /**
     * 歌曲作者
     */
    private TextView mTextViewControllerAuthorName;
    /**
     * 歌曲名字
     */
    private TextView mTextViewControllerSongName;

    /**
     * 绑定布局
     *
     * @return
     */
    protected abstract int getLayout();

    /**
     * 初始化事件和数据
     * 网络请求不要放在此方法中，因为断网重连时不会调用此方法，只会调用onCreateLoadData和onResumeLoadData
     */
    protected abstract void initEventAndData();

    /**
     * 在onCreate方法中加载数据
     * 不需要刷新数据则请求数据在此方法中调用
     */
    protected abstract void onCreateLoadData();

    /**
     * 资源释放
     */
    protected abstract void onDestroyRelease();

    /**
     * 在onResume中加载数据
     * 需要返回刷新数据则选择放在这个方法中加载，否则在onCreateLoadData中加载数据
     */
    protected abstract void onResumeLoadData();

    /**
     * 控件初始化
     */
    protected abstract void initView();

    /**
     * 创建Presenter
     *
     * @return
     */
    protected abstract T createPresenter();


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /**
         * 创建presenter对象
         */
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //设置状态栏颜色
//            getWindow().setStatusBarColor(0xff24cf5f);
        }
        App.resetDensity(this, DESIGN_WIDTH);
        setContentView(getLayout());
        super.onCreate(savedInstanceState);
        //绑定初始化ButterKnife
        ButterKnife.bind(this);
        mActivity = this;
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initView();
        initEventAndData();
        onCreateLoadData();


        //展示底部播放fragment
        initHandle();
        showQuickFragment();
        initReceiver();
        AudioPlayerManager.getInstance(mActivity).init();
    }

    public void initHandle() {
        //创建ui handler
        if (mUIHandler == null) {
            mUIHandler = new WeakRefHandler(Looper.getMainLooper(), mActivity, new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    handleUIMessage(msg);
                    return true;
                }
            });
        }

        if (mHandlerThread == null) {
            //创建异步HandlerThread
            mHandlerThread = new HandlerThread("loadActivityData", Process.THREAD_PRIORITY_BACKGROUND);
            //必须先开启线程
            mHandlerThread.start();
        }
        if (mWorkerHandler == null) {
            //子线程Handler
            mWorkerHandler = new WeakRefHandler(mHandlerThread.getLooper(), mActivity, new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    handleWorkerMessage(msg);
                    return true;
                }
            });
        }

    }

    /**
     * 处理UI
     *
     * @param msg
     */
    protected void handleUIMessage(Message msg) {

    }

    /**
     * 处理子线程worker
     *
     * @param msg
     */
    protected void handleWorkerMessage(Message msg) {

    }

    /**
     * 初始化广播
     */
    private void initReceiver() {
        //音频广播
        App.getAudioBroadcastReceiverInstance().setReceiverListener(new AudioBroadcastReceiver.AudioReceiverListener() {
            @Override
            public void onReceive(Context context, final Intent intent, final int code) {
                LogUtil.e("接收广播" + code);
                mUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        handleAudioBroadcastReceiver(intent, code);
                    }
                });
            }

            private void handleAudioBroadcastReceiver(Intent intent, int code) {
                switch (code) {
                    case AudioBroadcastReceiver.ACTION_CODE_NULL:
                        /**
                         * mImageViewControllerStartOrStop
                         * mImageViewControllerBg
                         * mCircleSeekBarControllerRate
                         * mTextViewControllerAuthorName
                         * mTextViewControllerSongName
                         */
                        //空数据
                        mTextViewControllerSongName.setText(R.string.def_songName);
                        mTextViewControllerAuthorName.setText(R.string.def_artist);
                        mImageViewControllerStartOrStop.setSelected(false);
                        mCircleSeekBarControllerRate.setEnabled(false);
                        mCircleSeekBarControllerRate.setMaxProgress(0);
                        mCircleSeekBarControllerRate.setProgress(0);
                        Glide.with(mActivity).load("").error(R.drawable.svg_icon_rt).thumbnail(0.1f);
//                        //重置额外歌词状态
//                        mConfigInfo.setExtraLrcStatus(ConfigInfo.EXTRALRCSTATUS_NOSHOWEXTRALRC);
//
//                        if (mAdapter != null)
//                            mAdapter.reshViewHolder(null);

                        break;
                    //播放初始化
                    case AudioBroadcastReceiver.ACTION_CODE_INIT:
                        Bundle initBundle = intent.getBundleExtra(AudioBroadcastReceiver.ACTION_BUNDLEKEY);
                        AudioInfo initAudioInfo = initBundle.getParcelable(AudioBroadcastReceiver.ACTION_DATA_KEY);
                        if (initAudioInfo != null) {
                            LogUtil.e("播放初始化");
                            mTextViewControllerSongName.setText(initAudioInfo.getSongName());
                            mTextViewControllerAuthorName.setText(initAudioInfo.getSingerName());
                            mImageViewControllerStartOrStop.setSelected(true);
                            mCircleSeekBarControllerRate.setEnabled(true);
                            mCircleSeekBarControllerRate.setMaxProgress((int) initAudioInfo.getDuration());
                            mCircleSeekBarControllerRate.setProgress((int) initAudioInfo.getPlayProgress());
                            Glide.with(mActivity).load(initAudioInfo.getImageUrl()).placeholder(R.drawable.svg_icon_rt).error(R.drawable.svg_icon_rt).thumbnail(0.1f);

                            //加载歌手头像
//                            ImageUtil.loadSingerImage(mContext, mArtistImageView, initAudioInfo.getSingerName(), mConfigInfo.isWifi(), 400, 400, new AsyncHandlerTask(mUIHandler, mWorkerHandler), new ImageUtil.ImageLoadCallBack() {
//                                @Override
//                                public void callback(Bitmap bitmap) {
//                                    //if (bitmap != null) {
//                                    AudioBroadcastReceiver.sendNotifiyImgLoadedReceiver(mContext, initAudioInfo);
//                                    // }
//                                }
//                            });

//                            //加载歌词
//                            String keyWords = initAudioInfo.getTitle();
//                            LyricsManager.getInstance(mContext).loadLyrics(keyWords, keyWords, initAudioInfo.getDuration() + "", initAudioInfo.getHash(), mConfigInfo.isWifi(), new AsyncHandlerTask(mUIHandler, mWorkerHandler), null);
//
//                            if (mAdapter != null) {
//
//                                if (mIsShowPopPlayList) {
//                                    //定位
//                                    int position = AudioPlayerManager.getInstance(mContext).getCurSongIndex(mConfigInfo.getAudioInfos(), mConfigInfo.getPlayHash());
//                                    if (position != -1) {
//                                        ((LinearLayoutManager) mPlayListRListView.getLayoutManager()).scrollToPositionWithOffset(position, 0);
//                                    }
//                                }
//
//                                mAdapter.reshViewHolder(initAudioInfo.getHash());
//                            }
                        } else {
//                            if (mAdapter != null)
//                                mAdapter.reshViewHolder(null);
                        }

                        break;
                    //播放
                    case AudioBroadcastReceiver.ACTION_CODE_PLAY:
                        Bundle bundle = intent.getBundleExtra(AudioBroadcastReceiver.ACTION_BUNDLEKEY);
                        AudioInfo audioInfo = bundle.getParcelable(AudioBroadcastReceiver.ACTION_DATA_KEY);
                        if (audioInfo != null) {
                            LogUtil.e("播放");
                            mTextViewControllerSongName.setText(audioInfo.getSongName());
                            mTextViewControllerAuthorName.setText(audioInfo.getSingerName());
                            mCircleSeekBarControllerRate.setEnabled(true);
                            mCircleSeekBarControllerRate.setMaxProgress((int) audioInfo.getDuration());
                            mCircleSeekBarControllerRate.setProgress((int) audioInfo.getPlayProgress());
                            Glide.with(mActivity).load(audioInfo.getImageUrl()).placeholder(R.drawable.svg_icon_rt).error(R.drawable.svg_icon_rt).thumbnail(0.1f);
                            mImageViewControllerStartOrStop.setSelected(true);
                            mCircleSeekBarControllerRate.setProgress((int) audioInfo.getPlayProgress());
                        }

                        break;
                    //播放中
                    case AudioBroadcastReceiver.ACTION_CODE_PLAYING:
                        Bundle playingBundle = intent.getBundleExtra(AudioBroadcastReceiver.ACTION_BUNDLEKEY);
                        AudioInfo playingAudioInfo = playingBundle.getParcelable(AudioBroadcastReceiver.ACTION_DATA_KEY);
                        if (playingAudioInfo != null) {
                            mCircleSeekBarControllerRate.setProgress((int) playingAudioInfo.getPlayProgress());
                        }

                        break;
                    //停止播放歌曲
                    case AudioBroadcastReceiver.ACTION_CODE_STOP:
//                        //暂停完成
                        mImageViewControllerStartOrStop.setSelected(false);

                        break;
                    //seekto歌曲
                    case AudioBroadcastReceiver.ACTION_CODE_SEEKTO:
                        Bundle seektoBundle = intent.getBundleExtra(AudioBroadcastReceiver.ACTION_BUNDLEKEY);
                        AudioInfo seektoAudioInfo = seektoBundle.getParcelable(AudioBroadcastReceiver.ACTION_DATA_KEY);
                        if (seektoAudioInfo != null) {
                            mCircleSeekBarControllerRate.setProgress(seektoAudioInfo.getPlayProgress());
                        }
                        break;
                    //歌曲完成
                    case AudioBroadcastReceiver.ACTION_CODE_DOWNLOAD_FINISH:
                        //在线歌曲下载完成
                    case AudioBroadcastReceiver.ACTION_CODE_DOWNLOADONEDLINESONG:
//                        if (!mIsShowPopPlayList || mAdapter == null) {
//                            return;
//                        }
                        //网络歌曲下载完成
                        Bundle downloadedBundle = intent.getBundleExtra(AudioBroadcastReceiver.ACTION_BUNDLEKEY);
//                        DownloadTask downloadedTask = downloadedBundle.getParcelable(AudioBroadcastReceiver.ACTION_DATA_KEY);
//                        String downloadedHash = downloadedTask.getTaskId();
//                        if (downloadedTask != null && !TextUtils.isEmpty(downloadedHash)) {
//                            mAdapter.reshViewHolder(downloadedHash);
//                        }

                        break;
                    //更新播放列表歌曲
                    case AudioBroadcastReceiver.ACTION_CODE_UPDATE_PLAYLIST:
//                        if (!mIsShowPopPlayList || mAdapter == null) {
//                            return;
//                        }
//
//                        //设置当前歌曲数据
//                        List<AudioInfo> audioInfoList = mConfigInfo.getAudioInfos();
//                        mPopListSizeTv.setText(audioInfoList.size() + "");
//
//                        mAdapter.notifyDataSetChanged();

                        break;
                    //在线歌曲下载中
                    case AudioBroadcastReceiver.ACTION_CODE_DOWNLOADONLINESONG:
//                        //网络歌曲下载中
//                        Bundle downloadOnlineSongBundle = intent.getBundleExtra(AudioBroadcastReceiver.ACTION_BUNDLEKEY);
//                        DownloadTask downloadingTask = downloadOnlineSongBundle.getParcelable(AudioBroadcastReceiver.ACTION_DATA_KEY);
//                        String hash = mConfigInfo.getPlayHash();
//                        AudioInfo audioInfo = AudioPlayerManager.getInstance(mContext).getCurSong(hash);
//                        if (audioInfo != null && downloadingTask != null && !TextUtils.isEmpty(hash) && hash.equals(downloadingTask.getTaskId())) {
//                            int downloadedSize = DownloadThreadInfoDB.getDownloadedSize(mContext, downloadingTask.getTaskId(), OnLineAudioManager.mThreadNum);
//                            double pre = downloadedSize * 1.0 / audioInfo.getFileSize();
//                            int downloadProgress = (int) (mMusicSeekBar.getMax() * pre);
//                            mMusicSeekBar.setSecondaryProgress(downloadProgress);
//                        }

                        break;
                    //通知栏 桌面歌词隐藏
                    case AudioBroadcastReceiver.ACTION_CODE_NOTIFY_DESLRC_HIDE_ACTION:
//
//                        mDesktoplrcSwitchButton.setChecked(false);
//                        mConfigInfo.setShowDesktopLrc(false).save();
//                        //
//                        AudioBroadcastReceiver.sendReceiver(mContext, AudioBroadcastReceiver.ACTION_CODE_NOTIFY_DESLRC);
//                        //关闭桌面歌词
//                        HPApplication applicationTtemp = (HPApplication) getApplication();
//                        applicationTtemp.stopFloatService();

                        break;
                    //通知栏 桌面歌词显示
                    case AudioBroadcastReceiver.ACTION_CODE_NOTIFY_DESLRC_SHOW_ACTION:

//                        if (!hasShowFloatWindowPermission()) return;
//
//                        mDesktoplrcSwitchButton.setChecked(true);
//                        mConfigInfo.setShowDesktopLrc(true).save();
//                        //
//                        AudioBroadcastReceiver.sendReceiver(mContext, AudioBroadcastReceiver.ACTION_CODE_NOTIFY_DESLRC);
//                        //启动桌面歌词
//                        HPApplication application = (HPApplication) getApplication();
//                        application.startFloatService();

                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 销毁服务
     */
    public void destroyService() {
        AudioPlayerService.stopService(mActivity);
    }

    /**
     * 释放数据
     */
    public void releaseData() {
//        ImageUtil.release();
//        DownloadAudioManager.getInstance(mContext).release();
        AudioPlayerManager.getInstance(mActivity).release();
//        ToastUtil.release();
    }

    /**
     * 销毁广播
     */
    public void destroyReceiver() {
//        if (mFragmentReceiver != null) {
//            mFragmentReceiver.unregisterReceiver(mContext);
//        }
        LogUtil.e("销毁广播");
        if (mAudioBroadcastReceiver != null) {
            mAudioBroadcastReceiver.unregisterReceiver(mActivity);
        }
//
//        if (mAppSystemReceiver != null) {
//            mAppSystemReceiver.unregisterReceiver(mContext);
//        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            if (mPhoneReceiver != null) {
//                mPhoneReceiver.unregisterReceiver(mContext);
//            }
//        } else {
//            if (mPhoneV4Receiver != null) {
//                mPhoneV4Receiver.unregisterReceiver(mContext);
//            }
//        }
    }

    /**
     * 展示底部播放fragment
     * 全部继承BaseAbstractMvpActivity的activity都展示
     */
    public void showQuickFragment() {
        if (mFloatView == null) {
            mFloatView = LayoutInflater.from(getBaseContext()).inflate(R.layout.fragment_quick_controls, null);
            ViewGroup mDecorView = (ViewGroup) getWindow().getDecorView();
            mContentContainer = (FrameLayout) ((ViewGroup) mDecorView.getChildAt(0)).getChildAt(1);
            mLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //获取当前正在播放的音乐
            mLayoutParams.gravity = Gravity.BOTTOM;//设置对齐位置
            mImageViewControllerSongList = mFloatView.findViewById(R.id.image_view_controller_song_list);
            mImageViewControllerStartOrStop = mFloatView.findViewById(R.id.image_view_controller_start_or_stop);
            mImageViewControllerBg = mFloatView.findViewById(R.id.image_view_controller_bg);
            mCircleSeekBarControllerRate = mFloatView.findViewById(R.id.circle_seek_bar_controller_rate);
            mTextViewControllerAuthorName = mFloatView.findViewById(R.id.text_view_controller_author_name);
            mTextViewControllerSongName = mFloatView.findViewById(R.id.text_view_controller_song_name);
            mImageViewControllerSongList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            mImageViewControllerStartOrStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mImageViewControllerStartOrStop.isSelected()){
                        AudioPlayerManager.getInstance(mActivity).pause();
                    }else {
                        AudioPlayerManager.getInstance(mActivity).play((int) mCircleSeekBarControllerRate.getVelocity());
                    }
                }
            });
        }
        mContentContainer.addView(mFloatView, mLayoutParams);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * presenter 解除view订阅
         */
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        onDestroyRelease();
        if (needRegisterNetworkChangeObserver()) {
            NetStateChangeReceiver.unregisterObserver(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (needRegisterNetworkChangeObserver()) {
            NetStateChangeReceiver.registerObserver(this);
        }
        onResumeLoadData();
    }


    /**
     * 网络重连重新加载
     */
    @Override
    protected void onNetConnectedReLoad() {
        super.onNetConnectedReLoad();
        onCreateLoadData();
        onResumeLoadData();
    }


    /**
     * 打开一个Activity 默认 不关闭当前activity
     *
     * @param className
     */
    public void gotoActivity(Class<?> className) {
        gotoActivity(className, false);
    }

    /**
     * 打开一个Activity  关闭当前activity
     *
     * @param className
     */
    public void gotoActivityWithFinish(Class<?> className) {
        gotoActivity(className, true);
    }

    /**
     * 打开一个Activity，并控制是否finish
     *
     * @param className              className
     * @param isCloseCurrentActivity 是否关闭
     */
    public void gotoActivity(Class<?> className, boolean isCloseCurrentActivity) {
        gotoActivity(className, isCloseCurrentActivity, null);
    }

    /**
     * 打开一个activity，并传递数据
     *
     * @param className              className
     * @param isCloseCurrentActivity 是否关闭
     * @param bundle                 bundle数据
     */
    public void gotoActivity(Class<?> className, boolean isCloseCurrentActivity, Bundle bundle) {
        Intent intent = new Intent(this, className);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (isCloseCurrentActivity) {
            finish();
        }
    }

    /**
     * 打开一个带结果返回的activity，并传递数据
     *
     * @param className   className
     * @param bundle      bundle数据
     * @param requestCode 请求码
     */
    public void gotoActivityWithResult(Class<?> className, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, className);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 自定义返回监听
     */
    public void setBackPress() {
        try {
            View backView = findViewById(R.id.tool_bar);
            backView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } catch (Exception e) {

        }
    }

    /**
     * 系统返回键监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();//返回
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
