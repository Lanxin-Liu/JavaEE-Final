package com.healthykitchen.springboot.dao;

import com.healthykitchen.springboot.pojo.Like;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 12:41 2019/11/23
 * @version: v1.0
 */
@Repository
public interface LikeDAO {
    @Insert("insert into Love (like_recipe_id,like_user_id,like_time,like_id) values (#{likeRecipeId}, #{likeUserId}, #{likeTime},#{likeId})")
    void insertLike(Like like);

    @Delete("delete from Like where like_recipe_id = #{recipeId} and like_user_id = #{userId}")
    void deleteLike(int recipeId, int userId);
}
