package edu.zc.oj.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author keep-looking
 * @time 2021/3/2 - 17:40
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Result {
    /*
    * Result entity members
    */
    private Integer cpuTime;
    private Integer realTime;
    private Integer memory;
    private Integer signal;
    private Integer exitCode;
    private ErrorCode result;
    private ResultCode error;

}
