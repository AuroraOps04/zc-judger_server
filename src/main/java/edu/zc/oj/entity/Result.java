package edu.zc.oj.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author keep-looking
 * @date 2021/3/2 - 17:40
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Result {
    /*
    * Result entity members
    */

    @JsonProperty("cpu_time")
    private Integer cpuTime;
    @JsonProperty("real_time")
    private Integer realTime;
    private Integer memory;
    private Integer signal;
    @JsonProperty("exit_code")
    private Integer exitCode;
    private ErrorCode error;
    private ResultCode result;
}
