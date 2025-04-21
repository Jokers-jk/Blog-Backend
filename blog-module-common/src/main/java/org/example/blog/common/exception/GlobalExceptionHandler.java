package org.example.blog.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.blog.common.common.StatusCode;
import org.example.blog.common.utils.BaseResponse;
import org.example.blog.common.utils.JsonUtil;
import org.example.blog.common.utils.ResultUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({ BizException.class })
    @ResponseBody
    public BaseResponse<Object> handleBizException(HttpServletRequest request, BizException e) {
        log.info("{} request error, errorCode: {}, errorMessage: {}", JsonUtil.handleRequest(request), e.getCode(), e.getMessage());
        return ResultUtils.error(e);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public BaseResponse<Object> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        log.info("{} request error, ", JsonUtil.handleRequest(request),e);
        return ResultUtils.error(StatusCode.REQUEST_ERROR);
    }


    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public BaseResponse<Object> handleOtherException(HttpServletRequest request, Exception e) {
        log.error("{} request error, ",JsonUtil.handleRequest(request), e);
        return ResultUtils.error(StatusCode.SYSTEM_ERROR);
    }


}
