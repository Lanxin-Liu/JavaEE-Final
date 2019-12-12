package com.healthykitchen.springboot.pojo;


import java.io.Serializable;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 20:26 2019/11/19
 * @version: v1.0
 */

public class RecipeMaterial implements Serializable{

    private int recipeId;

    private String materialName;

    private int materialCount;

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }


    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public int getMaterialCount() {
        return materialCount;
    }

    public void setMaterialCount(int materialCount) {
        this.materialCount = materialCount;
    }
}
