package edu.zc.oj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author keep-looking
 * @time 2021/3/5 - 18:21
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TestCases {
    /**
     * TestCases entity members
     */
    private String in;
    private String out;
}
