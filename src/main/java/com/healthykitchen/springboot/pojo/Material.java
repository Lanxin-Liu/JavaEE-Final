package com.healthykitchen.springboot.pojo;


/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 20:23 2019/11/19
 * @version: v1.0
 */
public class Material {

   private String materialName;

   private int calorie;

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }
}
