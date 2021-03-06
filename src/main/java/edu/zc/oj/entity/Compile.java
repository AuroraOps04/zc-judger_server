package edu.zc.oj.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author keep-looking
 * @date 2021/3/5 - 17:46
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel("编译配置")
public class Compile {
    /**
     * Compile entity members
     */
    @ApiModelProperty("源代码")
    private String srcName;
    private String exeName;
    private Integer maxCpuTime;
    private Integer maxRealTime;
    private Integer maxMemory;
    @ApiModelProperty("编译路径规则")
    private String compileCommand;
}
