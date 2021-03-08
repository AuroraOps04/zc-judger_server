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
    @ApiModelProperty("源文件名")
    @NotNull
    private String srcName;

    @NotNull
    @ApiModelProperty("可执行文件名")
    private String exeName;

    @NotNull(message = "cpu运行最大时间不能为空")
    private Integer maxCpuTime;

    @NotNull
    private Integer maxRealTime;

    @NotNull(message = "虚拟内存大小不能为空")
    private Integer maxMemory;

    @ApiModelProperty("编译format字符串")
    @NotNull
    private String compileCommand;
}
