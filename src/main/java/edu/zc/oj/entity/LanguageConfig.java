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
 * @date 2021/3/5 - 17:45
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel("使用的编程语言对应的一些配置")
public class LanguageConfig {
    /**
     * LanguageConfig entity members
     */
    @ApiModelProperty("运行配置")
    private Run run;
    @ApiModelProperty("编译配置")
    private Compile compile;
}
