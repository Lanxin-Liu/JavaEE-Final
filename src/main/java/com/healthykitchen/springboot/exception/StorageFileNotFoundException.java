package com.healthykitchen.springboot.exception;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 16:11 2019/12/08
 * @version: v1.0
 */

public class StorageFileNotFoundException extends StorageException {

    private static final long serialVersionUID = -7119518537629449580L;

    public StorageFileNotFoundException(String message) {

        super(message);

    }

    public StorageFileNotFoundException(String message, Throwable cause) {

        super(message, cause);

    }

}
