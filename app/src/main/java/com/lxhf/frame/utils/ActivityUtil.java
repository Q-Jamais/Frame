package com.lxhf.frame.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 * 用于界面跳转(包括携带数据)
 *
 * @author Jamais
 * @time 17/2/20 下午4:53
 */

public class ActivityUtil {

    /**
     * 跳转到下一个Activity
     *
     * @param context
     * @param cls
     * @author Jamais
     * @time 17/2/20 下午4:53
     */
    public static void Go(Context context, Class<?> cls) {

        Intent i1 = new Intent();
        i1.setClass(context, cls);
        context.startActivity(i1);
    }

    /**
     * 跳转到下一个Activity，携带参数
     *
     * @param context
     * @param cls
     * @param bundle  用于上一个activity向下一个activity传参
     * @author Jamais
     * @time 17/2/20 下午4:53
     */
    public static void Go(Context context, Class<?> cls, Bundle bundle) {

        Intent i1 = new Intent();
        if (null != bundle) {
            i1.putExtras(bundle);
        }
        i1.setClass(context, cls);
        context.startActivity(i1);
    }

    /**
     * 跳转到下一个Activity，携带参数（可串化对象）
     *
     * @param context
     * @param cls
     * @param map     Intent putExtra
     * @author Jamais
     * @time 17/2/20 下午4:53
     */
    public static void Go(Context context, Class<?> cls,
                          HashMap<String, Serializable> map) {

        Intent i = new Intent();
        if (null != map) {
            Set<String> keys = map.keySet();
            for (String key : keys) {
                i.putExtra(key, map.get(key));
            }
        }
        i.setClass(context, cls);
        context.startActivity(i);
    }
}
