package com.lxhf.frame.manage;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import com.lxhf.frame.bean.Tab;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目中XML文件数据解析（）
 * Created by Jamais on 16/12/28.
 */

public class XmlManage {

    /**
     * 导航栏标签
     *
     * @author Jamais
     * @created at 17/8/15 下午4:14
     */
    public static List<Tab> getTabFromXml(Context mContext, int xmlID) throws Exception {
        List<Tab> tabs = new ArrayList<>();
        Resources res = mContext.getResources();
        XmlResourceParser xmlParser = res.getXml(xmlID);
        int eventType = xmlParser.getEventType();
        // 判断是否到了文件的结尾
        while (eventType != XmlResourceParser.END_DOCUMENT) {
            //文件的内容的起始标签开始，注意这里的起始标签是tab.xml文件里面<tabs>标签下面的第一个标签
            if (eventType == XmlResourceParser.START_TAG) {
                String tagname = xmlParser.getName();
                if (tagname.endsWith("tab")) {
                    Tab tab = new Tab();
                    tab.setName(xmlParser.getAttributeValue(null, "name"));
                    tabs.add(tab);
                }
            } else if (eventType == XmlResourceParser.END_TAG) {
            } else if (eventType == XmlResourceParser.TEXT) {
            }
            eventType = xmlParser.next();
        }
        xmlParser.close();
        return tabs;
    }
}
