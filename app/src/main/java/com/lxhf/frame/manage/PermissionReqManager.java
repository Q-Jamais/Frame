package com.lxhf.frame.manage;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * 权限申请
 * Created by Jamais on 17/6/26.
 * E-mail : liutl@hfvast.com
 */
public class PermissionReqManager {
    private static PermissionReqManager permissionReqManager = null;

    public static PermissionReqManager getIntance() {
        synchronized (PermissionReqManager.class) {
            if (permissionReqManager == null) {
                permissionReqManager = new PermissionReqManager();
            }
        }
        return permissionReqManager;
    }

    /**
     * 检查是否具备此权限
     *
     * @param mContext
     * @param permissions 权限
     * @author Jamais
     * @created at 17/6/26 上午10:23
     */
    public void checkPermission(Activity mContext, String[] permissions, int requestCode) {
        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // 检查该权限是否已经获取
            int i = ContextCompat.checkSelfPermission(mContext, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                requestPermission(mContext, permissions, requestCode);
            }
        }
    }

    /**
     * 当权限不具备时，就弹框申请权限
     *
     * @param mContext
     * @param permission
     * @param requestCode
     * @author Jamais
     * @created at 17/6/26 上午10:26
     */
    private void requestPermission(Activity mContext, String[] permission, int requestCode) {
        ActivityCompat.requestPermissions(mContext, permission, requestCode);
    }

    /**
     * 打开设置界面
     *
     * @author Jamais
     * @created at 17/8/9 上午10:29
     */
    private static void openSettingActivity(final Activity activity, String message) {

        showMessageOKCancel(activity, message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                intent.setData(uri);
                activity.startActivity(intent);
            }
        });
    }

    private static void showMessageOKCancel(final Activity context, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}
