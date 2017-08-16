package com.lxhf.frame.app.common;

import android.Manifest;

/**
 * Created by Jamais on 17/8/9.
 * E-mail : liutl@hfvast.com
 */
public class PermissionInfos {
    //  手机（信息）
    private static final String PHONE_PERMISSION = Manifest.permission_group.PHONE;
    //  读写存储
    private static final String READ_AND_WRITE_EXTERNAL = Manifest.permission_group.STORAGE;
    //  位置信息
    private static final String LOCATION_INFO = Manifest.permission_group.LOCATION;

    public static String[] getPermissionInfos() {
        String[] infos = new String[]{PHONE_PERMISSION, READ_AND_WRITE_EXTERNAL, LOCATION_INFO};
        return infos;
    }
}
