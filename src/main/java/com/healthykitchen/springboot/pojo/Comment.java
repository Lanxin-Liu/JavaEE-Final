package com.healthykitchen.springboot.pojo;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 12:01 2019/11/23
 * @version: v1.0
 */
public class Comment {
    private int commentId;
    private int commentUserId;
    private int commentRecipeId;
    private String commentContent;

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    private String commentTime;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(int commentUserId) {
        this.commentUserId = commentUserId;
    }

    public int getCommentRecipeId() {
        return commentRecipeId;
    }

    public void setCommentRecipeId(int commentRecipeId) {
        this.commentRecipeId = commentRecipeId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
