package com.freak.neteasecloudmusic.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.freak.neteasecloudmusic.app.App;
import com.freak.neteasecloudmusic.event.LoginOutEvent;
import com.freak.neteasecloudmusic.net.status.NetStateChangeObserver;
import com.freak.neteasecloudmusic.net.status.NetStateChangeReceiver;
import com.freak.neteasecloudmusic.net.status.NetworkType;
import com.freak.neteasecloudmusic.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BaseActivity extends AppCompatActivity implements NetStateChangeObserver {
    private AppCompatActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //活动控制器
        mActivity = this;
        App.getInstance().addActivity(this, this.getClass());
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (needRegisterNetworkChangeObserver()) {
            NetStateChangeReceiver.registerObserver(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        if (needRegisterNetworkChangeObserver()) {
            NetStateChangeReceiver.unregisterObserver(this);
        }
        EventBus.getDefault().unregister(this);
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
        ToastUtil.showLong(this, "当前连接的是" + networkType.toString() + "网络");
        onNetConnectedReLoad();
        hideDisConnectedView();
    }

    protected void onNetConnectedReLoad() {

    }

    /**
     * 显示无网络状态
     */
    public void showDisConnectedView() {
        ToastUtil.showLong(this, "网络连接错误，请检测您的网络设置");
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public synchronized void doLoginOut(LoginOutEvent loginOutEvent) {
        if (loginOutEvent != null) {
        }

    }

}
