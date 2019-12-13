package com.healthykitchen.springboot.service;

import com.healthykitchen.springboot.dao.*;
import com.healthykitchen.springboot.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.List;

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
    private UserDAO userDAO;
    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private RecipeMaterialDAO recipeMaterialDAO;
    @Autowired
    private MaterialDao materialDao;


    public RecipeDAO getRecipeDAO() {
        return recipeDAO;
    }

    public void addStep(RecipeStep rs) {
        rsDAO.addRecipeStep(rs);
    }

    public List<Comment> getRecipeComment(int recipeId){
        return commentDAO.getRecipeComment(recipeId);
    }


    public List<RecipeMaterial> getRecipeMaterial(int recipeId){
        return recipeMaterialDAO.getRecipeMaterial(recipeId);
    }

    public void setRecipeDAO(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    public void addRecipe(Recipe recipe) {
        recipeDAO.addRecipe(recipe);
    }

    public void addRecipeMaterial(List<RecipeMaterial> recipeMaterials){
        for (RecipeMaterial rm:recipeMaterials){
            recipeMaterialDAO.addRecipeMaterial(rm);
        }
    }

    public int getRecipeCalorie(Recipe recipe){
        int rId;
        rId=recipe.getRecipeId();
        List<RecipeMaterial> rm=new ArrayList<>();
        Material m;
        rm=recipeMaterialDAO.getRecipeMaterial(rId);
        int totc=0;//总卡路里
        int count;
        int ec;//单位卡路里
        for (RecipeMaterial i:rm){
            m=materialDao.getMaterialCalorie(i.getMaterialName());
            //System.out.println(m.getCalorie()+"aaaaa"+m.getMaterialName());
            ec=m.getCalorie();
            count=i.getMaterialCount();
            //System.out.println(i.getMaterialCount());
            totc=ec*count+totc;
        }
        return totc;
    }

    public void addComment(Comment comment) {
        commentDAO.insertComment(comment);
    }


    public Recipe getRecipeById(int id) {
        Recipe r = this.recipeDAO.getRecipeById(id);
        return r;
    }

    public List<Recipe> getRecipeByName(String name){
        List<Recipe> recipes=this.recipeDAO.getRecipeByName(name);
        return recipes;
    }

    public List<Recipe> getRecipeByLike(){
        List<Recipe> recipes=this.recipeDAO.getRecipeByLike();
        return recipes;
    }

    public List<Recipe> getRecipeByUserName(String username){
        List<Recipe> recipes=this.recipeDAO.getRecipeByUserName(username);
        return recipes;
    }

    public List<Recipe> getRecipeByUserId(int userId){
        List<Recipe> recipes=this.recipeDAO.getRecipeByUserId(userId);
        return recipes;
    }

    public List<Recipe> getRecipeByTag(String tagName){
        List<Recipe> recipes=this.recipeDAO.getRecipeByTag(tagName);
        return recipes;
    }

    public void addNewCollection() {

    }

    public int countUser() {
        return recipeDAO.getRecipeNum();
    }

    public int countRecipeStep(Recipe recipe) {
        return rsDAO.getRecipeStepNum(recipe);
    }
}
