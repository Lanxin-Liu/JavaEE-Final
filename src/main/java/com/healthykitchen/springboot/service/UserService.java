package com.healthykitchen.springboot.service;

import com.healthykitchen.springboot.dao.UserDAO;
import com.healthykitchen.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: UserService
 * @description: 用户登录/用户注册/用户关注(未实现)/收藏菜谱
 * @author: anonym_co
 * @date: 14:05 2019/11/13
 * @version: v1.0
 */

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public boolean isExist(String username) {
        List<User> users = getByUsername(username);
        return null!=users;
    }

    public List<User> getByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public void add(User user) {
        userDAO.addUser(user);
    }
}
