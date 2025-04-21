package org.example.blog.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.blog.common.aspect.ApiOperationLog;
import org.example.blog.common.utils.BaseResponse;
import org.example.blog.common.utils.ResultUtils;
import org.example.blog.web.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class TestController {

    @PostMapping("/test")
    @ApiOperationLog()
    public BaseResponse<User> test(@RequestBody @Validated User user) {
        // 返参
        return ResultUtils.success();
    }

}
