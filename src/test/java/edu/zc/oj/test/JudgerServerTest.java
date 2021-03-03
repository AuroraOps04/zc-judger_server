package edu.zc.oj.test;

import edu.zc.oj.entity.Config;
import edu.zc.oj.entity.Result;
import edu.zc.oj.service.JudgerServerService;
import edu.zc.oj.service.impl.JudgerServerServiceImpl;
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
    public void testRun()  {
        final Config config = new Config();
        config.setCpuTime(1000);
        config.setRealTime(2000);
        config.setMemory(128 * 1024 * 1024);
        config.setProcessNumber(200);
        config.setOutputSize(10000);
        config.setStack(32 * 1024 * 1024);
        config.setExePath("/bin/echo");
        config.setInputFile("/dev/null");
        config.setOutputFile("echo.out");
        config.setErrorFile("echo.out");
        config.setArgs("helloWorld");
        config.setEnv("foo=bar");
        config.setLogPath("judger.log");
        config.setSeccompRuleName("c_cpp");
        config.setUid(0);
        config.setGid(0);
        JudgerServerService judger = new JudgerServerServiceImpl();
        final Result result = judger.run(config);
        System.out.println(result);
    }
}
