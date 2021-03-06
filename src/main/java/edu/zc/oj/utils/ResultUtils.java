package edu.zc.oj.utils;

import edu.zc.oj.entity.response.Response;
import edu.zc.oj.entity.response.ResponseCode;

/**
 * @author coderplus-tr
 * @date 2021/3/5 20:41:48
 */
public class ResultUtils {
    public static Response success(){
        return success(null);
    }
    public static Response success(Object data){
        return new Response(ResponseCode.SUCCESS, data);
    }
    public static Response serverError(){
        return new Response(ResponseCode.SERVER_ERROR);
    }

    public static Response paramsError(){
       return paramsError(null);
    }
    public static Response paramsError(Object error){
        return new Response(ResponseCode.PARAMS_ERROR, error);
    }

    public static Response notFoundError() {
        return new Response(ResponseCode.NOT_FOUND);
    }
}
