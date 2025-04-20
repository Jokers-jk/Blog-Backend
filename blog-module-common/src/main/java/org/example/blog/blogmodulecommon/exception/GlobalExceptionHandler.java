package org.example.blog.blogmodulecommon.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.blog.blogmodulecommon.utils.BaseResponse;
import org.example.blog.blogmodulecommon.utils.ResultUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({ BizException.class })
    @ResponseBody
    public BaseResponse<Object> handleBizException(HttpServletRequest request, BizException e) {
        //todo 日志获取请求头
        log.warn("{} request error, errorCode: {}, errorMessage: {}", request.getRequestURI(), e.getCode(), e.getMessage());
        return ResultUtils.error(e);
    }
}
