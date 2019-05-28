package com.freak.neteasecloudmusic.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.freak.httphelper.BasePresenter;
import com.freak.httphelper.RxBaseView;
import com.freak.httphelper.RxBus;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.app.App;
import com.freak.neteasecloudmusic.commom.constants.Constants;
import com.freak.neteasecloudmusic.event.UserEvent;
import com.freak.neteasecloudmusic.modules.controls.QuickControlsFragment;
import com.freak.neteasecloudmusic.net.status.NetStateChangeObserver;
import com.freak.neteasecloudmusic.net.status.NetStateChangeReceiver;
import com.freak.neteasecloudmusic.net.status.NetworkType;
import com.freak.neteasecloudmusic.utils.SharedPreferencesUtils;
import com.freak.neteasecloudmusic.utils.ToastUtil;


/**
 * @author freak
 * @date 2019/2/29
 * MVP activity基类
 */

public abstract class BaseAbstractMvpActivity<T extends BasePresenter> extends AppCompatActivity implements RxBaseView, NetStateChangeObserver {
    protected T mPresenter;
    protected Activity mActivity;
    private View netErrorView;
    private QuickControlsFragment mFragment;
    private View mFloatView;
    private FrameLayout mContentContainer;
    private static AppCompatActivity sAppCompatActivity;

    /**
     * 绑定布局
     *
     * @return
     */
    protected abstract int getLayout();

    /**
     * 初始化事件和数据
     */
    protected abstract void initEventAndData();

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

    public static AppCompatActivity getBaseActivity(){
        return sAppCompatActivity;
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /**
         * 创建presenter对象
         */
        mPresenter = createPresenter();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(getLayout());
        super.onCreate(savedInstanceState);
        mActivity = this;
        sAppCompatActivity=this;
        //活动控制器
        App.getInstance().addActivity(this);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initView();
        initEventAndData();
        //TODO  每一个页面包含id为bottom_container的布局
        //注册播放广播
//        IntentFilter intentFilter = new IntentFilter();
        //展示底部播放fragment
        showQuickFragment();
    }

    /**
     * 展示底部播放fragment
     * 全部继承BaseAbstractMvpActivity的activity都展示
     */
    public void showQuickFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (mFragment == null) {
            mFragment = QuickControlsFragment.getInstance();
            transaction.add(R.id.bottom_container, mFragment).commitNowAllowingStateLoss();
        } else {
            transaction.show(mFragment).commitNowAllowingStateLoss();
        }
//        mFloatView = LayoutInflater.from(getBaseContext()).inflate(R.layout.fragment_quick_controls,null);
//        ViewGroup mDecorView = (ViewGroup)getWindow().getDecorView();
//        mContentContainer = (FrameLayout)((ViewGroup)mDecorView.getChildAt(0)).getChildAt(1);
//        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
////        musicProcessBar = (ProgressBar)mFloatView.findViewById(R.id.float_Music_ProgressBar);
////        musicImageView = (ImageView)mFloatView.findViewById(R.id.float_MusicImage);
////        musicNameTextView = (TextView)mFloatView.findViewById(R.id.float_Music_Name);
////        musicArtistTextView = (TextView)mFloatView.findViewById(R.id.float_Music_Artist);
////        musicPlayBtn = (ImageView)mFloatView.findViewById(R.id.float_Play_Btn);
////        musicNextBtn = (ImageView)mFloatView.findViewById(R.id.float_Next_Music);
////        musicPlayListBtn = (ImageView)mFloatView.findViewById(R.id.float_Play_List);
////        mFloatView.findViewById(R.id.float_Music_Container).setOnClickListener(this);
////        musicPlayBtn.setOnClickListener(this);
////        musicPlayListBtn.setOnClickListener(this);
////        musicNextBtn.setOnClickListener(this);
//        //获取当前正在播放的音乐
//        layoutParams.gravity = Gravity.BOTTOM;//设置对齐位置
//        mContentContainer.addView(mFloatView,layoutParams);
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
        App.getInstance().removeActivity(this);
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
    }

    /**
     * 是否需要注册网络变化的Observer,如果不需要监听网络变化,则返回false;否则返回true
     */
    protected boolean needRegisterNetworkChangeObserver() {
        return true;
    }

    /**
     * 网络断开时执行的操作
     */
    @Override
    public void onNetDisconnected() {
        showDisConnectedView();
    }

    /**
     * 网络重连时执行的操作
     *
     * @param networkType
     */
    @Override
    public void onNetConnected(NetworkType networkType) {
        ToastUtil.showLong(mActivity, "当前连接的是" + networkType.toString() + "网络");
        hideDisConnectedView();
    }

    /**
     * 显示无网络状态
     */
    public void showDisConnectedView() {
        ToastUtil.shortShow("网络连接错误，请检测您的网络设置");
//        netErrorView = findViewById(R.id.rl_net_error);
//        netErrorView.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏无网络状态
     */
    public void hideDisConnectedView() {
//        netErrorView = findViewById(R.id.rl_net_error);
//        netErrorView.setVisibility(View.GONE);
    }


    /**
     * 打开一个Activity 默认 不关闭当前activity
     *
     * @param className
     */
    public void gotoActivity(Class<?> className) {
        gotoActivity(className, false, null);
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

    /**
     * 未登录状态提示
     *
     * @param text 未登陆提示语
     */
    public void toastShow(String text) {
        if (text.equals(Constants.UO_LOGIN)) {
            SharedPreferencesUtils.put(getApplicationContext(), Constants.ACCESS_TOKEN, false);
            RxBus.getDefault().post(new UserEvent(1, Constants.RENOVATE));
        }
        ToastUtil.shortShow(text);
    }
}
