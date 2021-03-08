package edu.zc.oj.controller;

import edu.zc.oj.configuration.JudgeConfiguration;
import edu.zc.oj.entity.*;
import edu.zc.oj.entity.request.Compile;
import edu.zc.oj.entity.request.JudgeParameter;
import edu.zc.oj.entity.request.Run;
import edu.zc.oj.entity.request.TestCase;
import edu.zc.oj.judge.JudgeClient;
import edu.zc.oj.judge.JudgeResource;
import edu.zc.oj.judge.PlanFileResource;
import edu.zc.oj.utils.CommandUtils;
import edu.zc.oj.utils.StringUtils;
import edu.zc.oj.utils.UuidUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailTo:5239604@qq.com">coderPlus-tr</a>
 * @date 2021/3/6
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class JudgeController {
    private final JudgeConfiguration judgeConfiguration;
    private final JudgeClient judgeClient;

    /**
     * <ol>
     *     <li>通过languageConfig对源代码进行编译</li>
     *     <li>循环测试用例</li>
     *     <li>生成临时输入输出文件</li>
     *     <li>如果result.result == 0 进行下一步,否则直接返回</li>
     *     <li>比对临时输出文件和测试用例的输出去掉尾部空格的MD5值是否一致</li>
     *     <li>不一致更改result.result = 4</li>
     * </ol>
     *
     * @param judgeParameter 包含源代码,测试用例,语言配置
     * @return 所有测试用例的测试结果
     */
    @PostMapping("/judge")
    public List<Result> judge(@RequestBody @Valid JudgeParameter judgeParameter) throws Exception {
        final Compile compile = judgeParameter.getLanguageConfig().getCompile();
        final Run run = judgeParameter.getLanguageConfig().getRun();
        final String src = judgeParameter.getSrc();
        final String judgeLogPath = judgeConfiguration.getJudgeLogPath();
        final Integer userId = judgeConfiguration.getUserId();
        final Integer groupId = judgeConfiguration.getGroupId();

        final List<Result> results = new ArrayList<>();

        try (final JudgeResource judgeResource = new JudgeResource(UuidUtils.uuid32(), judgeConfiguration)) {
            final String workDir = judgeResource.getJudgeDir();

            // compile
            String compileCommandFormat = compile.getCompileCommand();
            final String exePath = workDir + File.separator + compile.getExeName();
            final String srcPath = workDir + File.separator + compile.getSrcName();
            // generate src file
            if (compileCommandFormat.contains("{src_path}")) {
                compileCommandFormat = compileCommandFormat.replace("{src_path}", srcPath);
            }
            if (compileCommandFormat.contains("{exe_dir}")) {
                compileCommandFormat = compileCommandFormat.replace("{exe_dir}", workDir);
            }
            if (compileCommandFormat.contains("{exe_path}")) {
                compileCommandFormat = compileCommandFormat.replace("{exe_path}", exePath);
            }
            try (final PlanFileResource planFileResource = new PlanFileResource(judgeConfiguration, srcPath, src)) {
                // compile src file
                CommandUtils.exec(compileCommandFormat);
                String commandFormat = run.getCommand();
                if (commandFormat.contains("{exe_path}")) {
                    commandFormat = commandFormat.replace("{exe_path}", exePath);
                }
                if (commandFormat.contains("{exe_dir}")) {
                    commandFormat = commandFormat.replace("{exe_dir}", workDir);
                }
                if (commandFormat.contains("{max_memory}")) {
                    commandFormat = commandFormat.replace("{max_memory}", String.valueOf(compile.getMaxMemory() / 1024));
                }
                final String[] commands = commandFormat.split(" ");
                final String command = commands[0];
                String[] args;
                if (commands.length > 1) {
                    args = new String[commands.length - 1];
                    System.arraycopy(commands, 1, args, 0, args.length );
                } else {
                    args = null;
                }
                final List<TestCase> testCases = judgeParameter.getTestCases();
                for (int i = 0; i < testCases.size(); i++) {
                    final String inFilePath = workDir + File.separator + i + ".in";
                    final String outFilePath = workDir + File.separator + i + ".out";
                    final String in = testCases.get(i).getIn();
                    final String out = testCases.get(i).getOut();

                    Config config = new Config();
                    config.setLogPath(judgeLogPath);
                    config.setGid(groupId);
                    config.setUid(userId);
                    config.setStack(128 * 1024 * 1024);
                    config.setMemory(judgeParameter.getMaxMemory() == null ? compile.getMaxMemory() : judgeParameter.getMaxMemory());
                    config.setSeccompRuleName(run.getSeccompRule());
                    config.setExePath(commands[0]);
                    config.setCpuTime(judgeParameter.getMaxCpuTime() == null ? compile.getMaxCpuTime() : judgeParameter.getMaxCpuTime());
                    config.setRealTime(compile.getMaxRealTime());
                    // TODO: 获取环境变量的ENV
                    config.setEnv(run.getEnv());
                    config.setArgs(args);
                    config.setInputPath(inFilePath);
                    config.setOutputPath(outFilePath);
                    config.setProcessNumber(JudgeClient.UNLIMITED);
                    config.setOutputSize(1024 * 1024 * 16);
                    config.setMemoryLimitCheckOnly(run.getMemoryLimitCheckOnly() == null ? 0 : run.getMemoryLimitCheckOnly());

                    try (final PlanFileResource inFileResource = new PlanFileResource(judgeConfiguration, inFilePath, in);
                         final PlanFileResource outFileResource = new PlanFileResource(judgeConfiguration, outFilePath)
                    ) {
                        final Result result = judgeClient.run(config);
                        results.add(result);
                        if (result.getResult() == ResultCode.SUCCESS) {

                            String userOut = DigestUtils.md5DigestAsHex(StringUtils.trimEnd(new String(Files.readAllBytes(Paths.get(outFilePath))).toCharArray()).getBytes());
                            String realOut = DigestUtils.md5DigestAsHex(StringUtils.trimEnd(out.toCharArray()).getBytes());
                            if (!userOut.equals(realOut)) {
                                result.setResult(ResultCode.WRONG_ANSWER);
                            }
                        }
                    }
                }
                try{
                    Files.delete(Paths.get(exePath));
                }catch (IOException ignore){

                }
            }


        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }

        return results;
    }
}
