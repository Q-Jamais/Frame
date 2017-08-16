package com.lxhf.frame.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.lxhf.frame.utils.NetUtils;


/**
 * Created by Jamais on 17/7/27.
 * E-mail : liutl@hfvast.com
 */
public class NetWorkBroadcastReceiver extends BroadcastReceiver {

    private NetWorkChanageListen netWorkChangeListen;
    private ConnectivityManager connectivityManager;
    private NetworkInfo netInfo;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isAvailable()) {
//                String name = netInfo.getTypeName();
                if (netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    if (NetUtils.isWifiOnline(context)){
                        netWorkChangeListen.netWorkChange(NetWorkChangeType.TYPE_WIFI_Y);
                    }else {
                        netWorkChangeListen.netWorkChange(NetWorkChangeType.TYPE_WIFI_N);
                    }
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            netWorkChangeListen.netWorkChange(NetWorkChangeType.TYPE_WIFI);
//                            if (NetUtils.isNetworkOnline()) {
//                                netWorkChangeListen.netWorkChange(NetWorkChangeType.TYPE_WIFI_Y);
//                            } else {
//                                netWorkChangeListen.netWorkChange(NetWorkChangeType.TYPE_WIFI_N);
//                            }
//                        }
//                    }).start();
//                    netWorkChangeListen.netWorkChange(NetWorkChangeType.TYPE_WIFI);
                } else if (netInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    //手机网络
                    netWorkChangeListen.netWorkChange(NetWorkChangeType.TYPE_MOBILE);
                }
            } else {
                netWorkChangeListen.netWorkChange(NetWorkChangeType.TYPE_NET_BREAK);
            }
        }
    }

    /**
     * 网络改变监听
     *
     * @author Jamais
     * @created at 17/7/27 下午2:35
     */
    public interface NetWorkChanageListen {
        void netWorkChange(String type);
    }

    public void setNetWorkChangeListen(NetWorkChanageListen netWorkChangeListen) {
        this.netWorkChangeListen = netWorkChangeListen;
    }

    /**
     * 网络改变类型
     *
     * @author Jamais
     * @created at 17/7/27 下午2:57
     */
    public class NetWorkChangeType {
        //  WIFI网络
        public static final String TYPE_WIFI = "wifi";
        //  WIFI网络(不可访问网络)
        public static final String TYPE_WIFI_N = "wifi_n";
        //  wifi网络（可访问网络）
        public static final String TYPE_WIFI_Y = "wifi_y";
        //  手机网络
        public static final String TYPE_MOBILE = "mobile";
        //  网络断开
        public static final String TYPE_NET_BREAK = "netBreak";
    }
}
