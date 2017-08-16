package com.lxhf.frame.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by ww on 2016/3/14.
 */
public class T {
    /**
     * Toast短暂显示在中间
     *
     * @author Jamais
     * @created at 17/6/15 上午10:23
     */
    public static void toastAtCenterS(Context mContext, String content) {
        Toast toast = Toast.makeText(mContext, content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * Toast长时间显示在中间
     *
     * @author Jamais
     * @created at 17/6/15 上午10:24
     */
    public static void toastAtCenterL(Context mContext, String content) {
        Toast toast = Toast.makeText(mContext, content, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * Toast短暂显示在下方
     *
     * @author Jamais
     * @created at 17/6/15 上午10:24
     */
    public static void toastAtBottomS(Context mContext, String content) {
        Toast toast = Toast.makeText(mContext, content, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Toast长时间显示在下方
     *
     * @author Jamais
     * @created at 17/6/15 上午10:24
     */
    public static void toastAtBottomL(Context mContext, String content) {
        Toast toast = Toast.makeText(mContext, content, Toast.LENGTH_LONG);
        toast.show();
    }
}
