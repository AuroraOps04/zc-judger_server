package edu.zc.oj.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.zc.oj.entity.Config;
import edu.zc.oj.entity.Result;
import edu.zc.oj.service.JudgerServerService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import static edu.zc.oj.entity.ErrorCode.INVALID_CONFIG;


public class JudgerServerServiceImpl implements JudgerServerService {
    /**
     * Serialization
     * @param object Result or Config
     * @return json
     */
    private static String transJson(Object object){
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
     * @param json
     * @param clazz
     * @return T Result or Config
     */
    private static <T> T transObject(String json, Class<T> clazz){
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
        String line = "";
        Process process = null;
        int exitValue;
        Result result = null;
        String cmd = "libjudger.so"+config.toString();
//        序列化对象信息

        try {
            //判断是否执行成功
            process = Runtime.getRuntime().exec(cmd);
            exitValue = process.waitFor();
            if(exitValue == 0){
//                成功
                InputStreamReader ir = new InputStreamReader(process.getInputStream());
                LineNumberReader input = new LineNumberReader(ir);
                while((line = input.readLine()) != null){
                    System.out.println(line);
                }
                input.close();
                ir.close();
//                应答
                result = transObject(line, Result.class);
                System.out.println(result);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }
}