package com.healthykitchen.springboot.pojo;

/**
 * @className:
 * @description:
 * @author: anonym_co
 * @date: 15:40 2019/11/17
 * @version: v1.0
 */

public class Recipe {
    private int recipeId;

    private String recipeName;

    private String recipeTime;

    private int recipeTag;

    private String recipeDesc;

    private String recipeImage;

    private int likeNum;

    private int collectNum;

    private int size;

    private int recipeUserId;

    private String recipeUsername;

    private int recipeCalorie;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getRecipeCalorie() {
        return recipeCalorie;
    }

    public void setRecipeCalorie(int recipeCalorie) {
        this.recipeCalorie = recipeCalorie;
    }

    public String getRecipeUsername() {
        return recipeUsername;
    }

    public void setRecipeUsername(String recipeUsername) {
        this.recipeUsername = recipeUsername;
    }



    public Recipe() {

    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeTime() {
        return recipeTime;
    }

    public void setRecipeTime(String recipeTime) {
        this.recipeTime = recipeTime;
    }

    public int getRecipeTag() {
        return recipeTag;
    }

    public void setRecipeTag(int recipeTag) {
        this.recipeTag = recipeTag;
    }

    public String getRecipeDesc() {
        return recipeDesc;
    }

    public void setRecipeDesc(String recipeDesc) {
        this.recipeDesc = recipeDesc;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(int collectNum) {
        this.collectNum = collectNum;
    }

    public int getRecipeUserId() {
        return recipeUserId;
    }

    public void setRecipeUserId(int recipeUserId) {
        this.recipeUserId = recipeUserId;
    }

}
