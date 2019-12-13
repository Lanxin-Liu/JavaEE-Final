package com.healthykitchen.springboot.controller;

import com.healthykitchen.springboot.dao.*;
import com.healthykitchen.springboot.pojo.*;
import com.healthykitchen.springboot.result.Result;
import com.healthykitchen.springboot.result.ResultFactory;
import com.healthykitchen.springboot.service.CollectService;
import com.healthykitchen.springboot.service.RecipeService;

import com.healthykitchen.springboot.service.TagService;

import com.healthykitchen.springboot.service.UserService;
import com.healthykitchen.springboot.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    @Autowired
    private MaterialDao materialDao;
    @Autowired
    private UserService userService;


    /**
     * 【主页】按时间获取所有菜谱
     * @return
     */
    //获取所有菜谱 按时间排序
    @GetMapping("api/recipelist")
    @ResponseBody
    public List<Recipe> getAllRecipes(){
        List<Recipe> recipes=recipeDAO.getAllRecipes();
        for(Recipe i: recipes ){
            int userId=i.getRecipeUserId();
//            System.out.println(userService.getuserInfoById(userId).getUsername());
            i.setRecipeUsername(userService.getuserInfoById(userId).getUsername());
        }
        return recipes;
    }


    /**
     * 【主页】菜谱排行榜
     * @return
     */
    //根据菜谱热爱程度获取菜谱
//    @CrossOrigin(origins = {"http://0.0.0.0:8080", "null"})
    @PostMapping("api/recipeRank")
    @ResponseBody
    public  List<Recipe> getRecipeByLike(){
        List<Recipe> recipes=this.recipeService.getRecipeByLike();
        return  recipes;
    }

    /**
     * 【搜索】根据用户名字获取菜谱
     * @param username
     * @return
     */
    @PostMapping("api/searchrecipebyuser")
    @ResponseBody
    public List<Recipe> getRecipeByUserName(String username){
        List<Recipe> recipes=this.recipeService.getRecipeByUserName(username);
        return recipes;
    }

    /**
     * 【个人】获取本用户发布的菜谱
     * @param request
     * @return
     */
    @PostMapping("api/userrecipelist")
    @ResponseBody
    public List<Recipe> getRecipeByUserId(HttpServletRequest request) {
        HttpSession session=request.getSession(true) ;
        User user=(User)session.getAttribute("User");
        List<Recipe> recipes=this.recipeService.getRecipeByUserId(user.getId());
        return recipes;
    }

    /**
     * 【菜谱页】获取菜谱原料
     * @param recipeId
     * @return
     */
    @GetMapping("api/recipeMaterial")
    @ResponseBody
    public List<RecipeMaterial> getRecipeMaterial(@RequestParam(value = "recipeId")int recipeId){
        return recipeService.getRecipeMaterial(recipeId);
    }

    /**
     * 【菜谱页】获取菜谱评论
     * @param recipeId
     * @return
     */
    @PostMapping("api/getRecipeComment")
    @ResponseBody
    public List<Comment> getRecipeComment(@RequestParam(value = "recipeId") int recipeId){
        return recipeService.getRecipeComment(recipeId);
    }

    /**
     * 【菜谱页】根据菜谱ID获取菜谱
     * @param recipeId
     * @return
     */
    @PostMapping("api/getRecipebyId")
    @ResponseBody
    public Recipe getRecioeById(@RequestParam(value = "recipeId") int recipeId){
        return recipeService.getRecipeById(recipeId);
    }

    /**
     *【搜索】根据菜谱名字获取菜谱
     * @param tagName
     * @return
     */
    @PostMapping("api/searchrecipebytag")
    @ResponseBody
    public List<Recipe> getRecipeByTag(@RequestParam(value = "tagName") String tagName){
        //int tagId=tagService.getTagId(tagName);
        List<Recipe> recipes=this.recipeService.getRecipeByTag(tagName);
        return recipes;
    }


    @PostMapping
    @ResponseBody
    public int getMaterialCalorie(@RequestParam(value = "materialId") int materialId){
        return 0;
    }

    /**
     * 【菜谱页】点赞菜谱
     * @param rId
     * @param httpSession
     * @return
     */
    @GetMapping("api/like")
    @ResponseBody
    public Result likeRecipe(@RequestParam(value = "recipeId") int rId, HttpSession httpSession) {
        try {
            User user = (User) httpSession.getAttribute("User");
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

    /**
     * 【菜谱页】收藏菜谱
     * @param rId
     * @param cName
     * @param httpSession
     * @return
     */
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

    /**
     * 【个人】添加收藏夹
     * @param cName
     * @param httpSession
     * @return
     */
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
     * 【主页】发布菜谱
     * @param recipe
     * @param httpSession
     * @return
     */
    @GetMapping("api/release")
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


//    @GetMapping("api/test")
//    @ResponseBody
//    public Material getMaterial(String materialName){
//        return materialDao.getMaterialCalorie(materialName);
//    }


    /**
     * 【菜谱页】向菜谱中添加食材
     * @param recipeId
     * @param materialName
     * @param materialCount
     * @return
     */
    @GetMapping("api/addRecipeMaterial")
    @ResponseBody
    public Result addReciepeMaterial(int recipeId,String materialName,int materialCount){
        try{
            List<RecipeMaterial> recipeMaterials1=new ArrayList<>();
            RecipeMaterial temp=new RecipeMaterial();
            temp.setRecipeId(recipeId);
            temp.setMaterialName(materialName);
            temp.setMaterialCount(materialCount);
            recipeMaterials1.add(temp);
            recipeService.addRecipeMaterial(recipeMaterials1);
            return ResultFactory.buildSuccessResult(recipeMaterials1);
        } catch (Exception e)
        {
            return ResultFactory.buildFailResult("添加食材失败！");
        }
    }

    /**
     * 【菜谱页】获取菜谱总卡路里
     * @param recipeId
     * @return
     */
    @GetMapping("api/getRecipeCalorie")
    @ResponseBody
    public int getRecipeCalorie(int recipeId){
        Recipe recipe=recipeService.getRecipeById(recipeId);
        return recipeService.getRecipeCalorie(recipe);
    }

    /**
     * 【菜谱页】根据材料名字获取卡路里
     * @param materialName
     * @return
     */
    @PostMapping("api/getMaterialCalorie")
    @ResponseBody
    public int getMaterialCalorie(@RequestParam(value = "materialName") String materialName){
        return materialDao.getMaterialCalorie(materialName).getCalorie();
    }


    @GetMapping("api/addStep")
    public void addStepToRecipe(@RequestParam Recipe recipe) {
        RecipeStep rs = new RecipeStep();
        rs.setRecipeId(recipe.getRecipeId());
        recipeService.addStep(rs);
    }

    /**
     * 添加菜谱评论
     * @param rId
     * @param content
     * @param httpSession
     * @return
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
