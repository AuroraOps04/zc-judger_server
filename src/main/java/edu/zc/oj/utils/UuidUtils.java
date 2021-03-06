package edu.zc.oj.utils;

import java.util.UUID;

/**
 * @author <a href="mailTo:5239604@qq.com">coderPlus-tr</a>
 * @date 2021/3/6
 */

public class UuidUtils {
    public static String uuid(){
        return UUID.randomUUID().toString();
    }
    public static String uuid32(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
