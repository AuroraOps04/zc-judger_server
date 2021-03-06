package edu.zc.oj.utils;

/**
 * @author <a href="mailTo:5239604@qq.com">coderPlus-tr</a>
 * @date 2021/3/6
 */

public class StringUtils {
    public static String trimEnd(char[] value) {
        int len = value.length;
        int st = 0;
        while ((st < len) && (value[len - 1] <= ' ')) {
            len--;
        }
        return len < value.length ? new String(value).substring(st, len) : new String(value);
    }
}
