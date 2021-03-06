package edu.zc.oj.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author keep-looking
 * @date 2021/3/6 - 17:13
 */
@Slf4j
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ping {
    /**
     * ping entity numbers
     */
    private String judgerVersion;
    private String hostName;
    private Integer cpuCore;
    private Integer memory;
    private String action;

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
