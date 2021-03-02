package edu.zc.oj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author keep-looking
 * @time 2021/3/2 - 17:10
 */

@Data
@ToString
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
    private String[] args;
    private String[] env;
    private String logPath;
    private String seccompRuleName;
    private Integer uid;
    private Integer gid;

}