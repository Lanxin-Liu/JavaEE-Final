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
import org.springframework.web.bind.annotation.*;
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
     * 根据用户名字搜索用户
     * @param name
     * @return
     */
    @RequestMapping(value="/userInfoByName",method = RequestMethod.POST)
    public User getUserInfoByName(@RequestParam("userName") String name){
        User user= userService.getByUsername(name);
        return  user;
    }


    /**
     * 修改用户密码
     * @param password
     * @param httpSession
     * @return
     */
    @RequestMapping(value="/updateUserPassword",method = RequestMethod.POST)
    public Result updateUserPassword(@RequestParam("password") String password,HttpSession httpSession){
        User user = (User) httpSession.getAttribute("User");
        int uId=user.getId();
        try{
            user.setPassword(password);
            userService.updateUserInfo(user);
            return ResultFactory.buildSuccessResult(user);
        } catch (Exception e)
        {
            return ResultFactory.buildFailResult("更改密码失败！");
        }
//        if (exist) {
//            User user=new User();
//            user=userService.getuserInfoById(userId);
//            user.setPassword(password);
//            userService.updateUserInfo(user);
//            return ResultFactory.buildSuccessResult(user);
//        }
//        else
//            return ResultFactory.buildFailResult("更改个人密码失败！");
    }

    /**
     * 修改用户个人简介
     * @param intro
     * @param httpSession
     * @return
     */
    @RequestMapping(value="/updateUserInfo",method = RequestMethod.POST)
    public Result updateUserIntro(@RequestParam("intro")String intro,HttpSession httpSession){
        User user=(User)httpSession.getAttribute("User");
        try{
            user.setIntro(intro);
            userService.updateUserInfo(user);
            return ResultFactory.buildSuccessResult(user);
        } catch (Exception e)
        {
            return ResultFactory.buildFailResult("修改密码失败！");
        }
    }
//    @GetMapping("/updateUserIntro")
//    @ResponseBody
//    public Result updateUserIntro(String intro,int userId){
//        boolean exist=userService.existById(userId);
//        if (exist) {
//            User user=new User();
//            user=userService.getuserInfoById(userId);
//            user.setIntro(intro);
//            userService.updateUserInfo(user);
//            return ResultFactory.buildSuccessResult(user);
//        }
//        else
//            return ResultFactory.buildFailResult("更新个人简介失败！");
//    }

    /**
     * 修改用户名
     * @param name
     * @param httpSession
     * @return
     */
    @RequestMapping(value="/updateUserName",method = RequestMethod.POST)
    public Result updateUserName(@RequestParam("userName")String name,HttpSession httpSession){
        User user=(User)httpSession.getAttribute("User");
        boolean exits=userService.isExist(name);
        if (exits)
            return ResultFactory.buildFailResult("用户名已存在，修改失败！");
        else {
            try {
                user.setUsername(name);
                userService.updateUserInfo(user);
                return ResultFactory.buildSuccessResult(user);
            } catch (Exception e) {
                return ResultFactory.buildFailResult("修改用户名失败！");
            }
        }
    }
//    @GetMapping("/updateUserName")
//    @ResponseBody
//    public Result updateUserName(String name,int userId){
//        boolean exist=userService.existById(userId);
//        if (exist) {
//            User user=new User();
//            user=userService.getuserInfoById(userId);
//            user.setUsername(name);
//            userService.updateUserInfo(user);
//            return ResultFactory.buildSuccessResult(user);
//        }
//        else
//            return ResultFactory.buildFailResult("更改个人名字失败！");
//    }

    @RequestMapping(value="/updateUserGender",method = RequestMethod.POST)
    public Result updateUserGender(@RequestParam("gender")String gender,HttpSession httpSession){
        User user=(User)httpSession.getAttribute("User");
        try{
            user.setGender(gender);
            userService.updateUserInfo(user);
            return ResultFactory.buildSuccessResult(user);
        } catch (Exception e)
        {
            return ResultFactory.buildFailResult("修改性别失败！");
        }
    }
//    @GetMapping("/updateUserGender")
//    @ResponseBody
//    public Result updateUserGender(String gender,int userId){
//        boolean exist=userService.existById(userId);
//        if (exist) {
//            User user=new User();
//            user=userService.getuserInfoById(userId);
//            user.setGender(gender);
//            userService.updateUserInfo(user);
//            return ResultFactory.buildSuccessResult(user);
//        }
//        else
//            return ResultFactory.buildFailResult("修改用户性别失败！");
//    }

//    @GetMapping("/updateUserImage")
//    @ResponseBody
//    public Result updateUserGender(byte[] image,int userId){
//        boolean exist=userService.existById(userId);
//        if (exist) {
//            User user=new User();
//            user=userService.getuserInfoById(userId);
//            user.setImage(image);
//            userService.updateUserInfo(user);
//            return ResultFactory.buildSuccessResult(user);
//        }
//        else
//            return ResultFactory.buildFailResult("修改用户头像失败！");
//    }

    

}
