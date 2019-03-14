package com.freak.neteasecloudmusic.net.status;


/**
 * 网络类型
 *
 * @author freak
 * @date 2019/2/19
 */
public enum NetworkType {
    /**
     * WiFi
     */
    NETWORK_WIFI("WiFi"),
    /**
     * 4G
     */
    NETWORK_4G("4G"),
    /**
     * 3G
     */
    NETWORK_3G("3G"),
    /**
     * 2G
     */
    NETWORK_2G("2G"),
    /**
     * Unknown
     */
    NETWORK_UNKNOWN("Unknown"),
    /**
     * No network
     */
    NETWORK_NO("No network");

    private String desc;

    NetworkType(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc;
    }
}
