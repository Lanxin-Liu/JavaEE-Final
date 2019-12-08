package com.healthykitchen.springboot.dao;

import com.healthykitchen.springboot.pojo.Comment;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 12:05 2019/11/23
 * @version: v1.0
 */
@Repository
public interface CommentDAO {
    @Insert("insert into Comment (comment_user_id,comment_recipe_id,comment_content) values (#{commentUserId},#{commentRecipeId},#{commentContent})")
    void insertComment(Comment comment);
}
