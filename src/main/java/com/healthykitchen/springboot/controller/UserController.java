package com.healthykitchen.springboot.controller;

import com.healthykitchen.springboot.dao.UserDAO;
import com.healthykitchen.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @className:
 * @description:
 * @author: anonym_co
 * @date: 13:15 2019/11/13
 * @version: v1.0
 */
@Controller
public class UserController {

    @Autowired
    private UserDAO userdao;


    @GetMapping("/userlist")
    @ResponseBody
    public Map<String,String> getUserList() {
        List<User> userlist = userdao.findAll();
        Map<String ,String > map = new HashMap<String,String>();
        for (User u:userlist) {
            map.put(u.getUsername(),u.getIntro());
        }
        return map;
    }
}
