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
public class LanguageConfig {
    /**
     * LanguageConfig entity members
     */
    private Run run;
    private Compile compile;
}
