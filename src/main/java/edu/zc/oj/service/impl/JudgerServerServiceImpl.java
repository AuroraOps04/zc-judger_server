package edu.zc.oj.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.zc.oj.entity.Config;
import edu.zc.oj.entity.Result;
import edu.zc.oj.service.JudgerServerService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;


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
     * @param json string waiting to be deserialized
     * @param clazz return value type class
     * @return T Result or Config
     */
    private static <T> T transObject(String json, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        T object = null;
        try {
            object = objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public Result run(Config config) {
        Process process;
        Result result = null;
        String cmd = "libjudger.so " + config;
        try {
                process = Runtime.getRuntime().exec(cmd);
                System.out.println(process.waitFor());
                InputStreamReader ir = new InputStreamReader(process.getInputStream());
                LineNumberReader input = new LineNumberReader(ir);
                String line;
                StringBuilder resultJson = new StringBuilder();
                while ((line = input.readLine()) != null) {
                    resultJson.append(line);
                }
                input.close();
                ir.close();
                result = transObject(resultJson.toString(), Result.class);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }
}
