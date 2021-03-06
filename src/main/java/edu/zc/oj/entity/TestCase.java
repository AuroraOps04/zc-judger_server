package edu.zc.oj.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author keep-looking
 * @date 2021/3/5 - 18:21
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("测试用例")
public class TestCase {
    /**
     * TestCase entity members
     */

    private String in;
    private String out;
    private Integer id;
}
