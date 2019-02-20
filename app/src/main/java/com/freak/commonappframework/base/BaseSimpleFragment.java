package com.freak.commonappframework.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freak.commonappframework.commom.constants.Constants;
import com.freak.commonappframework.event.UserEvent;
import com.freak.commonappframework.net.status.NetStateChangeObserver;
import com.freak.commonappframework.net.status.NetStateChangeReceiver;
import com.freak.commonappframework.net.status.NetworkType;
import com.freak.commonappframework.utils.SharedPreferencesUtils;
import com.freak.commonappframework.utils.ToastUtil;
import com.freak.httphelper.RxBus;

import me.yokeyword.fragmentation.SupportFragment;


/**
 * 无MVP的Fragment基类
 *
 * @author freak
 * @date 2019/2/19
 */

public abstract class BaseSimpleFragment extends SupportFragment implements NetStateChangeObserver {

    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private View netErrorView;

    protected abstract int getLayoutId();

    protected abstract void initEventAndData(View view);

    protected abstract void initLazyData();

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);

        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initEventAndData(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initLazyData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        /**
         * 判断该页面是否开启了网络变化监听，已开启则关闭广播
         */
        if (needRegisterNetworkChangeObserver()) {
            NetStateChangeReceiver.unregisterObserver(this);
        }
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
//        netErrorView = mView.findViewById(R.id.rl_net_error);
//        netErrorView.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏无网络状态
     */
    public void hideDisConnectedView() {
//        netErrorView = mView.findViewById(R.id.rl_net_error);
//        netErrorView.setVisibility(View.GONE);
    }

    /**
     * 用户未登陆提示
     *
     * @param text
     */
    public void toastShow(String text) {
        if (text.equals(Constants.UO_LOGIN)) {
            SharedPreferencesUtils.put(mContext, Constants.TOKEN_ABLE, false);
            RxBus.getDefault().post(new UserEvent(1, Constants.RENOVATE));
        }
        ToastUtil.shortShow(text);
    }


}