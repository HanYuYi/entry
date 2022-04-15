package com.HanYuYi.util;

/**
 * String 工具类
 */
public class StringUtils {
    /**
     * 判断字符串是否为空
     * @param data
     * @return
     */
    public static boolean isNullOrEmpty(String data) {
        if (data == null || data == "") {
            return true;
        }
        return false;
    }
}
