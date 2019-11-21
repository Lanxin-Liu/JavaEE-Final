package com.healthykitchen.springboot.pojo;


/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 20:23 2019/11/19
 * @version: v1.0
 */
public class Material {
    private int mId;

    private int calorie;

    private String mName;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
