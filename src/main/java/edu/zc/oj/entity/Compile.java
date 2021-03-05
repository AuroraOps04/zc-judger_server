package edu.zc.oj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author keep-looking
 * @time 2021/3/5 - 17:46
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Compile {
    /**
     * Compile entity members
     */
    private String src_name;
    private String exe_name;
    private Integer max_cpu_time;
    private Integer max_real_time;
    private Integer max_memory;
    private String compile_command;
}
