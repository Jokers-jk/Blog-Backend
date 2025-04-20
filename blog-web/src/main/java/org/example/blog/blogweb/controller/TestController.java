package org.example.blog.blogweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.blog.blogmodulecommon.aspect.ApiOperationLog;
import org.example.blog.blogmodulecommon.common.StatusCode;
import org.example.blog.blogmodulecommon.utils.BaseResponse;
import org.example.blog.blogmodulecommon.utils.ResultUtils;
import org.example.blog.blogweb.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @PostMapping("/test")
    @ApiOperationLog()
    public BaseResponse<User> test(@RequestBody User user) {
        // 返参
        return ResultUtils.error(StatusCode.NOT_LOGIN_ERROR);
    }

}
