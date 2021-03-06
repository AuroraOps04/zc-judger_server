package edu.zc.oj.controller;

import edu.zc.oj.entity.Ping;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author keep-looking
 * @date 2021/3/6 - 16:04
 */
public class PingController {
    /**
     * return server's message
     * @return ping Object
     */
    @ApiOperation("ping接口")
    @PostMapping("/ping")
    public Ping ping(){
        Ping ping = new Ping();
        ping.setHostName(ping.getHostName());
        ping.setCpuCore(ping.getCpuCore());
        ping.setAction("pong");
        ping.setJudgerVersion("v1.0");
        return ping;
    }
}
