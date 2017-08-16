package com.lxhf.frame.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * SharedPreferences
 * Created by ${Quejamais} on 2016/1/25.
 */
public class SharedPreferencesHelp {

    //用于申请权限后回调标识
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1000;

    private static SharedPreferences sharedPreferences;
    private static SharedPreferencesHelp mSharedPreferencesHelp;

    /**
     * SharedPreferences实例化
     *
     * @param fileName 文件名
     * @return SharedPreferences
     * created at 2016/1/25 11:28
     * @author Quejamais
     */

    public static SharedPreferencesHelp getInstance(Context mContext, String fileName) {
        mSharedPreferencesHelp = new SharedPreferencesHelp();
        synchronized (SharedPreferencesHelp.class) {//  确保在多个线程中也是单例化
            if (sharedPreferences == null) {
                sharedPreferences = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
            }
        }
        return mSharedPreferencesHelp;
    }

    /**
     * 获取文件中数据
     *
     * @param key def
     * @return int
     * created at 2016/1/25 11:35
     * @author Quejamais
     */
    public int getInt(String key, int def) {
        try {
            if (sharedPreferences != null)
                def = sharedPreferences.getInt(key, def);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    /**
     * 把数据放到文件中保存
     *
     * @param key val
     * @return void
     * created at 2016/1/25 11:36
     * @author Quejamais
     */
    public void putInt(String key, int val) {
        try {
            if (sharedPreferences != null) {
                SharedPreferences.Editor e = sharedPreferences.edit();
                e.putInt(key, val);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//=================================以下同理==================================================
//==========================================================================================

    public long getLong(String key, long def) {
        try {
            if (sharedPreferences != null)
                def = sharedPreferences.getLong(key, def);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    public void putLong(String key, long val) {
        try {
            if (sharedPreferences != null) {
                SharedPreferences.Editor e = sharedPreferences.edit();
                e.putLong(key, val);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getString(String key) {
        String def = "";
        try {
            if (sharedPreferences != null)
                def = sharedPreferences.getString(key, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    public void putString(String key, String val) {
        try {
            if (sharedPreferences != null) {
                SharedPreferences.Editor e = sharedPreferences.edit();
                e.putString(key, val);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getBoolean(String key, boolean def) {
        try {
            if (sharedPreferences != null)
                def = sharedPreferences.getBoolean(key, def);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    public void putBoolean(String key, boolean val) {
        try {
            if (sharedPreferences != null) {
                SharedPreferences.Editor e = sharedPreferences.edit();
                e.putBoolean(key, val);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(String key) {
        try {
            if (sharedPreferences != null) {
                SharedPreferences.Editor e = sharedPreferences.edit();
                e.remove(key);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除数据
     *
     * @param
     * @return created at 2016/3/24 13:44
     * @author Quejamais
     */
    public void clearData() {
        if (sharedPreferences != null) {
            sharedPreferences.edit().clear().commit();
        }
    }
}
