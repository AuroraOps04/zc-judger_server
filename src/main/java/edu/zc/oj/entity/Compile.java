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
    private String srcName;
    private String exeName;
    private Integer maxCpuTime;
    private Integer maxRealTime;
    private Integer maxMemory;
    private String compileCommand;
}
