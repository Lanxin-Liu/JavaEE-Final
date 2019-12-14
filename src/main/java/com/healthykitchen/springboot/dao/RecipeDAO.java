package com.healthykitchen.springboot.dao;

import com.healthykitchen.springboot.pojo.Recipe;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
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
    @Insert("insert into Recipe (recipe_name, recipe_time, recipe_tag, recipe_image, size, recipe_user_id) values (#{recipeName},#{recipeTime},#{recipeTag},#{recipeImage},#{size},#{recipeUserId})")
    void addRecipe(Recipe recipe);

    @Select("SELECT * FROM Recipe order by recipe_time desc")
    List<Recipe> getAllRecipes();

    @Select("SELECT * FROM Recipe where recipe_id = #{recipeId}")
    Recipe getRecipeById(int recipeId);

    @Select("SELECT * FROM Recipe where locate(#{recipeName},recipe_name)>0;")
    List<Recipe> getRecipeByName(String recipeName);

    @Select("SELECT * FROM Recipe order by like_num desc ")
    List<Recipe> getRecipeByLike();

    @Select("SELECT distinct(recipe_id),recipe_name,recipe_time,recipe_tag,recipe_image,like_num,collect_num,size,recipe_user_id,recipe_desc FROM Recipe ,User_Info where User_Info.user_name=#{username} order by recipe_time desc;")
    List<Recipe> getRecipeByUserName(String username);

    @Select("select * from Recipe where recipe_user_id=1 order by recipe_time desc")
    List<Recipe> getRecipeByUserId(int userId);

    @Select("SELECT distinct(recipe_id),recipe_name,recipe_time,recipe_tag,recipe_image,like_num,collect_num,size,recipe_user_id,recipe_desc FROM Recipe ,Tag where Tag.tag_name=#{tagName} and Recipe.recipe_tag=Tag.tag_id;")
    List<Recipe> getRecipeByTag(String tagName);

    @Select("select COUNT(*) from Recipe")
    int getRecipeNum();

    @Update("UPDATE Recipe SET calorie=#{calorie} where recipe_id=#{recipeId}")
    void updateRecipeCalorie(Recipe recipe);


}
