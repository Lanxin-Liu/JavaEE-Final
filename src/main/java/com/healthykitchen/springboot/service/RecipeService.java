package com.healthykitchen.springboot.service;

import com.healthykitchen.springboot.dao.*;
import com.healthykitchen.springboot.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private RecipeStepDAO recipeStepDAO;


    public RecipeDAO getRecipeDAO() {
        return recipeDAO;
    }

//    public void addStep(RecipeContent rs) {
//        rsDAO.addRecipeStep(rs);
//    }

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

    public void addRecipeMaterial(RecipeMaterial recipeMaterials){
//        for (RecipeMaterial rm:recipeMaterials){
////            System.out.println(rm.getRecipeId());
////            System.out.println(rm.getMaterialCount());
////            System.out.println(rm.getMaterialName());
//            RecipeMaterial recipeMaterial=new RecipeMaterial();
//            System.out.println("succ");
//            recipeMaterial.setRmRecipeId(1);
//            recipeMaterial.setRmMaterialCount(1099);
//            recipeMaterial.setRmMaterialName("可乐");
//            System.out.println("succ");
////            recipeMaterialDAO.addRecipeMaterial(rm.getRmRecipeId(),rm.getRmMaterialName(),rm.getRmMaterialCount());
//            int t1=rm.getRmRecipeId();
//            String t2=rm.getRmMaterialName();
//            int bb=rm.getRmMaterialCount();
//            System.out.println("succ");
////            recipeMaterialDAO.addRecipeMaterial(1,"test22",1);
//            recipeMaterialDAO.addRecipeMaterial(recipeMaterial);
////            recipeMaterialDAO.addRecipeMaterial(1,"test22",1);
//            System.out.println("succ");
//        }
        recipeMaterialDAO.addRecipeMaterial(recipeMaterials);
    }

    public void addRecipetep(int a,int b, String c,String d){
        System.out.println("hi!");
        recipeStepDAO.addRecipeStep(a,b,c,d);
    }

    public int getRecipeCalorie(Recipe recipe){
        int rId;
        rId=recipe.getRecipeId();
        List<RecipeMaterial> rm=new ArrayList<>();
        rm=recipeMaterialDAO.getRecipeMaterial(rId);
        System.out.println("该菜谱卡路里为"+0);
        int totc=0;//总卡路里
        int count=0;
        int ec=0;//单位卡路里
        for (RecipeMaterial i:rm){
            Material m=new Material();
            m=materialDao.getMaterialCalorie(i.getMaterialName());
            System.out.println(m.getCalorie());
            //System.out.println(m.getCalorie()+"aaaaa"+m.getMaterialName());
            ec=m.getCalorie();
            count=i.getMaterialCount();
            //System.out.println(i.getMaterialCount());
            totc=ec*count+totc;
        }
        System.out.println("该菜谱卡路里为"+totc);
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

    public int countRecipeStep(int recipeId) {
        return rsDAO.getRecipeStepNum(recipeId);
    }
}
