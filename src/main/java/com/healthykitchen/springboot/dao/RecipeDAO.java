package com.healthykitchen.springboot.dao;

import com.healthykitchen.springboot.pojo.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * @className:
 * @description:
 * @author: anonym_co
 * @date: 15:40 2019/11/17
 * @version: v1.0
 */
@Repository
public interface RecipeDAO extends JpaRepository<Recipe, Integer> {

}
