package edu.zc.oj.test;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author coderplus-tr
 * @cretetime 2021/3/2 19:10:10
 */
public class JudgerServerTest {
    @Test
    public void testRun() throws IOException, InterruptedException {
	   Process proc = Runtime.getRuntime().exec("/root/libjudger.so --help");
        // 注意下面的操作
        String ls_1;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        while ((ls_1 = bufferedReader.readLine()) != null){
            System.out.println(ls_1);
        };
        bufferedReader.close();
        proc.waitFor();
    }
}
