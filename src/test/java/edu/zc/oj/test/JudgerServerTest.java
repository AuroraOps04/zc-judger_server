package edu.zc.oj.test;

import edu.zc.oj.entity.Config;
import edu.zc.oj.entity.Result;
import edu.zc.oj.judge.JudgeClient;
import edu.zc.oj.judge.impl.JudgeClientImpl;
import org.junit.Test;


/**
 * @author coderplus-tr
 * @date 2021/3/2 19:10:10
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
        config.setInputPath("/dev/null");
        config.setOutputPath("echo.out");
        config.setErrorPath("echo.out");
        config.setArgs(new String[]{"helloworld"});
        config.setEnv(new String[]{"foo=bar"});
        config.setLogPath("judger.log");
        config.setSeccompRuleName("c_cpp");
        config.setMemoryLimitCheckOnly(0);
        config.setUid(0);
        config.setGid(0);
    }
}
