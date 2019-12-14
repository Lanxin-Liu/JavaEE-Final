package com.healthykitchen.springboot.pojo;

import java.util.List;

public class DPRecipe {
    private DailyPlan dailyPlan;

    private Recipe recipe;

    public DailyPlan getDailyPlan() {
        return dailyPlan;
    }

    public void setDailyPlan(DailyPlan dailyPlan) {
        this.dailyPlan = dailyPlan;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
