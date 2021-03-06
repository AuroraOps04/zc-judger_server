package edu.zc.oj.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

/**
 * @author keep-looking
 * @date 2021/3/2 - 17:10
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Config {
    /*
     *   config entity members
     */

    private Integer cpuTime;
    private Integer realTime;
    private Integer memory;
    private Integer stack;
    private Integer processNumber;
    private Integer outputSize;
    private Integer memoryLimitCheckOnly;
    private String exePath;
    private String inputPath;
    private String outputPath;
    private String errorPath;
    private String[] args;
    private String[] env;
    private String logPath;
    private String seccompRuleName;
    private Integer uid;
    private Integer gid;

    @Override
    public String toString(){

        StringBuilder reArgs = new StringBuilder();
        StringBuilder reEnv = new StringBuilder();
        for (String arg : args) {
            reArgs.append(" --args=").append(arg);
        }
        for (String en:env){
            reEnv.append(" --env=").append(en);
        }

        return " --max_cpu_time=" + cpuTime + " --max_real_time=" + realTime + " --max_memory=" + memory + " --max_stack="
                + stack + " --max_process_number=" + processNumber + " --max_output_size=" + outputSize + " --memory_limit_check_only="
                + memoryLimitCheckOnly + " --exe_path=" + exePath + " --input_path=" + inputPath + " --output_path=" + outputPath
                + " --error_path=" + errorPath + reArgs + reEnv + " --log_path=" + logPath + " --seccomp_rule_name="
                + seccompRuleName + " --uid=" + uid + " --gid=" + gid;
    }

}