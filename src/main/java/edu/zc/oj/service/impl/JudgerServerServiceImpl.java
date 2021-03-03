package edu.zc.oj.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.zc.oj.entity.Config;
import edu.zc.oj.entity.Result;
import edu.zc.oj.service.JudgerServerService;
import org.jcp.xml.dsig.internal.dom.DOMUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import static edu.zc.oj.entity.ErrorCode.INVALID_CONFIG;


public class JudgerServerServiceImpl implements JudgerServerService {
    /**
     * Serialization
     *
     * @param object Result or Config
     * @return json
     */
    private static String transJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * Deserialization
     *
     * @param json
     * @param clazz
     * @return T Result or Config
     */
    private static <T> T transObject(String json, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        T object = null;
        try {
            object = objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public Result run(Config config) {
        Process process = null;
        int exitCode;
        Result result = null;
        String cmd = "libjudger.so" + config;

        try {
            process = Runtime.getRuntime().exec(cmd);
            exitCode = process.waitFor();
            if (exitCode == 0) {
                InputStreamReader ir = new InputStreamReader(process.getInputStream());
                LineNumberReader input = new LineNumberReader(ir);
                String line;
                String resultJson = "";
                while ((line = input.readLine()) != null) {
                    resultJson += line;
                }
                input.close();
                ir.close();
                result = transObject(resultJson, Result.class);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }
}