package com.healthykitchen.springboot.dao;

import com.healthykitchen.springboot.pojo.Like;
import org.apache.ibatis.annotations.Insert;
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
    @Insert("insert into Like (like_recipe_id,like_user_id,like_time) values (likeRecipeId, likeUserId, likeTime)")
    void insertLike(Like like);
}
