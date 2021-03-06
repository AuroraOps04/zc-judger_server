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
import java.nio.file.Files;
import java.nio.file.Path;
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
    private JudgeConfiguration judgeConfiguration;
    private JudgeClient judgeClient;

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
        final Integer maxCpuTime = judgeParameter.getMaxCpuTime();
        final Integer maxMemory = judgeParameter.getMaxMemory();
        final String judgeLogPath = judgeConfiguration.getJudgeLogPath();
        final Integer userId = judgeConfiguration.getUserId();
        final Integer groupId = judgeConfiguration.getGroupId();

        final List<Result> results = new ArrayList<>();

        try (final JudgeResource judgeResource = new JudgeResource(UuidUtils.uuid32(), judgeConfiguration)) {
            final String workDir = judgeResource.getJudgeDir();

            // compile
            final String compileCommand = compile.getCompileCommand();
            final String exeName = compile.getExeName();
            final String srcPath = workDir + File.separator + compile.getSrcName();
            // generate src file
            final String command = String.format(compileCommand, srcPath, exeName);
            try (final PlanFileResource planFileResource = new PlanFileResource(judgeConfiguration, srcPath, src)) {
                // compile src file
                CommandUtils.exec(command);
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
                    config.setStack(32 * 1024 * 1024);
                    config.setMemory(maxMemory);
                    config.setSeccompRuleName(run.getSeccompRule());
                    config.setExePath(srcPath);
                    config.setCpuTime(maxCpuTime);
                    config.setEnv(run.getEnv());
                    config.setInputPath(inFilePath);
                    config.setOutputPath(outFilePath);

                    try (final PlanFileResource inFileResource = new PlanFileResource(judgeConfiguration, inFilePath, in);
                         final PlanFileResource outFileResource = new PlanFileResource(judgeConfiguration, outFilePath)
                    ) {
                        final Result result = judgeClient.run(config);
                        results.add(result);
                        if (result.getResult() == ResultCode.SUCCESS) {

                            String userOut = DigestUtils.md5DigestAsHex(StringUtils.trimEnd(Arrays.toString(Files.readAllBytes(Paths.get(outFilePath))).toCharArray()).getBytes());
                            String realOut = DigestUtils.md5DigestAsHex(StringUtils.trimEnd(out.toCharArray()).getBytes());
                            if(!userOut.equals(realOut)){
                                result.setResult(ResultCode.WRONG_ANSWER);
                            }
                        }
                    }
                }

            }


        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }

        return results;
    }
}
