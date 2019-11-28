package com.healthykitchen.springboot.controller;

import com.healthykitchen.springboot.dao.RecipeDAO;
import com.healthykitchen.springboot.dao.UserDAO;
import com.healthykitchen.springboot.pojo.Recipe;
import com.healthykitchen.springboot.pojo.User;
import com.healthykitchen.springboot.service.FollowService;
import com.healthykitchen.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @className:
 * @description: 打印当前用户列表
 * @author: anonym_co
 * @date: 13:15 2019/11/13
 * @version: v1.0
 */
@Controller
public class UserController {

    @Autowired
    private UserDAO userdao;
    @Autowired
    private UserService userService;
    @Autowired
    private RecipeDAO recipeDAO;


    @GetMapping("/userlist")
    @ResponseBody
    public List<String> getUserList() {
        List<User> userlist = userdao.getUserlist();
        List<String> username =new ArrayList<String>();

        for(User u:userlist){
            username.add(u.getUsername());
        }
        return username;
    }

    @GetMapping("/userInfoByName")
    @ResponseBody
    public List<User> getUserInfoByName(String name){
        List<User> users= userService.getByUsername(name);
        return  users;
    }

//    @GetMapping("/myrecipes")
//    @ResponseBody
//    public List<Recipe> getUserRecipes(HttpSession request){
//        List<Recipe> recipes=recipeDAO.getRecipeByUserId(usrId);
//    }

//    @GetMapping("/followinglist")
//    @ResponseBody
//    public List<User> getFollowingList(int userId){
//        int[] users=userService.getuserFollowing(userId);
//        List<User> userList=new ArrayList<>();
//        for(int i=0;i<users.length;i++){
//            User u = new User();
//            u=userService.getuserInfoById(users[i]);
//            userList.add(u);
//        }
//        return userList;
//    }



}