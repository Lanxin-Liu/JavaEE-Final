package com.healthykitchen.springboot.service;

import com.healthykitchen.springboot.dao.CollectionDAO;
import com.healthykitchen.springboot.dao.LikeDAO;
import com.healthykitchen.springboot.pojo.Collection;
import com.healthykitchen.springboot.pojo.Like;
import com.healthykitchen.springboot.pojo.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public void addLikeToRecipe(Recipe recipe, int uId) {
        Like like = new Like();
        like.setRecipeId(recipe.getRecipeId());
        like.setTime(new Date());
        like.setUserId(uId);
        likeDAO.insertLike(like);
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
}
