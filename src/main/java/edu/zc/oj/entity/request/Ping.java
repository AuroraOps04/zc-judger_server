package edu.zc.oj.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author keep-looking
 * @date 2021/3/6 - 17:13
 */
@Slf4j
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel("ping实体")
public class Ping {
    /**
     * ping entity numbers
     */
    private String judgerVersion;
    private String hostName;
    private Integer cpuCore;
    private Integer memory;
    private String action;

    @ApiModelProperty("得到服务器的主机名")
    public String getHostName(){
        String hostName = "";
        try {
            InetAddress addr = InetAddress.getLocalHost();
            hostName = addr.getHostName();

        } catch (UnknownHostException e) {
            log.warn(e.getMessage());
        }
        return hostName;
    }
    @ApiModelProperty("得到服务器的cpu核心数")
    public int getCpuCore(){
        return Runtime.getRuntime().availableProcessors();
    }

    public String getJudgerVersion() {
        return judgerVersion;
    }

    public String getAction() {
        return action;
    }

}
