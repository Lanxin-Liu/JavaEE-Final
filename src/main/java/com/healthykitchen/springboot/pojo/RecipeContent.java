package com.healthykitchen.springboot.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 20:18 2019/11/19
 * @version: v1.0
 */
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class RecipeContent implements Serializable {

    private int stepId;

    private int recipeId;

    private String stepDesc;

    private byte[] stepImage;

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

    public byte[] getStepImage() {
        return stepImage;
    }

    public void setStepImage(byte[] stepImage) {
        this.stepImage = stepImage;
    }
}
