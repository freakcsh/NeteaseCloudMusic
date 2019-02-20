package com.freak.commonappframework.net.status;

/**
 * 网络状态变化观察者
 *
 * @author freak
 * @date 2019/2/19
 */
public interface NetStateChangeObserver {
    /**
     * 网络断开
     */
    void onNetDisconnected();

    /**
     * 网络连接
     *
     * @param networkType 网络类型
     */
    void onNetConnected(NetworkType networkType);
}
