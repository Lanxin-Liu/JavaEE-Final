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

    private int materialId;

    private String materialName;

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
}
