package com.freak.neteasecloudmusic.modules.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.freak.httphelper.log.LogUtil;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpActivity;
import com.freak.neteasecloudmusic.commom.constants.Constants;
import com.freak.neteasecloudmusic.glide.GlideApp;
import com.freak.neteasecloudmusic.handler.WeakRefHandler;
import com.freak.neteasecloudmusic.modules.base.adapter.MenuItemAdapter;
import com.freak.neteasecloudmusic.modules.base.entity.LoginStatusEntity;
import com.freak.neteasecloudmusic.modules.disco.base.DiscoFragment;
import com.freak.neteasecloudmusic.modules.homepage.base.entity.MenuEntity;
import com.freak.neteasecloudmusic.modules.login.LoginActivity;
import com.freak.neteasecloudmusic.modules.music.MusicFragment;
import com.freak.neteasecloudmusic.modules.video.base.VideoFragment;
import com.freak.neteasecloudmusic.player.manager.AudioPlayerManager;
import com.freak.neteasecloudmusic.player.manager.entity.AudioInfo;
import com.freak.neteasecloudmusic.receiver.AudioBroadcastReceiver;
import com.freak.neteasecloudmusic.service.AudioPlayerService;
import com.freak.neteasecloudmusic.utils.SPUtils;
import com.freak.neteasecloudmusic.utils.StringUtils;
import com.freak.neteasecloudmusic.utils.ToastUtil;
import com.freak.neteasecloudmusic.view.custom.viewpager.CustomViewPager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * @author freak
 * @date 2019/02/19
 */
@SuppressWarnings("ALL")
public class MainActivity extends BaseAbstractMvpActivity<MainPresenter> implements MainContract.View, View.OnClickListener {
    private ImageView mImgDisco, mImgMusic, mImgVideo, mImgSearch;
    private List<ImageView> tabs;
    private DrawerLayout drawerLayout;
    private RecyclerView mRecycleViewMenu;
    private long time = 0;
    private List<MenuEntity> mMenuEntityList;
    private MenuItemAdapter mMenuItemAdapter;
    private ImageView mImgMenu;
    private View mHeadVew;
    private TextView text_view_menu_login;
    private ImageView img_bg_login;
    private TextView text_view_username;
    private String mBackgroundUrl;
    private ImageView img_bg;
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

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        initReceiver();
        initService();
        AudioPlayerManager.getInstance(this).init();

    }

    /**
     * 初始化广播
     */
    private void initReceiver() {

//        //fragment广播
//        mFragmentReceiver = new FragmentReceiver(mContext);
//        mFragmentReceiver.setReceiverListener(new FragmentReceiver.FragmentReceiverListener() {
//            @Override
//            public void onReceive(Context context, final Intent intent, final int code) {
//                mUIHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        handleFragmentReceiver(intent, code);
//                    }
//                });
//            }
//
//            /**
//             * 处理fragment
//             * @param intent
//             * @param code
//             */
//            private void handleFragmentReceiver(final Intent intent, int code) {
//
//                switch (code) {
//                    case FragmentReceiver.ACTION_CODE_OPEN_RECOMMENDFRAGMENT:
//
//                        //排行
//                        Bundle recommendBundle = intent.getBundleExtra(SongFragment.ARGUMENTS_KEY);
//                        SongFragment recommendSongFragment = SongFragment.newInstance();
//                        recommendSongFragment.setArguments(recommendBundle);
//                        mSlidingMenuOnListener.addAndShowFragment(recommendSongFragment);
//
//
//                        break;
//                    case FragmentReceiver.ACTION_CODE_OPEN_SPECIALFRAGMENT:
//                    case FragmentReceiver.ACTION_CODE_OPEN_LOCALFRAGMENT:
//                    case FragmentReceiver.ACTION_CODE_OPEN_LIKEFRAGMENT:
//                    case FragmentReceiver.ACTION_CODE_OPEN_RECENTFRAGMENT:
//
//                        Bundle bundle = intent.getBundleExtra(SongFragment.ARGUMENTS_KEY);
//                        SongFragment songFragment = SongFragment.newInstance();
//                        songFragment.setArguments(bundle);
//
//                        mSlidingMenuOnListener.addAndShowFragment(songFragment);
//                        break;
//                    case FragmentReceiver.ACTION_CODE_OPEN_DOWNLOADFRAGMENT:
//
//                        DownloadMusicFragment downloadMusicFragment = DownloadMusicFragment.newInstance();
//                        mSlidingMenuOnListener.addAndShowFragment(downloadMusicFragment);
//
//                        break;
//
//                    case FragmentReceiver.ACTION_CODE_CLOSE_FRAGMENT:
//
//                        mSlidingMenuOnListener.hideFragment();
//
//                        break;
//                }
//            }
//        });
//        mFragmentReceiver.registerReceiver(mContext);

        //音频广播
        mAudioBroadcastReceiver = new AudioBroadcastReceiver();
        mAudioBroadcastReceiver.setReceiverListener(new AudioBroadcastReceiver.AudioReceiverListener() {
            @Override
            public void onReceive(Context context, final Intent intent, final int code) {
                LogUtil.e("接收广播"+code);
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

                        //空数据
//                        mSongNameTextView.setText(R.string.def_songName);
//                        mSingerNameTextView.setText(R.string.def_artist);
//                        mPauseImageView.setVisibility(View.INVISIBLE);
//                        mPlayImageView.setVisibility(View.VISIBLE);
//
//                        //
//                        mMusicSeekBar.setEnabled(false);
//                        mMusicSeekBar.setProgress(0);
//                        mMusicSeekBar.setSecondaryProgress(0);
//                        mMusicSeekBar.setMax(0);
//
//                        //
//                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bpz);
//                        mArtistImageView.setImageDrawable(new BitmapDrawable(bitmap));
//                        mArtistImageView.setTag("");
//
//                        //重置额外歌词状态
//                        mConfigInfo.setExtraLrcStatus(ConfigInfo.EXTRALRCSTATUS_NOSHOWEXTRALRC);
//
//                        if (mAdapter != null)
//                            mAdapter.reshViewHolder(null);

                        break;
                    case AudioBroadcastReceiver.ACTION_CODE_INIT:
                        Bundle initBundle = intent.getBundleExtra(AudioBroadcastReceiver.ACTION_BUNDLEKEY);
                        final AudioInfo initAudioInfo = initBundle.getParcelable(AudioBroadcastReceiver.ACTION_DATA_KEY);
                        if (initAudioInfo != null) {
//                            mSongNameTextView.setText(initAudioInfo.getSongName());
//                            mSingerNameTextView.setText(initAudioInfo.getSingerName());
//                            mPauseImageView.setVisibility(View.INVISIBLE);
//                            mPlayImageView.setVisibility(View.VISIBLE);
//
//                            //设置进度条
//                            mMusicSeekBar.setEnabled(true);
//                            mMusicSeekBar.setMax((int) initAudioInfo.getDuration());
//                            mMusicSeekBar.setProgress((int) initAudioInfo.getPlayProgress());
//                            mMusicSeekBar.setSecondaryProgress(0);

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
                    case AudioBroadcastReceiver.ACTION_CODE_PLAY:
//                        if (mPauseImageView.getVisibility() != View.VISIBLE)
//                            mPauseImageView.setVisibility(View.VISIBLE);
//
//                        if (mPlayImageView.getVisibility() != View.INVISIBLE)
//                            mPlayImageView.setVisibility(View.INVISIBLE);

                        break;
                    case AudioBroadcastReceiver.ACTION_CODE_PLAYING:

                        Bundle playingBundle = intent.getBundleExtra(AudioBroadcastReceiver.ACTION_BUNDLEKEY);
                        AudioInfo playingAudioInfo = playingBundle.getParcelable(AudioBroadcastReceiver.ACTION_DATA_KEY);
                        if (playingAudioInfo != null) {
//                            mMusicSeekBar.setProgress((int) playingAudioInfo.getPlayProgress());
                        }

                        break;
                    case AudioBroadcastReceiver.ACTION_CODE_STOP:
//                        //暂停完成
//                        if (mPauseImageView.getVisibility() != View.INVISIBLE)
//                            mPauseImageView.setVisibility(View.INVISIBLE);
//
//                        if (mPlayImageView.getVisibility() != View.VISIBLE)
//                            mPlayImageView.setVisibility(View.VISIBLE);

                        break;

                    case AudioBroadcastReceiver.ACTION_CODE_SEEKTO:
                        Bundle seektoBundle = intent.getBundleExtra(AudioBroadcastReceiver.ACTION_BUNDLEKEY);
                        AudioInfo seektoAudioInfo = seektoBundle.getParcelable(AudioBroadcastReceiver.ACTION_DATA_KEY);
                        if (seektoAudioInfo != null) {
//                            mMusicSeekBar.setProgress(seektoAudioInfo.getPlayProgress());
                        }
                        break;

                    case AudioBroadcastReceiver.ACTION_CODE_DOWNLOAD_FINISH:
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
        mAudioBroadcastReceiver.registerReceiver(this);

//        //系统
//        mAppSystemReceiver = new AppSystemReceiver();
//        mAppSystemReceiver.setReceiverListener(new AppSystemReceiver.AppSystemReceiverListener() {
//            @Override
//            public void onReceive(Context context, final Intent intent, final int code) {
//                mUIHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        handleAppSystemBroadcastReceiver(intent, code);
//                    }
//                });
//            }
//
//            private void handleAppSystemBroadcastReceiver(Intent intent, int code) {
//                switch (code) {
//                    case AppSystemReceiver.ACTION_CODE_TOAST_ERRORMSG:
//                        Bundle toastErrorMSGBundle = intent.getBundleExtra(AppSystemReceiver.ACTION_BUNDLEKEY);
//                        String msg = toastErrorMSGBundle.getString(AppSystemReceiver.ACTION_DATA_KEY);
//                        ToastUtil.showTextToast(mContext, msg);
//
//                        break;
//                    case AppSystemReceiver.ACTION_CODE_TIMER_SETTING:
//                        mUIHandler.removeMessages(MESSAGE_WHAT_TIMERUPDATE);
//                        //设置timer
//                    case AppSystemReceiver.ACTION_CODE_TIMER_UPDATE:
//                        Message tempMsg = Message.obtain();
//                        tempMsg.what = MESSAGE_WHAT_TIMERUPDATE;
//
//                        Bundle timerBundle = intent.getBundleExtra(AppSystemReceiver.ACTION_BUNDLEKEY);
//                        TimerInfo timerInfo = timerBundle.getParcelable(AppSystemReceiver.ACTION_DATA_KEY);
//                        mConfigInfo.setTimerInfo(timerInfo);
//                        if (timerInfo != null) {
//                            tempMsg.obj = timerInfo;
//                            mUIHandler.sendMessageDelayed(tempMsg, 1000);
//                        } else {
//                            mUIHandler.sendMessage(tempMsg);
//                        }
//                        //更新
//                        break;
//                    case AppSystemReceiver.ACTION_CODE_SCREEN_OFF:
//                        //关闭屏幕
//                        if (mConfigInfo.isShowLockScreenLrc()) {
//
//                            Intent lockIntent = new Intent(MainActivity.this,
//                                    LockActivity.class);
//                            lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                            startActivity(lockIntent);
//                            //去掉动画
//                            overridePendingTransition(0, 0);
//                        }
//
//                        break;
//                }
//            }
//        });
//        mAppSystemReceiver.registerReceiver(mContext);
//
//        //线控
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            mPhoneReceiver = new PhoneReceiver(mContext);
//            mPhoneReceiver.registerReceiver(mContext);
//        } else {
//            mPhoneV4Receiver = new PhoneV4Receiver(mContext);
//            mPhoneV4Receiver.registerReceiver(mContext);
//        }
    }

    /**
     * 初始服务
     */
    private void initService() {
        AudioPlayerService.startService(this);
    }

    @Override
    protected void onDestroy() {
        releaseData();
        destroyService();
        destroyReceiver();
        super.onDestroy();
    }

    /**
     * 销毁服务
     */
    private void destroyService() {
        LogUtil.e("销毁服务");
        AudioPlayerService.stopService(this);
    }

    /**
     * 释放数据
     */
    private void releaseData() {
//        ImageUtil.release();
//        DownloadAudioManager.getInstance(mContext).release();
        AudioPlayerManager.getInstance(this).release();
//        ToastUtil.release();
    }

    /**
     * 销毁广播
     */
    private void destroyReceiver() {
//        if (mFragmentReceiver != null) {
//            mFragmentReceiver.unregisterReceiver(mContext);
//        }
        LogUtil.e("销毁广播");
        if (mAudioBroadcastReceiver != null) {
            mAudioBroadcastReceiver.unregisterReceiver(this);
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

    @Override
    protected void initView() {
        mImgDisco = (ImageView) findViewById(R.id.img_disco);
        mImgMusic = (ImageView) findViewById(R.id.img_music);
        mImgVideo = (ImageView) findViewById(R.id.img_video);
        mImgSearch = (ImageView) findViewById(R.id.img_search);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mRecycleViewMenu = (RecyclerView) findViewById(R.id.recycle_view_menu);
        mImgMenu = (ImageView) findViewById(R.id.img_menu);
        mImgMenu.setOnClickListener(this);
        mImgSearch.setOnClickListener(this);
        mBackgroundUrl = (String) SPUtils.get(this, Constants.LOGIN_URL, "");
        setViewPager();
        setUpDrawer();
        //创建ui handler
        mUIHandler = new WeakRefHandler(Looper.getMainLooper(), this, new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                handleUIMessage(msg);
                return true;
            }
        });

        //创建异步HandlerThread
        mHandlerThread = new HandlerThread("loadActivityData", Process.THREAD_PRIORITY_BACKGROUND);
        //必须先开启线程
        mHandlerThread.start();
        //子线程Handler
        mWorkerHandler = new WeakRefHandler(mHandlerThread.getLooper(), this, new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                handleWorkerMessage(msg);
                return true;
            }
        });
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
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


    private void setViewPager() {
        tabs = new ArrayList<>();
        mMenuEntityList = new ArrayList<>();
        tabs.add(mImgMusic);
        tabs.add(mImgDisco);
        tabs.add(mImgVideo);
        mMenuEntityList = getItemList(this);
        final CustomViewPager customViewPager = (CustomViewPager) findViewById(R.id.main_viewpager);
        final MusicFragment musicFragment = new MusicFragment();
        final DiscoFragment discoFragment = new DiscoFragment();
        final VideoFragment videoFragment = new VideoFragment();
        CustomViewPagerAdapter customViewPagerAdapter = new CustomViewPagerAdapter(getSupportFragmentManager());
        customViewPagerAdapter.addFragment(discoFragment);
        customViewPagerAdapter.addFragment(musicFragment);
        customViewPagerAdapter.addFragment(videoFragment);
        customViewPager.setAdapter(customViewPagerAdapter);
        customViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switchTabs(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        customViewPager.setCurrentItem(1);
        switchTabs(1);
        mImgMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customViewPager.setCurrentItem(0);
            }
        });
        mImgDisco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customViewPager.setCurrentItem(1);
            }
        });
        mImgVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customViewPager.setCurrentItem(2);
            }
        });
    }

    private void setUpDrawer() {
        mRecycleViewMenu.setLayoutManager(new LinearLayoutManager(this));
        mMenuItemAdapter = new MenuItemAdapter(R.layout.item_menu, mMenuEntityList);
        mPresenter.loadLoginStatusEntity();
        mMenuItemAdapter.bindToRecyclerView(mRecycleViewMenu);
        mRecycleViewMenu.setAdapter(mMenuItemAdapter);
        mMenuItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                menuItemOnClick(position);
            }
        });
    }

    private void initHeadView(int type, LoginStatusEntity loginStatusEntity) {
        switch (type) {
            case 1:
                mHeadVew = LayoutInflater.from(this).inflate(R.layout.view_menu_view_head_login, null);
                img_bg_login = mHeadVew.findViewById(R.id.img_bg_login);
                img_bg = mHeadVew.findViewById(R.id.img_bg);
                text_view_username = mHeadVew.findViewById(R.id.text_view_username);
                GlideApp.with(this).load(mBackgroundUrl).thumbnail(0.1f).into(img_bg_login);
                GlideApp.with(this).load(loginStatusEntity.getProfile().getAvatarUrl()).thumbnail(0.1f).into(img_bg);
                text_view_username.setText(loginStatusEntity.getProfile().getNickname());
                mMenuItemAdapter.addHeaderView(mHeadVew);
                break;
            case 2:
                mHeadVew = LayoutInflater.from(this).inflate(R.layout.item_menu_head, null);
                text_view_menu_login = mHeadVew.findViewById(R.id.text_view_menu_login);
                text_view_menu_login.setOnClickListener(this);
                mMenuItemAdapter.addHeaderView(mHeadVew);
                break;
            default:
                break;
        }

    }

    public List<MenuEntity> getItemList(Context context) {
        List<MenuEntity> mineItems = new ArrayList<>();
        String json = StringUtils.getJsonForLocation(context, "menu.json");
        if (!TextUtils.isEmpty(json)) {
            try {
                JSONArray array = new JSONArray(json);
                for (int i = 0; i < array.length(); i++) {
                    JSONArray arrayItem = array.getJSONArray(i);
                    for (int k = 0; k < arrayItem.length(); k++) {
                        JSONObject object = arrayItem.getJSONObject(k);
                        MenuEntity mineItem = new MenuEntity();
                        mineItem.setId(object.optInt("id"));
                        mineItem.setTitle(object.optString("title"));
                        mineItem.setIcon(context.getResources().getIdentifier(object.optString("icon"), "mipmap", context.getPackageName()));
                        mineItem.setShowArrows(object.optBoolean("isShowArrows"));
                        mineItem.setShowBigView(object.optBoolean("isShowBigView"));
                        mineItem.setShowSmallView(object.optBoolean("isShowSmallView"));
                        mineItems.add(mineItem);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mineItems;
    }

    public void menuItemOnClick(int position) {
        switch (mMenuEntityList.get(position).getId()) {
            case 10:
                ToastUtil.shortShow(mMenuEntityList.get(position).getTitle());
                drawerLayout.closeDrawers();
                break;
            case 11:
                ToastUtil.shortShow(mMenuEntityList.get(position).getTitle());
                drawerLayout.closeDrawers();
                break;
            case 12:
                ToastUtil.shortShow(mMenuEntityList.get(position).getTitle());
                drawerLayout.closeDrawers();
                break;
            case 13:
                ToastUtil.shortShow(mMenuEntityList.get(position).getTitle());
                drawerLayout.closeDrawers();
                break;
            case 20:
                ToastUtil.shortShow(mMenuEntityList.get(position).getTitle());
                drawerLayout.closeDrawers();
                break;
            case 21:
                ToastUtil.shortShow(mMenuEntityList.get(position).getTitle());
                drawerLayout.closeDrawers();
                break;
            case 22:
                ToastUtil.shortShow(mMenuEntityList.get(position).getTitle());
                drawerLayout.closeDrawers();
                break;
            case 30:
                ToastUtil.shortShow(mMenuEntityList.get(position).getTitle());
                drawerLayout.closeDrawers();
                break;

            default:
                break;
        }
    }

    private void switchTabs(int position) {
        for (int i = 0; i < tabs.size(); i++) {
            if (position == i) {
                tabs.get(i).setSelected(true);
            } else {
                tabs.get(i).setSelected(false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //侧滑菜单
            case R.id.img_menu:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            //搜索
            case R.id.img_search:
                break;
            //登陆
            case R.id.text_view_menu_login:
                LoginActivity.startAction(this);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void showToast(String toast) {

    }

    @Override
    public void getLoginStatusSuccess(LoginStatusEntity loginStatusEntity) {
        if (!TextUtils.isEmpty(loginStatusEntity.getProfile().getNickname())) {
            initHeadView(1, loginStatusEntity);
        } else {
            initHeadView(2, loginStatusEntity);
        }
    }

    @Override
    public void getLoginStatusError() {
        initHeadView(2, null);
    }

    static class CustomViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();

        public CustomViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public void addFragment(Fragment fragment) {
            mFragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

    }
}
