package com.lxhf.frame.manage;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例化容器
 * Created by Jamais on 17/5/31.
 * E-mail : liutl@hfvast.com
 */

public class SingletonManager {

    private SingletonManager() {
    }

    private static Map<String, Object> instanceMap = new HashMap<>();

    /**
     * 注册单例化
     * @author Jamais
     * @param instance 单例化对象
     * @param key 键值
     * @created at 17/5/31 上午11:11
     */
    public static void registerInstance(String key, Object instance) {
        if (!instanceMap.containsKey(key)) {
            instanceMap.put(key, instance);
        }
    }

    /**
     * 根据键值获取单例化
     * @author Jamais
     * @created at 17/5/31 上午11:12
     */
    public static Object getInstance(String key) {
        return instanceMap.get(key);
    }
}
