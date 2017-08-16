package com.lxhf.frame.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

/***
 * 网络相关工具类
 */
public class NetUtils {

    /**
     * 是否有网络连接
     *
     * @param context
     * @author Jamais
     * @time 17/2/22 下午5:14
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断WIFI网络是否可用
     *
     * @param context
     * @author Jamais
     * @time 17/2/22 下午5:15
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo
                    (ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断已经连接wifi的情况下，是否可以访问网络
     *
     * @author wz
     * @time 2017/3/8 12:37
     */
    public static boolean isWifiOnline(Context context) {
        int state = getConnectedType(context);
        if (state == 1 && isNetworkOnline()) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否可以访问外网
     * 这是个耗时的方法
     *
     * @author wz
     * @time 2017/3/8 12:34
     */
    public static boolean isNetworkOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("ping -c 1 www.baidu.com");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 判断MOBILE网络是否可用
     *
     * @param context
     * @author Jamais
     * @time 17/2/22 下午5:15
     */
    public boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 获取当前网络连接的类型信息 1 wifi 2 移动网络 -1 无网络
     *
     * @param context
     * @author Jamais
     * @time 17/2/22 下午5:15
     */
    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI ? 1
                        : 2;
            }
        }
        return -1;
    }


}
