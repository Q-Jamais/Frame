package com.lxhf.frame.utils;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import java.util.UUID;

/**
 * Created by Jamais on 17/6/22.
 * E-mail : liutl@hfvast.com
 */
public class DevInfo {

    private static String uniqueId = null;

    /**
     * 设备唯一标示(不可变)
     *
     * @author Jamais
     * @created at 17/7/20 上午10:04
     */

    public static String getDevUUID(Context mContext) {
        synchronized (DevInfo.class) {
            if (uniqueId == null) {
                final TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
                final String tmDevice, tmSerial, tmPhone, androidId;
                tmDevice = "" + tm.getDeviceId();
                tmSerial = "" + tm.getSimSerialNumber();
                androidId = "" + android.provider.Settings.Secure.getString(mContext.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
                UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
                uniqueId = deviceUuid.toString();
            }
        }
        return uniqueId;
    }

    /**
     * 获取手机唯一标示IMEI
     *
     * @author Jamais
     * @created at 17/6/22 上午11:16
     */
    public static String getIMEI(Context mContext) {

        String imei = ((TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        return imei == null ? "" : imei;
    }

    /**
     * 获取运营商sim卡IMSI号
     *
     * @author Jamais
     * @created at 17/6/22 上午11:17
     */
    public static String getIMSI(Context mContext) {
        String imsi = ((TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
        return imsi == null ? "" : imsi;
    }

    /**
     * 获取DisplayMetrics，包括屏幕高宽，密度等
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    /**
     * 获得屏幕宽度 px
     *
     * @param context
     * @return
     */
    public static int getWidth(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获得屏幕高度 px
     *
     * @param context
     * @return
     */
    public static int getHeight(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
}
