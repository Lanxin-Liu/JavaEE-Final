package com.healthykitchen.springboot.pojo;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 20:00 2019/12/02
 * @version: v1.0
 */
public class Collection {
    private int collectionId;
    private String collectionName;
    private int collectionUserId;
    private int collectionRecipeId;

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public int getCollectionUserId() {
        return collectionUserId;
    }

    public void setCollectionUserId(int collectionUserId) {
        this.collectionUserId = collectionUserId;
    }

    public int getCollectionRecipeId() {
        return collectionRecipeId;
    }

    public void setCollectionRecipeId(int collectionRecipeId) {
        this.collectionRecipeId = collectionRecipeId;
    }
}
