package edu.zc.oj.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailTo:5239604@qq.com">coderPlus-tr</a>
 * @date 2021/3/6
 */

@Component
@Data
public class JudgeConfiguration {
    @Value("${judge.judge-workspace-base}")
    private String workDir;
    @Value("${judge.judge-user}")
    private String judgeUser;
    @Value("${judge.judge-group}")
    private String judgeGroup;
    @Value("${judge.judge-run-log-path}")
    private String judgeLogPath;
    @Value("${judge.judge-user-id}")
    private Integer userId;
    @Value("${judge.judge-group-id}")
    private Integer groupId;
}
