package com.healthykitchen.springboot.service;

import com.healthykitchen.springboot.dao.RecipeDAO;
import com.healthykitchen.springboot.dao.RecipeStepDAO;
import com.healthykitchen.springboot.dao.UserDAO;
import com.healthykitchen.springboot.pojo.HostHolder;
import com.healthykitchen.springboot.pojo.Recipe;
import com.healthykitchen.springboot.pojo.RecipeStep;
import com.healthykitchen.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

/**
 * @className:
 * @description: 菜谱排行/菜谱收藏/菜谱查找/菜谱发布
 * @author: anonym_co
 * @date: 15:57 2019/11/17
 * @version: v1.0
 */
@Service
public class RecipeService {
    @Autowired
    private RecipeDAO recipeDAO;
    @Autowired
    private RecipeStepDAO rsDAO;
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private UserDAO userDAO;

    public void add(Recipe recipe)
    {
        recipeDAO.addRecipe(recipe);
        recipe.setUserId(hostHolder.getUser().getId());
    }

    public void addStep(Recipe recipe, RecipeStep rs){
        rsDAO.addRecipeStep(rs);
    }
}
