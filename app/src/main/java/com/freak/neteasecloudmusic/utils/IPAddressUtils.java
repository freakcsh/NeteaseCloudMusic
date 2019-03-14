package com.freak.neteasecloudmusic.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 *
 * @author hboxs020
 * @date 2017/7/31
 */

public class IPAddressUtils {

    /**
     * 以太网状态下ip
     *
     * @return
     */
    private static String getLocalIpAddress() {
        try {
            String allIP = "";
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    allIP += inetAddress.getHostAddress() + "\n";
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
            return allIP;
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return "获取失败";
    }

    //wifi状态下IP
    private static String getWifiIp(Context context) {

        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        //检查Wifi状态
        if (!wifiManager.isWifiEnabled()) {

            return "wifi未开启";
        }

        WifiInfo wifiInfo = wifiManager.getConnectionInfo();

        //获取32位整型ip地址
        int ipAddress = wifiInfo.getIpAddress();

        System.out.println("获取32位整型ip地址:" + ipAddress);

        //把整型地址转换成“*.*.*.*”地址
        String ip = intToIp(ipAddress);

        System.out.println("转换后ip地址:" + ip);

        return ip;
    }

    private static String intToIp(int ipAddress) {

        return (ipAddress & 0xFF) + "." +
                ((ipAddress >> 8) & 0xFF) + "." +
                ((ipAddress >> 16) & 0xFF) + "." +
                (ipAddress >> 24 & 0xFF);
    }

    public static String getIP(Context context) {

        NetworkInfo localNetworkInfo = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable())) {
            //根据网络类型获取对应IP地址

            if (localNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                //ic_network_off
                return "Wifi连接：" + getWifiIp(context);
            }

            if (localNetworkInfo.getType() == ConnectivityManager.TYPE_ETHERNET) {
                //以太网
                return "以太网连接：" + getLocalIpAddress();
            }

        } else {
            return "当前无网络，请检查设备网络连接";
        }

        return "获取失败";
    }

    /**
     * 检查网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean checkEnable(Context context) {

        NetworkInfo localNetworkInfo = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable())) {

            return true;
        }
        return false;
    }
}
