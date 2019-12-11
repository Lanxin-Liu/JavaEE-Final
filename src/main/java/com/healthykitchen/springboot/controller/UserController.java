package com.healthykitchen.springboot.controller;

import com.healthykitchen.springboot.dao.RecipeDAO;
import com.healthykitchen.springboot.dao.UserDAO;
import com.healthykitchen.springboot.pojo.Recipe;
import com.healthykitchen.springboot.pojo.User;
import com.healthykitchen.springboot.result.Result;
import com.healthykitchen.springboot.result.ResultFactory;
import com.healthykitchen.springboot.service.FollowService;
import com.healthykitchen.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
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

    /**
     * 获取用户信息
     * @param name
     * @return
     */
    @GetMapping("/userInfoByName")
    @ResponseBody
    public User getUserInfoByName(String name){
        User users= userService.getByUsername(name);
        return  users;
    }

    @GetMapping("/updateUserPassword")
    @ResponseBody
    public Result updateUserPassword(String password,int userId){
        boolean exist=userService.existById(userId);
        if (exist) {
            User user=new User();
            user=userService.getuserInfoById(userId);
            user.setPassword(password);
            userService.updateUserInfo(user);
            return ResultFactory.buildSuccessResult(user);
        }
        else
            return ResultFactory.buildFailResult("更改个人密码失败！");
    }

    @GetMapping("/updateUserIntro")
    @ResponseBody
    public Result updateUserIntro(String intro,int userId){
        boolean exist=userService.existById(userId);
        if (exist) {
            User user=new User();
            user=userService.getuserInfoById(userId);
            user.setIntro(intro);
            userService.updateUserInfo(user);
            return ResultFactory.buildSuccessResult(user);
        }
        else
            return ResultFactory.buildFailResult("更新个人简介失败！");
    }

    @GetMapping("/updateUserName")
    @ResponseBody
    public Result updateUserName(String name,int userId){
        boolean exist=userService.existById(userId);
        if (exist) {
            User user=new User();
            user=userService.getuserInfoById(userId);
            user.setUsername(name);
            userService.updateUserInfo(user);
            return ResultFactory.buildSuccessResult(user);
        }
        else
            return ResultFactory.buildFailResult("更改个人名字失败！");
    }

    @GetMapping("/updateUserGender")
    @ResponseBody
    public Result updateUserGender(String gender,int userId){
        boolean exist=userService.existById(userId);
        if (exist) {
            User user=new User();
            user=userService.getuserInfoById(userId);
            user.setGender(gender);
            userService.updateUserInfo(user);
            return ResultFactory.buildSuccessResult(user);
        }
        else
            return ResultFactory.buildFailResult("修改用户性别失败！");
    }

    @GetMapping("/updateUserImage")
    @ResponseBody
    public Result updateUserGender(byte[] image,int userId){
        boolean exist=userService.existById(userId);
        if (exist) {
            User user=new User();
            user=userService.getuserInfoById(userId);
            user.setImage(image);
            userService.updateUserInfo(user);
            return ResultFactory.buildSuccessResult(user);
        }
        else
            return ResultFactory.buildFailResult("修改用户头像失败！");
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
