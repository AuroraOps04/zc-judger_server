package edu.zc.oj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author keep-looking
 * @time 2021/3/2 - 17:10
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
    private String inputFile;
    private String outputFile;
    private String errorFile;
    private String args;
    private String env;
    private String logPath;
    private String seccompRuleName;
    private Integer uid;
    private Integer gid;

    @Override
    public String toString(){
        return " --max_cpu_time=" + cpuTime + " --max_real_time=" + realTime + " --max_memory=" + memory + " --max_stack="
                + stack + " --max_process_number=" + processNumber + " --max_output_size=" + outputSize + " --memory_limit_check_only="
                + memoryLimitCheckOnly + " --exe_path=" + exePath + " --input_path=" + inputFile + " --output_path=" + outputFile
                + " --error_path=" + errorFile + " --args=" + args + " --env=" + env + " --log_path=" + logPath + " --seccomp_rule_name="
                + seccompRuleName + " --uid=" + uid + " --gid=" + gid;
    }
}