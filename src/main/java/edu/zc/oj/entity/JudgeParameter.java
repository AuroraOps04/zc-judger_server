package edu.zc.oj.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("judge的参数实体类")
public class JudgeParameter {
    /**
     * judge's parameters
     */
    @ApiModelProperty("使用的编程语言对应的一些配置")
    private LanguageConfig languageConfig;
    @ApiModelProperty("测试用例")
    private List<TestCase> testCase;
    @ApiModelProperty("源代码")
    private String src;
    @ApiModelProperty("cpu运行最大时间")
    private Integer maxCpuTime;
    @ApiModelProperty("进程虚拟内存的最大大小")
    private Integer maxMemory;
}
