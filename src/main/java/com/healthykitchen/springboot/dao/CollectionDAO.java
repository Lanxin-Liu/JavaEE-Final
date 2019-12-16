package com.healthykitchen.springboot.dao;

import com.healthykitchen.springboot.pojo.Collection;
import com.healthykitchen.springboot.pojo.Recipe;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 20:06 2019/12/02
 * @version: v1.0
 */
@Repository
public interface CollectionDAO {
    @Insert("insert into Collection (collection_name,collection_user_id,collection_recipe_id) values (#{collectionName},#{collectionUserId},#{collectionRecipeId})")
    void addCollection(Collection collection);

    @Select("SELECT * FROM Collection where collection_name = #{collectionName} and collection_user_id = #{uId}")
    Collection getCollectionByUserIdAndName(int uId, String collectionName);

    @Select("SELECT COUNT(collection_recipe_id) FROM Collection WHERE collection_user_id = #{userId}")
    int getRecipeNums(int userId);

    @Select("SELECT collection_recipe_id FROM Collection where collection_user_id = #{collectionUserId}")
    int[] getMyCollection(int collectionUserId);


}
