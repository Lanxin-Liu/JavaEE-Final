package com.healthykitchen.springboot.dao;

import com.healthykitchen.springboot.pojo.RecipeStep;
import com.healthykitchen.springboot.service.RecipeService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 02:59 2019/11/22
 * @version: v1.0
 */
@Repository
public interface RecipeStepDAO {
    @Insert("insert into Recipe_Content (step_recipe_id, step_desc, step_image) values (#{stepRecipeId},#{stepDesc},#{stepImage})")
    void addRecipeStep(RecipeStep step);
}
