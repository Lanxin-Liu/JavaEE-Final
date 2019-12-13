package com.healthykitchen.springboot.controller;

/**
 * @className:
 * @description: 登录&登出&注册
 * @author: anonym_co
 * @date: 13:17 2019/11/13
 * @version: v1.0
 */

import ch.qos.logback.core.boolex.EvaluationException;
import com.healthykitchen.springboot.dao.UserDAO;
import com.healthykitchen.springboot.pojo.User;
import com.healthykitchen.springboot.result.Result;
import com.healthykitchen.springboot.result.ResultFactory;
import com.healthykitchen.springboot.service.UserService;
import com.healthykitchen.springboot.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDAO userDAO;

    @PostMapping(value = "api/login")
    @ResponseBody
//    @RequestMapping(value="/login", method = RequestMethod.POST)
    public Result login(@RequestBody User user,HttpServletRequest request) {
        User user1 = userService.getUserNameandPassword(user.getUsername(),user.getPassword());
        if(user1 != null ) {
             HttpSession session=request.getSession();
             session.setAttribute("User",user1);
//            httpSession.setAttribute("user",user1);
            System.out.println("success!");
            User temp=new User();
            session=request.getSession();
            temp=(User)session.getAttribute("User");
            System.out.print(temp.getUserId()+temp.getUsername());
            return ResultFactory.buildSuccessResult(user1);
        } else {
            return ResultFactory.buildFailResult("登录失败！");
        }
    }


    /**
     * 注册
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping("api/register")
    @ResponseBody
    public Result register(@RequestParam(value = "username",required = true) String username,@RequestParam(value = "password",required = true) String password) {
        User user = new User(username, password);
        username = HtmlUtils.htmlEscape(username);
        boolean exist = userService.isExist(username);

        if (exist) {
            String message = "用户名已被使用";
            return ResultFactory.buildFailResult(message);
        }

        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");

        user.setUserId(userService.countUser());
        user.setPassword(passwordMD5);
        userService.add(user);

        return ResultFactory.buildSuccessResult(user);
    }


    @GetMapping("api/logout")
    @ResponseBody
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        return "api/login";
    }

}

