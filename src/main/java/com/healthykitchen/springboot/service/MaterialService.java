package com.healthykitchen.springboot.service;

import com.healthykitchen.springboot.dao.RecipeDAO;
import com.healthykitchen.springboot.pojo.Recipe;
import com.healthykitchen.springboot.pojo.RecipeMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialService {
    @Autowired
    private RecipeDAO recipeDAO;

    int getRecipeCalorie(Recipe recipe){
        int recipeId=recipe.getRecipeId();
        int totalCalorie=recipeDAO.getRecipeCalorie(recipeId);
        return totalCalorie;
    }

    List<String> getRecipeMaterial(Recipe recipe){
        int recipeId=recipe.getRecipeId();
        List<RecipeMaterial> recipeMaterials=recipeDAO.getMaterial(recipeId);
        List<String> materials=new ArrayList<>();
        for(RecipeMaterial i:recipeMaterials){
            materials.add(i.getMaterialName());
        }
        return materials;
    }

}
