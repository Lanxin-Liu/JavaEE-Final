package com.healthykitchen.springboot.dao;

import com.healthykitchen.springboot.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @className: UserDAO
 * @description:
 * @author: anonym_co
 * @date: 13:13 2019/11/13
 * @version: v1.0
 */
@Repository
public interface UserDAO extends JpaRepository<User,Integer>{

    User findByUsernameAndPassword(String username,String password);

    User findByUsername(String username);

}
