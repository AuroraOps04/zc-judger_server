package edu.zc.oj.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author keep-looking
 * @date 2021/3/5 - 17:47
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel("运行配置")
public class Run {
    /**
     * Run entity members
     */
    @ApiModelProperty("程序执行路径")
    private String command;
    @ApiModelProperty("使用的语言规则")
    private String seccompRule;
    @ApiModelProperty("环境变量")
    private String[] env;

}
