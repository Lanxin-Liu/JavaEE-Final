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
    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getLikeRecipeId() {
        return likeRecipeId;
    }

    public void setLikeRecipeId(int likeRecipeId) {
        this.likeRecipeId = likeRecipeId;
    }

    public int getLikeUserId() {
        return likeUserId;
    }

    public void setLikeUserId(int likeUserId) {
        this.likeUserId = likeUserId;
    }

    public String getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(String likeTime) {
        this.likeTime = likeTime;
    }

    private int likeId;
    private int likeRecipeId;
    private int likeUserId;
    private String likeTime;


}
