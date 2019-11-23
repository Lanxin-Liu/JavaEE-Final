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

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }
}
