package com.lxhf.frame.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Jamais on 17/6/15.
 * E-mail : liutl@hfvast.com
 */
public class AppUtils {

    /**
     * 返回当前程序版本名
     *
     * @author Jamais
     * @created at 17/6/15 上午10:05
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
//            int versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            L.e("AppUtils.getAppVersionName.ERR", e + "");
        }
        return versionName;
    }

    /**
     * 返回当前程序版本号
     *
     * @author Jamais
     * @created at 17/6/15 上午10:07
     */
    public static int getAppVersionCode(Context context) {
        int versioncode = 0;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versioncode = pi.versionCode;
        } catch (Exception e) {
            versioncode = 0;
            L.e("AppUtils.getAppVersionName.ERR", e + "");
        }
        return versioncode;
    }
}
