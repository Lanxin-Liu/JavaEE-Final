package com.healthykitchen.springboot.result;

/**
 * @className:
 * @description:
 * @author: anonym_co
 * @date: 15:26 2019/11/15
 * @version: v1.0
 */
public enum ResultCode {
    SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
