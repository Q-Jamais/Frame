package com.lxhf.frame.manage;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.lxhf.frame.R;
import com.lxhf.frame.utils.L;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限操作工具类
 *
 * @author Jamais
 * @created at 17/8/9 上午10:46
 */
public class PermissionManager {

    private static final String TAG = PermissionManager.class.getSimpleName();
    //  请求码（与权限一一对应）
    public static final int CODE_RECORD_AUDIO = 0;
    public static final int CODE_GET_ACCOUNTS = 1;
    public static final int CODE_READ_PHONE_STATE = 2;
    public static final int CODE_CALL_PHONE = 3;
    public static final int CODE_CAMERA = 4;
    public static final int CODE_ACCESS_FINE_LOCATION = 5;
    public static final int CODE_ACCESS_COARSE_LOCATION = 6;
    public static final int CODE_READ_EXTERNAL_STORAGE = 7;
    public static final int CODE_WRITE_EXTERNAL_STORAGE = 8;
    public static final int CODE_MULTI_PERMISSION = 100;

    //  权限
    public static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    public static final String PERMISSION_GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS;
    public static final String PERMISSION_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String PERMISSION_CALL_PHONE = Manifest.permission.CALL_PHONE;
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    public static final String PERMISSION_ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String PERMISSION_ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final String PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    //  需要申请的权限数组
    private static final String[] requestPermissions = {
            PERMISSION_RECORD_AUDIO,
            PERMISSION_GET_ACCOUNTS,
            PERMISSION_READ_PHONE_STATE,
            PERMISSION_CALL_PHONE,
            PERMISSION_CAMERA,
            PERMISSION_ACCESS_FINE_LOCATION,
            PERMISSION_ACCESS_COARSE_LOCATION,
            PERMISSION_READ_EXTERNAL_STORAGE,
            PERMISSION_WRITE_EXTERNAL_STORAGE
    };

    public interface PermissionGrant {
        void onPermissionGranted(int requestCode);
    }

    /**
     * 请求权限
     *
     * @param activity
     * @param permissionGrant
     * @param requestCode
     * @author Jamais
     * @created at 17/8/9 上午10:38
     */
    public static void requestPermission(final Activity activity, final int requestCode, PermissionGrant permissionGrant) {
        if (activity == null) {
            return;
        }

        if (requestCode < 0 || requestCode >= requestPermissions.length) {
            L.i(TAG, "非法的请求码（不存在）");
            return;
        }

        final String requestPermission = requestPermissions[requestCode];

        //如果是6.0以下的手机，ActivityCompat.checkSelfPermission()会始终等于PERMISSION_GRANTED，
        // 但是，如果用户关闭了你申请的权限，ActivityCompat.checkSelfPermission(),会导致程序崩溃(java.lang.RuntimeException: Unknown exception code: 1 msg null)，
        // 你可以使用try{}catch(){},处理异常，也可以判断系统版本，低于23就不申请权限，直接做你想做的。permissionGrant.onPermissionGranted(requestCode);
//        if (Build.VERSION.SDK_INT < 23) {
//            permissionGrant.onPermissionGranted(requestCode);
//            return;
//        }

        int checkSelfPermission;
        try {
            checkSelfPermission = ActivityCompat.checkSelfPermission(activity, requestPermission);
        } catch (RuntimeException e) {
            L.e(TAG, "RuntimeException:" + e.getMessage());
            return;
        }

        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
            //  不具备此权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, requestPermission)) {
                //  是否展示请求理由（本应用为何请求此权限）
                shouldShowRationale(activity, requestCode, requestPermission);
            } else {
                //  不需要提供请求理由
                ActivityCompat.requestPermissions(activity, new String[]{requestPermission}, requestCode);
            }

        } else {
            if (permissionGrant != null) {
                //  此权限已打开
                permissionGrant.onPermissionGranted(requestCode);
            } else {
                return;
            }
        }
    }

    /**
     * @author Jamais
     * @created at 17/8/9 上午11:02
     */
    private static void requestMultiResult(Activity activity, String[] permissions, int[] grantResults, PermissionGrant permissionGrant) {

        if (activity == null) {
            return;
        }
        Map<String, Integer> perms = new HashMap<>();

        ArrayList<String> notGranted = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            perms.put(permissions[i], grantResults[i]);
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                notGranted.add(permissions[i]);
            }
        }

        if (notGranted.size() == 0) {
            //  所有权限都申请成功
            permissionGrant.onPermissionGranted(CODE_MULTI_PERMISSION);
        } else {
            //  需要打开设置
            openSettingActivity(activity, "those permission need granted!");
        }

    }


    /**
     * 一次性申请多个权限
     *
     * @author Jamais
     * @created at 17/8/9 上午11:09
     */
    public static void requestMultiPermissions(final Activity activity, PermissionGrant grant) {

        final List<String> permissionsList = getNoGrantedPermission(activity, false);
        final List<String> shouldRationalePermissionsList = getNoGrantedPermission(activity, true);

        //TODO checkSelfPermission
        if (permissionsList == null || shouldRationalePermissionsList == null) {
            return;
        }
        if (permissionsList.size() > 0) {
            ActivityCompat.requestPermissions(activity, permissionsList.toArray(new String[permissionsList.size()]),
                    CODE_MULTI_PERMISSION);
        } else if (shouldRationalePermissionsList.size() > 0) {
            showMessageOKCancel(activity, "should open those permission",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity, shouldRationalePermissionsList.toArray(new String[shouldRationalePermissionsList.size()]),
                                    CODE_MULTI_PERMISSION);
                            Log.d(TAG, "showMessageOKCancel requestPermissions");
                        }
                    });
        } else {
            grant.onPermissionGranted(CODE_MULTI_PERMISSION);
        }

    }


    /**
     * 给出请求此权限的理由（弹框）
     *
     * @author Jamais
     * @created at 17/8/9 上午10:58
     */
    private static void shouldShowRationale(final Activity activity, final int requestCode, final String requestPermission) {
        //TODO
        String[] permissionsHint = activity.getResources().getStringArray(R.array.permissions);
        showMessageOKCancel(activity, "Rationale: " + permissionsHint[requestCode], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions(activity,
                        new String[]{requestPermission},
                        requestCode);
            }
        });
    }

    /**
     * 弹框展示提示信息
     *
     * @author Jamais
     * @created at 17/8/9 上午10:58
     */
    private static void showMessageOKCancel(final Activity context, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();

    }

    /**
     * @param activity
     * @param requestCode  Need consistent with requestPermission
     * @param permissions
     * @param grantResults
     */
    public static void requestPermissionsResult(final Activity activity, final int requestCode, @NonNull String[] permissions,
                                                @NonNull int[] grantResults, PermissionGrant permissionGrant) {

        if (activity == null) {
            return;
        }
        if (requestCode == CODE_MULTI_PERMISSION) {
            requestMultiResult(activity, permissions, grantResults, permissionGrant);
            return;
        }

        if (requestCode < 0 || requestCode >= requestPermissions.length) {
            //  无效的请求码
            return;
        }

        if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (permissionGrant!=null){
                //  请求成功
                permissionGrant.onPermissionGranted(requestCode);
            }else {
                return;
            }

        } else {
            //  请求失败
            String[] permissionsHint = activity.getResources().getStringArray(R.array.permissions);
            openSettingActivity(activity, "Result" + permissionsHint[requestCode]);
        }

    }

    /**
     * 打开设置，由用户自己设置权限
     *
     * @author Jamais
     * @created at 17/8/9 上午11:00
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


    /**
     * @param activity
     * @param isShouldRationale true: return no granted and shouldShowRequestPermissionRationale permissions, false:return no granted and !shouldShowRequestPermissionRationale
     * @return
     */

    /**
     * 获取到没有授权的权限
     *
     * @author Jamais
     * @created at 17/8/9 上午11:12
     */
    public static ArrayList<String> getNoGrantedPermission(Activity activity, boolean isShouldRationale) {

        ArrayList<String> permissions = new ArrayList<>();

        for (int i = 0; i < requestPermissions.length; i++) {
            String requestPermission = requestPermissions[i];
            int checkSelfPermission = -1;
            try {
                checkSelfPermission = ActivityCompat.checkSelfPermission(activity, requestPermission);
            } catch (RuntimeException e) {
                Log.e(TAG, "RuntimeException:" + e.getMessage());
                return null;
            }

            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
                //  不具备权限
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, requestPermission)) {
                    //  应该给出请求理由
                    if (isShouldRationale) {
                        permissions.add(requestPermission);
                    }

                } else {
                    if (!isShouldRationale) {
                        permissions.add(requestPermission);
                    }
                }
            }
        }
        return permissions;
    }

}
