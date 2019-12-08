package com.healthykitchen.springboot.common;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 15:51 2019/12/08
 * @version: v1.0
 */

public class StorageProperties {

    /**
     * Folder location for storing files
     */


    private String location = "src/main/resources/static/upload-dir";

    public String getLocation() {

        return location;

    }

    public void setLocation(String location) {

        this.location = location;

    }

}

