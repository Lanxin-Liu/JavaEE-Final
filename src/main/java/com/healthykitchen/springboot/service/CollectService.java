package com.healthykitchen.springboot.service;

import com.healthykitchen.springboot.dao.CollectionDAO;
import com.healthykitchen.springboot.dao.LikeDAO;
import com.healthykitchen.springboot.pojo.Collection;
import com.healthykitchen.springboot.pojo.Like;
import com.healthykitchen.springboot.pojo.Recipe;
import com.healthykitchen.springboot.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 10:03 2019/12/03
 * @version: v1.0
 */
@Service
public class CollectService {
    @Autowired
    private CollectionDAO collectionDAO;
    @Autowired
    private LikeDAO likeDAO;
    @Autowired
    private  RecipeService recipeService;

    public CollectionDAO getCollectionDAO() {
        return collectionDAO;
    }

    public void setCollectionDAO(CollectionDAO collectionDAO) {
        this.collectionDAO = collectionDAO;
    }

    public LikeDAO getLikeDAO() {
        return likeDAO;
    }

    public void setLikeDAO(LikeDAO likeDAO) {
        this.likeDAO = likeDAO;
    }

    /**
     * 添加收藏夹
     */
    public void createCollection(Collection collection) {
        collectionDAO.addCollection(collection);
    }
    /**
     * 添加like
     */
    public void addLikeToRecipe(Like like) {
        likeDAO.insertLike(like);
    }

    public List<Recipe> getMyCollection(int UserId){
        int[] recipeid=collectionDAO.getMyCollection(UserId);
        List<Recipe> recipes=new ArrayList<>();
        for (int i=0;i<recipeid.length;i++){
            Recipe recipe=new Recipe();
            recipe=recipeService.getRecipeById(recipeid[i]);
            recipes.add(recipe);
        }
        return recipes;
    }

    /**
     * 判断该收藏夹是否已存在
     */
    public boolean ifExist(int id, String name ) {
        Collection c = collectionDAO.getCollectionByUserIdAndName(id, name);
        if(c == null) {
            return false;
        } else return true;
    }

    public void deleteLike(int recipeId, int userId) {
        likeDAO.deleteLike(recipeId, userId);
    }
}
