package com.healthykitchen.springboot.dao;

import com.healthykitchen.springboot.pojo.Recipe;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @className:
 * @description:
 * @author: anonym_co
 * @date: 15:40 2019/11/17
 * @version: v1.0
 */
@Repository
public interface RecipeDAO {
    @Insert("insert into Recipe (recipe_name, recipe_time, recipe_tag, recipe_image, size, recipe_user_id) values (#{name},#{time},#{tag},#{image},#{size},#{userId})")
    void addRecipe(Recipe recipe);

    @Select("SELECT * FROM healthykitchen.Recipe order by recipe_time desc")
    List<Recipe> getAllRecipes();
}
