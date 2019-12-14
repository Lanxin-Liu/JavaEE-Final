package com.healthykitchen.springboot.pojo;

import org.springframework.stereotype.Component;

@Component
public class DailyPlan {

    private int DPId;

    private String DPDate;

    private int DPCalorie;

    private int DPUserId;

    public int getDPRecipeId() {
        return DPRecipeId;
    }

    public void setDPRecipeId(int DPRecipeId) {
        this.DPRecipeId = DPRecipeId;
    }

    private int DPRecipeId;

    public DailyPlan(){

    }

    public int getDPId() {
        return DPId;
    }

    public void setDPId(int DPId) {
        this.DPId = DPId;
    }


    public String getDPDate() {
        return DPDate;
    }

    public void setDPDate(String DPDate) {
        this.DPDate = DPDate;
    }

    public int getDPCalorie() {
        return DPCalorie;
    }

    public void setDPCalorie(int DPCalorie) {
        this.DPCalorie = DPCalorie;
    }


    public int getDPUserId() {
        return DPUserId;
    }

    public void setDPUserId(int DPUserId) {
        this.DPUserId = DPUserId;
    }



}
