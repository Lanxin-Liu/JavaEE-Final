package com.healthykitchen.springboot.pojo;

import java.util.Date;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 12:39 2019/11/23
 * @version: v1.0
 */
public class Like {
    private int likeId;
    private int likeRecipeId;
    private int likeUserId;
    private Date likeTime;

    public int getId() {
        return likeId;
    }

    public void setId(int id) {
        this.likeId = id;
    }

    public int getRecipeId() {
        return likeRecipeId;
    }

    public void setRecipeId(int recipeId) {
        this.likeRecipeId = recipeId;
    }

    public int getUserId() {
        return likeUserId;
    }

    public void setUserId(int userId) {
        this.likeUserId = userId;
    }

    public Date getTime() {
        return likeTime;
    }

    public void setTime(Date time) {
        this.likeTime = time;
    }
}
