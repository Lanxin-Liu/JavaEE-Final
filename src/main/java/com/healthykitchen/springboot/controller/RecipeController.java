package com.healthykitchen.springboot.controller;

import com.healthykitchen.springboot.pojo.Recipe;
import com.healthykitchen.springboot.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @className:
 * @description: 发布：待解决的问题：怎么获取userid
 * @author: anonym_co
 * @date: 15:57 2019/11/17
 * @version: v1.0
 */
@Controller
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/release")
    public void releaseRecipe(Recipe recipe){

    }
}
