package com.lxhf.frame.utils;

import android.util.Log;

/**
 * Log日志输出(通过设置isLog来决定项目中是否打印日志)
 * Created by Jamais on 16/12/26.
 */

public class L {

    /**
     * 注释掉Set方法，由此处直接控制Log是否打印，不允许别处修改
     *
     * @author wz
     * @time 2017/2/22 13:04
     */
    private static boolean isLog = true;

    /**
     * 设置是否打印日志
     *
     * @author Jamais
     * @time 16/12/26 下午3:45
     */
    public static void setIsLog(boolean bool) {
        isLog = bool;
    }

    /**
     * 输出INFO程序日志信息
     *
     * @author Jamais
     * @time 16/12/26 下午3:39
     */
    public static void i(String tag, String des) {
        if (isLog) {
            Log.i(tag, des);
        }
    }

    /**
     * 输出ERROR错误信息
     *
     * @author Jamais
     * @time 16/12/26 下午3:39
     */
    public static void e(String tag, String des) {
        if (isLog) {
            Log.e(tag, des);
        }
    }

    /**
     * 输出DEBUG故障日志信息
     *
     * @author Jamais
     * @time 16/12/26 下午3:39
     */
    public static void d(String tag, String des) {
        if (isLog) {
            Log.d(tag, des);
        }
    }

    /**
     * 输出VERBOSE冗余日志信息
     *
     * @author Jamais
     * @time 16/12/26 下午3:39
     */
    public static void v(String tag, String des) {
        if (isLog) {
            Log.v(tag, des);
        }
    }

    /**
     * 输出WARN警告日志信息
     *
     * @author Jamais
     * @time 16/12/26 下午3:39
     */
    public static void w(String tag, String des) {
        if (isLog) {
            Log.w(tag, des);
        }
    }
}
