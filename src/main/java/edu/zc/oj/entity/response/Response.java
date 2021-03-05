package edu.zc.oj.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author coderplus-tr
 * @date 2021/3/5 20:33:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private Integer code;
    private String message;
    private Object data;

    public Response(ResponseCode responseCode){
        code = responseCode.getCode();
        message = responseCode.getMessage();
    }
    public Response(ResponseCode responseCode, Object data){
        code = responseCode.getCode();
        message = responseCode.getMessage();
        this.data = data;
    }
}
