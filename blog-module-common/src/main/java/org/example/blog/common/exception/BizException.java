package org.example.blog.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BizException extends RuntimeException {

    private String code;

    private String message;

    public BizException(BaseExceptionInterface baseExceptionInterface) {
        this.code = baseExceptionInterface.getCode();
        this.message = baseExceptionInterface.getMessage();
    }
}
