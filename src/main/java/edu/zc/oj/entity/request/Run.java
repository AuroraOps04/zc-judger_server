package edu.zc.oj.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

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
    @NotNull
    @ApiModelProperty("程序执行的format字符串")
    private String command;

    @ApiModelProperty("系统调用限制")
    private String seccompRule;


    @ApiModelProperty("环境变量")
    private String[] env;

    @ApiModelProperty
    private Integer memoryLimitCheckOnly;

}
