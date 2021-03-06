package edu.zc.oj.controller;

import edu.zc.oj.entity.response.Response;
import edu.zc.oj.utils.ResultUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author <a href="mailTo:5239604@qq.com">coderPlus-tr</a>
 * @date 2021/3/6
 */

@Controller
public class NotFoundController implements ErrorController {
    /**
     * handle 404 error
     */

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = {"/error"})
    @ResponseBody
    public Response error() {
        return ResultUtils.notFoundError();
    }

}