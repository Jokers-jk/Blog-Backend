package org.example.blog.common.utils;

import lombok.Data;
import org.example.blog.common.common.StatusCode;

import java.io.Serializable;

@Data
public class BaseResponse<T> implements Serializable {

    private String code;

    private T data;

    private String message;

    public BaseResponse(String code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(String code) {
        this(code, null, "");
    }

    public BaseResponse(String code, T data) {
        this(code, data, "");
    }

    public BaseResponse(String code, String message) { this(code, null, message); }

    public BaseResponse(StatusCode statusCode) {
        this(statusCode.getCode(), null, statusCode.getMessage());
    }

    public BaseResponse(StatusCode statusCode, String message) {
        this(statusCode.getCode(), null, message);
    }

    public BaseResponse(StatusCode statusCode, T data, String message) {
        this(statusCode.getCode(), data, message);
    }
}

