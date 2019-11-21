package com.healthykitchen.springboot.pojo;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 02:51 2019/11/22
 * @version: v1.0
 */
public class RecipeStep {
    private int stepId;
    private int recipeId;
    private String stepDesc;
    private byte[] image;

    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getStepDesc() {
        return stepDesc;
    }

    public void setStepDesc(String stepDesc) {
        this.stepDesc = stepDesc;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
