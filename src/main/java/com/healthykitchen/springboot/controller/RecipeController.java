package com.healthykitchen.springboot.controller;

import com.healthykitchen.springboot.dao.RecipeDAO;
import com.healthykitchen.springboot.dao.UserDAO;
import com.healthykitchen.springboot.pojo.HostHolder;
import com.healthykitchen.springboot.pojo.Recipe;
import com.healthykitchen.springboot.pojo.RecipeStep;
import com.healthykitchen.springboot.result.Result;
import com.healthykitchen.springboot.result.ResultFactory;
import com.healthykitchen.springboot.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @className:
 * @description: 发布:问题记录1.添加步骤2.添加图片 返回类型未确定
 * @author: anonym_co
 * @date: 15:57 2019/11/17
 * @version: v1.0
 */
@Controller
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RecipeDAO recipeDAO;

    //获取所有菜谱 按时间排序
    @GetMapping("api/recipelist")
    @ResponseBody
    public List<Recipe> getAllRecipes(){
        List<Recipe> recipes=recipeDAO.getAllRecipes();
        return recipes;
    }

    //根据菜谱名字获取菜谱
    @GetMapping("api/searchrecipe")
    @ResponseBody
    public  List<Recipe> getRecipeByName(String name){
        List<Recipe> recipes=this.recipeService.getRecipeByName(name);
        return recipes;
    }


    /*
    //添加菜谱
    @GetMapping("api/release")
    @ResponseBody
    public void releaseRecipe(Model model, @RequestParam("name") String recipeName, @RequestParam(value = "tag",
            required = false, defaultValue = "1") int tag, @RequestParam(value = "image",
            required = false, defaultValue = "null") byte[] image) {
        try {
            Recipe recipe = new Recipe();
            recipe.setUserId(hostHolder.getUser().getId());
            recipe.setName(recipeName);
            recipe.setTag(tag);
            recipe.setImage(image);
            recipeService.add(recipe);
            model.addAttribute("success_msg", "菜谱发布成功");
            model.addAttribute("jump_url", "/");
        } catch (Exception e) {
            model.addAttribute("error_msg", "菜谱发布失败");
            model.addAttribute("jump_url", "/");
        }

    }

    //添加步骤（url和返回的错误信息未确定）
    @GetMapping("api/release")
    public void addStepToRecipe(Recipe recipe, @RequestParam("desc") String desc, @RequestParam("image") byte[] image)
    {
        RecipeStep rs = new RecipeStep();
        rs.setStepDesc(desc);
        rs.setImage(image);
        rs.setRecipeId(recipe.getRecipeId());
        recipeService.addStep(recipe, rs);
    }

     */



}
