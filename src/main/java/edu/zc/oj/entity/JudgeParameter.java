package edu.zc.oj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author keep-looking
 * @date 2021/3/6 - 14:03
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JudgeParameter {
    /**
     * judge's parameters
     */
    private LanguageConfig languageConfig;
    private List<TestCase> testCase;
    private String src;
    private Integer maxCpuTime;
    private Integer maxMemory;
}
