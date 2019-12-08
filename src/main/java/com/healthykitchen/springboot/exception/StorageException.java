package com.healthykitchen.springboot.exception;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 16:10 2019/12/08
 * @version: v1.0
 */
public class StorageException extends RuntimeException {

    private static final long serialVersionUID = 2430191988074222554L;

    public StorageException(String message) {

        super(message);

    }

    public StorageException(String message, Throwable cause) {

        super(message, cause);

    }

}

