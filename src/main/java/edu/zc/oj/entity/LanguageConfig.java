package edu.zc.oj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author keep-looking
 * @time 2021/3/5 - 17:45
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LanguageConfig {
    /**
     * LanguageConfig entity members
     */
    private String src;
    private Integer maxCpuTime;
    private Integer maxMemory;
    private Compile compile;
    private Run run;
    private List<TestCases> testCases;
}
