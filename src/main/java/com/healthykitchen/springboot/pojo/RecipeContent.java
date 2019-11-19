package com.healthykitchen.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 20:18 2019/11/19
 * @version: v1.0
 */

@Entity
@Table(name = "Recipe_Content")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class RecipeContent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "step_id")
    private int stepId;

    @Id
    @Column(name = "step_recipe_id")
    private int recipeId;

    @Column(name = "step_desc")
    private String stepDesc;

    @Column(name = "step_image")
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
