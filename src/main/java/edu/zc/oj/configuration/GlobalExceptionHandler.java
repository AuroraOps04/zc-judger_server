package edu.zc.oj.configuration;

import edu.zc.oj.entity.response.Response;
import edu.zc.oj.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author coderplus-tr
 * @date 2021/3/5 20:36:18
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value=MethodArgumentNotValidException.class)
    public Response methodArgumentNotValidHandler(HttpServletRequest request,
                                                  MethodArgumentNotValidException exception) {
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        StringBuffer errorMsg= new StringBuffer();
        errors.forEach(x -> errorMsg.append(x.getDefaultMessage()).append(";"));
        log.error("---MethodArgumentNotValidException Handler--- ERROR: {}", errorMsg.toString());
        return ResultUtils.paramsError(errors);
    }

    @ExceptionHandler(value = Exception.class)
    public Response unknownExceptionHandler(Exception e){
        log.error("occurrence unknown exception cause: {} ", e.getMessage());
        return ResultUtils.serverError();
    }
}
