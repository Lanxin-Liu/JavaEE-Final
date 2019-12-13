package com.healthykitchen.springboot.dao;

import com.healthykitchen.springboot.pojo.Recipe;
import com.healthykitchen.springboot.pojo.RecipeStep;
import com.healthykitchen.springboot.service.RecipeService;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 02:59 2019/11/22
 * @version: v1.0
 */
@Repository
public interface RecipeStepDAO {
    @Insert("insert into Recipe_Content (step_id, step_recipe_id, step_desc, step_image) values (#{stepId},#{stepRecipeId},#{stepDesc},#{stepImage})")
    void addRecipeStep(RecipeStep step);

    @Select("select count(*) from Recipe_Content where step_recipe_id = #{recipeId}")
    int getRecipeStepNum(Recipe recipe);

    @Select("select * from Recipe_Content where step_recipe_id = #{recipeId}")
    List<RecipeStep> getRecipeStepList(Recipe recipe);
}
