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
    private int stepRecipeId;
    private String stepDesc;
    private String stepImage;

    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public int getRecipeId() {
        return stepRecipeId;
    }

    public void setRecipeId(int recipeId) {
        this.stepRecipeId = recipeId;
    }

    public String getStepDesc() {
        return stepDesc;
    }

    public void setStepDesc(String stepDesc) {
        this.stepDesc = stepDesc;
    }

    public String getImage() {
        return stepImage;
    }

    public void setImage(String image) {
        this.stepImage = image;
    }
}
