package com.healthykitchen.springboot.dao;

import com.healthykitchen.springboot.pojo.RecipeMaterial;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeMaterialDAO {
    @Select("SELECT Recipe_has_Material.recipe_id,Material.material_name,Recipe_has_Material.material_count FROM Material,Recipe_has_Material where recipe_id=#{recipeId}")
    List<RecipeMaterial> getRecipeMaterial(int recipeId);

    @Insert("insert Recipe_has_Material(recipe_id,material_name,material_count) values (#{recipeId},#{materialName},#{materialCount})")
    void addRecipeMaterial(RecipeMaterial recipeMaterial);

}
