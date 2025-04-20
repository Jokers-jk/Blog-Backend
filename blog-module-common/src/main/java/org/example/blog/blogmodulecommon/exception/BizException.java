package org.example.blog.blogmodulecommon.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BizException extends RuntimeException {

    private Integer code;

    private String message;

    public BizException(BaseExceptionInterface baseExceptionInterface) {
        this.code = baseExceptionInterface.getCode();
        this.message = baseExceptionInterface.getMessage();
    }
}
