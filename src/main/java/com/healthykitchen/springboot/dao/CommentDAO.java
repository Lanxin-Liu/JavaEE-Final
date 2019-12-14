package com.healthykitchen.springboot.dao;

import com.healthykitchen.springboot.pojo.Comment;
import com.healthykitchen.springboot.pojo.Recipe;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 12:05 2019/11/23
 * @version: v1.0
 */
@Repository
public interface CommentDAO {
    @Insert("insert into Comment (comment_user_id,comment_recipe_id,comment_content,comment_time) values (#{commentUserId},#{commentRecipeId},#{commentContent},#{commentTime})")
    void insertComment(Comment comment);

    @Select("select * from Comment where comment_recipe_id=#{commentRecipeId}")
    List<Comment> getRecipeComment(int commentRecipeId);

}
