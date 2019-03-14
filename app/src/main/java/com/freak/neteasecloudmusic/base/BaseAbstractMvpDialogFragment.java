package com.freak.neteasecloudmusic.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.freak.neteasecloudmusic.net.status.NetStateChangeObserver;
import com.freak.neteasecloudmusic.net.status.NetStateChangeReceiver;
import com.freak.neteasecloudmusic.net.status.NetworkType;
import com.freak.neteasecloudmusic.utils.ToastUtil;
import com.freak.httphelper.BasePresenter;


/**
 * MVP模式的Base Dialog fragment
 *
 * @author quchao
 * @date 2017/11/28
 */

public abstract class BaseAbstractMvpDialogFragment<T extends BasePresenter> extends DialogFragment implements BaseView ,NetStateChangeObserver {


    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            //防止连续点击add多个fragment
            manager.beginTransaction().remove(this).commit();
            super.show(manager, tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化数据
     */
    protected abstract void initEventAndData();

    protected T mPresenter;
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private RelativeLayout netErrorView;
    protected View loadingView;
    private boolean hidden = false;
    protected abstract T createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initEventAndData(View view);

    protected abstract void initLazyData();

    /**
     * 显示加载中view，由子类实现
     */
    protected abstract void showLoading();

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        if (needRegisterNetworkChangeObserver()){
            //此处可加入网络连接错误的布局绑定
//            netErrorView = mView.findViewById(R.id.rl_net_error);
        }
        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initEventAndData(view);
        initLazyData();
        showLoading();
        if (needRegisterNetworkChangeObserver()){
            //此处可加入网络连接错误的布局绑定
//            netErrorView = mView.findViewById(R.id.rl_net_error);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
//        this.hidden=hidden;
//        /**
//         * 判断该页面是否需要开启网络变化监听，如需要则启动广播
//         */
//        if (!hidden){
//            if (needRegisterNetworkChangeObserver()) {
//                NetStateChangeReceiver.registerObserver(this);
//            }
//        }

    }

    @Override
    public void onResume() {
        super.onResume();
        /**
         * 判断该页面是否需要开启网络变化监听，如需要则启动广播
         */
        if (needRegisterNetworkChangeObserver()) {
            NetStateChangeReceiver.registerObserver(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        /**
         * 判断该页面是否开启了网络变化监听，已开启则关闭广播
         */
        if (needRegisterNetworkChangeObserver()) {
            NetStateChangeReceiver.unregisterObserver(this);
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
        ToastUtil.showLong(mActivity, "当前连接的是"+networkType.toString()+"网络");
        hideDisConnectedView();
    }

    /**
     * 显示无网络状态
     */
    public void showDisConnectedView() {
        ToastUtil.shortShow("网络连接错误，请检测您的网络设置");
//        netErrorView.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏无网络状态
     */
    public void hideDisConnectedView() {
//        netErrorView.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

}
