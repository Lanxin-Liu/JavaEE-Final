package com.healthykitchen.springboot.controller;

import com.healthykitchen.springboot.dao.CollectionDAO;
import com.healthykitchen.springboot.dao.LikeDAO;
import com.healthykitchen.springboot.dao.RecipeDAO;
import com.healthykitchen.springboot.dao.UserDAO;
import com.healthykitchen.springboot.pojo.*;
import com.healthykitchen.springboot.result.Result;
import com.healthykitchen.springboot.result.ResultFactory;
import com.healthykitchen.springboot.service.CollectService;
import com.healthykitchen.springboot.service.RecipeService;

import com.healthykitchen.springboot.service.TagService;

import com.healthykitchen.springboot.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
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
    private RecipeDAO recipeDAO;
    @Autowired
    private CollectService collectService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CollectionDAO collectionDAO;


    //获取所有菜谱 按时间排序
    @GetMapping("api/recipelist")
    @ResponseBody
    public List<Recipe> getAllRecipes(){
        List<Recipe> recipes=recipeDAO.getAllRecipes();
        return recipes;
    }


    //根据菜谱热爱程度获取菜谱
    @GetMapping("api/recipeRank")
    @ResponseBody
    public  List<Recipe> getRecipeByLike(){
        List<Recipe> recipes=this.recipeService.getRecipeByLike();
        return  recipes;
    }

    @GetMapping("api/searchrecipebyuser")
    @ResponseBody
    public List<Recipe> getRecipeByUserName(String username){
        List<Recipe> recipes=this.recipeService.getRecipeByUserName(username);
        return recipes;
    }

    @GetMapping("api/userrecipelist")
    @ResponseBody
    public List<Recipe> getRecipeByUserId(int userId){
        List<Recipe> recipes=this.recipeService.getRecipeByUserId(userId);
        return recipes;
    }

    @GetMapping("api/searchrecipebytag")
    @ResponseBody
    public List<Recipe> getRecipeByTag(String tagName){
        //int tagId=tagService.getTagId(tagName);
        List<Recipe> recipes=this.recipeService.getRecipeByTag(tagName);
        return recipes;
    }

    @GetMapping("api/like")
    @ResponseBody
    public Result likeRecipe(@RequestParam(value = "recipeId") int rId, HttpSession httpSession) {
        try {
            User user = (User) httpSession.getAttribute("user");
            int uId;
            uId = user.getId();
            Recipe recipe = recipeService.getRecipeById(rId);
            collectService.addLikeToRecipe(recipe, uId);
            recipe.setLikeNum(recipe.getLikeNum() + 1);
            return ResultFactory.buildSuccessResult(recipe);

        } catch(Exception e) {
            return ResultFactory.buildFailResult("点赞失败！");
        }

    }

    @GetMapping("api/collect")
    @ResponseBody
    public Result collectRecipe(@RequestParam(value = "recipeId") int rId,@RequestParam(value = "collectionName") String cName, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("User");
        int uId = user.getId();
        if(collectService.ifExist(uId, cName)) {
            int recipeNums = collectionDAO.getRecipeNums(uId);
            Collection c = new Collection();
            c.setCollectionId(recipeNums+1);
            c.setCollectionName(cName);
            c.setCollectionRecipeId(rId);
            c.setCollectionUserId(uId);
            collectionDAO.addCollection(c);
            return ResultFactory.buildSuccessResult(c);
        } else {
            return ResultFactory.buildFailResult("该收藏夹不存在！");
        }
    }

    @GetMapping("api/addCollection")
    @ResponseBody
    public Result createNewCollection(@RequestParam("collectionName") String cName, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("User");
        int uId = user.getId();
        if(!collectService.ifExist(uId, cName)) {
            int recipeNums = collectionDAO.getRecipeNums(uId);
            Collection c = new Collection();
            c.setCollectionUserId(uId);
            c.setCollectionName(cName);
            c.setCollectionId(recipeNums + 1);
            collectService.createCollection(c);
            return ResultFactory.buildSuccessResult(c);
        } else {
            return ResultFactory.buildFailResult("该收藏夹已存在！");
        }
    }

    /**
     * 添加菜谱
     * @param recipe
     * @param httpSession
     * @return
     */
    @PostMapping("api/release")
    @ResponseBody
    public Result releaseRecipe(@RequestParam("Recipe") Recipe recipe,HttpSession httpSession) {
        try {
            User user = (User) httpSession.getAttribute("User");
            DateUtil date = new DateUtil();
            int uId = user.getId();
            recipe.setRecipeTime(date.getTime());
            recipe.setRecipeUserId(uId);
            recipeService.addRecipe(recipe);
            return ResultFactory.buildSuccessResult(recipe);
        } catch (Exception e) {
            return ResultFactory.buildFailResult("添加菜谱失败！");
        }
    }

    /**
     * 添加步骤
     * @param recipe
     */
    @PostMapping("api/release")
    public void addStepToRecipe(Recipe recipe)
    {
        RecipeStep rs = new RecipeStep();
        rs.setRecipeId(recipe.getRecipeId());
        recipeService.addStep(rs);
    }

    /**
     * 添加评论
     */
    @GetMapping("api/comment")
    public Result commentToRecipe(@RequestParam("recipeId") int rId, @RequestParam("content") String content, HttpSession httpSession) {
        User user = (User)httpSession.getAttribute("User");
        Comment comment = new Comment();
        comment.setCommentRecipeId(rId);
        comment.setCommentContent(content);
        comment.setCommentUserId(user.getId());
        recipeService.addComment(comment);
        return ResultFactory.buildSuccessResult(comment);
    }






}
