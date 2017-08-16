package com.lxhf.frame.utils;

import java.util.regex.Pattern;

/**
 * 手机号码格式判断
 * Created by ww on 2016/2/21.
 */
public class RegexUtil {

    /**
     * 手机号判断
     *
     * @author Jamais
     * @created at 17/6/15 上午10:20
     */
    public static boolean isMobileNum(String str) {

        Pattern pattern = Pattern.compile("\\b(1)[345678][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]\\b");
        return pattern.matcher(str).matches();

    }

    /**
     * 密码判断
     *
     * @author Jamais
     * @created at 17/6/15 上午10:20
     */
    public static boolean isPasswordNum(String str) {
        Pattern pattern = Pattern.compile("^\\d{6}$");
        return pattern.matcher(str).matches();
    }

}
