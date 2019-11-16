package com.healthykitchen.springboot.service;

import com.healthykitchen.springboot.dao.UserDAO;
import com.healthykitchen.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: UserService
 * @description:
 * @author: anonym_co
 * @date: 14:05 2019/11/13
 * @version: v1.0
 */

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public boolean isExist(String username) {
        User user = getByUsername(username);
        return null!=user;
    }

    public User getByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public void add(User user) {
        userDAO.save(user);
    }
}
