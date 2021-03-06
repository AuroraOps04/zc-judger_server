package edu.zc.oj.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author <a href="mailTo:5239604@qq.com">coderPlus-tr</a>
 * @date 2021/3/6
 */

public class CommandUtils {
    /**
     * 默认输出字符集，设置成其它字符集中文会乱码
     */
    private static final String DEFAULT_CHARSET = "utf-8";

    /**
     * 执行系统命令
     *
     * @param command 命令
     * @return 命令执行完成输出内容
     * @throws IOException 执行失败时抛出异常，由调用者捕获处理
     * @throws InterruptedException 执行中断时抛出异常，由调用者捕获处理
     */
    public static String exec(String command) throws IOException, InterruptedException {

        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
        try (
                InputStream in = process.getInputStream();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {

            byte[] bytes = new byte[4096];
            int len = -1;
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }

            return out.toString(DEFAULT_CHARSET);

        }
    }

}
