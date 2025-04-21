package org.example.blog.common.utils;


import org.example.blog.common.common.StatusCode;
import org.example.blog.common.exception.BizException;

/**
 *  返回结果封装类
* */

public class ResultUtils {


    /**
     * success
     * @return BaseResponse
     */
    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(StatusCode.SUCCESS,StatusCode.SUCCESS.getMessage());
    }
    /**
     * success
     *
     * @param data 返回结果
     * @return BaseResponse
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(StatusCode.SUCCESS, data, StatusCode.SUCCESS.getMessage());
    }


    /**
     * success
     *
     * @param data 返回结果
     * @return BaseResponse
     */
    public static <T> BaseResponse<T> success(StatusCode statusCode,T data) {
        return new BaseResponse<>(statusCode, data, statusCode.getMessage());
    }

    /**
     * success
     *
     * @param data 返回结果
     * @return BaseResponse
     */
    public static <T> BaseResponse<T> success(String code, T data, String message) {
        return new BaseResponse<>(code, data, message);
    }

    /**
     * @param statusCode 状态码
     * @return BaseResponse
     */
    public static <T> BaseResponse<T> error(StatusCode statusCode) {
        return new BaseResponse<>(statusCode);
    }

    /**
     * @param statusCode 状态码
     * @param message 错误信息
     * @return BaseResponse
     */
    public static <T> BaseResponse<T> error(StatusCode statusCode, String message) {
        return new BaseResponse<>(statusCode, message);
    }

    /**
     * 失败 自定义
     *
     * @param code    自定义状态码
     * @param message 信息
     * @return BaseResponse
     */
    public static <T> BaseResponse<T> error(String code, String message) {
        return new BaseResponse<>(code, message);
    }

    /**
     * 失败 异常
     *
     * @param bizException  异常
     * @return BaseResponse
     */
    public static <T> BaseResponse<T> error(BizException bizException) {
        return new BaseResponse<>(bizException.getCode(), bizException.getMessage());
    }
}
