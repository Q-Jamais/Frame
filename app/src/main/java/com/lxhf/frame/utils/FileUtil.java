package com.lxhf.frame.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jamais on 17/2/20.
 */

public class FileUtil {

    private static final String TAG = "FileUtil";


    /**
     * 判断sd卡是否被挂载
     *
     * @author Jamais
     * @time 17/3/2 下午5:10
     */
    public static boolean isSDCardMounted() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取sdcard常用目录
     *
     * @author Jamais
     * @time 17/3/2 下午5:10
     */
    public static String getSDCardBaseDir() {
        if (isSDCardMounted()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }

    /**
     * 获取sdcard公有的目录的路径
     *
     * @author Jamais
     * @time 17/3/2 下午5:11
     */
    public static String getSDCardPublicDir(String type) {
        if (isSDCardMounted()) {
            return Environment.getExternalStoragePublicDirectory(type).toString();
        }
        return null;
    }

    /**
     * 获取sdcard私有cache的目录的路径
     *
     * @author Jamais
     * @time 17/3/2 下午5:11
     */
    public static String getSDCardPrivateCacheDir(Context context) {
        if (isSDCardMounted()) {
            return context.getExternalCacheDir().getAbsolutePath();
        }
        return null;
    }

    /**
     * 获取sdcard私有file目录的路径
     *
     * @author Jamais
     * @time 17/3/2 下午5:11
     */
    public static String getSDCardPrivateFilesDir(Context context, String type) {
        if (isSDCardMounted()) {
            return context.getExternalFilesDir(type).getAbsolutePath();
        }
        return null;
    }

    /**
     * 获取sdcard的完整空间大小 。返回MB
     *
     * @author Jamais
     * @time 17/3/2 下午5:11
     */
    public static long getSDCardSize() {
        if (isSDCardMounted()) {
            StatFs fs = new StatFs(getSDCardBaseDir());
            int count = fs.getBlockCount();
            int size = fs.getBlockSize();//此处过时了但也没有更好的方法更新
            return count * size / 1024 / 1024;
        }
        return 0;
    }

    /**
     * 获取sdcard的剩余空间的大小
     *
     * @author Jamais
     * @time 17/3/2 下午5:11
     */
    public static long getSDCardFreeSize() {
        if (isSDCardMounted()) {
            StatFs fs = new StatFs(getSDCardBaseDir());
            int count = fs.getFreeBlocks();
            int size = fs.getBlockSize();
            return count * size / 1024 / 1024;
        }
        return 0;
    }

    /**
     * 获取sdcard的可用空间的大小
     *
     * @author Jamais
     * @time 17/3/2 下午5:12
     */
    public static long getSDCardAvailableSize() {
        if (isSDCardMounted()) {
            StatFs fs = new StatFs(getSDCardBaseDir());
            int count = fs.getAvailableBlocks();
            int size = fs.getBlockSize();
            return count * size / 1024 / 1024;

        }
        return 0;
    }

    /**
     * 往SD卡的自定义目录下保存文件(xml)
     *
     * @author Jamais
     * @time 17/3/2 下午4:57
     */
    public static boolean saveFileToSDCard(byte[] data,
                                           String fileName) {
        BufferedOutputStream bos = null;
        if (isSDCardMounted()) {
            File file = new File(getSDCardBaseDir() + File.separator + "xml");
            if (!file.exists()) {
                file.mkdirs();// 递归创建自定义目录
            }
            try {
                bos = new BufferedOutputStream(new FileOutputStream(new File(
                        file, fileName)));
                bos.write(data);
                bos.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 往SD卡的自定义目录下保存临时文件(xml)
     *
     * @author Jamais
     * @time 17/3/2 下午4:57
     */
    public static boolean saveCasualFileToSDCard(byte[] data) {
        removeFileFromSDCard("casual.xml");//   移除临时文件 casual.xml
        BufferedOutputStream bos = null;
        if (isSDCardMounted()) {
            File file = new File(getSDCardBaseDir() + File.separator + "xml");
            if (!file.exists()) {
                file.mkdirs();// 递归创建自定义目录
            }
            try {
                bos = new BufferedOutputStream(new FileOutputStream(new File(
                        file, "casual.xml")));//    创建临时文件
                bos.write(data);
                bos.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 删除文件夹
     *
     * @param name 文件夹名称
     * @author Jamais
     * @time 17/3/20 下午3:05
     */
    public static boolean removeXmlFileFromSDCard(String name) {
        File file = new File(getSDCardBaseDir() + File.separator + name);
        if (file.exists() && !file.isFile()) {
            File files[] = file.listFiles();
            for (File fil : files
                    ) {
                fil.delete();
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 从xml文件夹中中删除文件
     *
     * @author Jamais
     * @time 17/3/2 下午5:29
     */
    public static boolean removeFileFromSDCard(String fileName) {
        File file = new File(getSDCardBaseDir() + File.separator + "xml" + File.separator +
                fileName);
        if (file.exists()) {
            try {
                file.delete();
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 移除临时文件
     *
     * @author Jamais
     * @time 17/3/3 上午9:44
     */
    public static boolean removCasualFileFromSDCard() {
        File file = new File(getSDCardBaseDir() + File.separator + "xml" + File.separator +
                "casual.xml");
        if (file.exists()) {
            try {
                file.delete();
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 获取xml文件夹中文件列表
     *
     * @author Jamais
     * @time 17/3/9 下午4:33
     */
    public static List<String> getXMLFileArr() {
        List<String> files = new ArrayList<>();
        File file = new File(getSDCardBaseDir() + File.separator + "xml");
        if (file.isDirectory() && file.exists()) {
            File[] subFile = file.listFiles();
            if (subFile.length > 0) {
                for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
                    // 判断是否为文件夹
                    if (!subFile[iFileLength].isDirectory()) {
                        String filename = subFile[iFileLength].getName();
                        // 判断是否为xml结尾
                        if (filename.trim().toLowerCase().endsWith(".xml")) {
                            files.add(filename);
                        }
                    }
                }
            }
            Log.i(TAG, "getXMLFileArr: " + files);
        }
        return files;
    }

    /**
     * 获取所有的报告列表
     *
     * @author wz
     * @time 2017/3/9 15:41
     */
    public static List<String> getReportFileList() {
        List<String> reportList = getXMLFileArr();
        if (reportList != null || reportList.size() > 0) {
            Iterator iterator = reportList.iterator();
            while (iterator.hasNext()) {
                if (TextUtils.equals((String) iterator.next(), "casual.xml")) {
                    iterator.remove();
                }
            }
        }
        return reportList;
    }

    /**
     * 文件是否存在（文件名.xml）
     *
     * @author Jamais
     * @time 17/3/6 上午10:02
     */
    public static boolean isExistsFile(String fileName) {
        File file = new File(FileUtil.getSDCardBaseDir() + File.separator + "xml" + File
                .separator + fileName);
        if (file.exists()) {
            //存在
            return true;
        } else {
            return false;
        }
    }

    /**
     * 文件是否存在
     *
     * @param fileName 文件名+后缀（文件名.xml）
     * @author Jamais
     * @time 17/3/6 上午10:02
     */
    public static boolean isExistsCasual(String fileName) {
        return isExistsFile(fileName);
    }

    /**
     * 创建文件夹
     *
     * @param dirPath 目录路径
     * @author: 张迎迎
     */
    public static void createFolder(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) { // 目录不存在 则创建
            boolean folderCreated = dir.mkdirs(); // mkdir创建一层，mkdirs创建多层
        }
    }
}
