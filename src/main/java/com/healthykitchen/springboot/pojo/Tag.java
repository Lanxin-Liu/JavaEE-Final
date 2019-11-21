package com.healthykitchen.springboot.pojo;


/**
 * @className:
 * @description:
 * @author: anonym_co
 * @date: 15:44 2019/11/17
 * @version: v1.0
 */
public class Tag {
    private int tagId;

    private String tagName;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
