package com.healthykitchen.springboot.controller;

/**
 * @className:
 * @description: 登录&登出&注册
 * @author: anonym_co
 * @date: 13:17 2019/11/13
 * @version: v1.0
 */

import com.healthykitchen.springboot.pojo.User;
import com.healthykitchen.springboot.result.Result;
import com.healthykitchen.springboot.result.ResultFactory;
import com.healthykitchen.springboot.service.UserService;
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
    UserService userService;

    @PostMapping(value = "/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser, HttpServletRequest request) {
        String username = requestUser.getUsername();
        // 将html格式的username转为非html格式
        username = HtmlUtils.htmlEscape(username);
        int userId=userService.getuserIdByUsername(username);

        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setTimeout(10000);
        UsernamePasswordToken token = new UsernamePasswordToken(username, requestUser.getPassword());
        token.setRememberMe(true);
        try {
            subject.login(token);
            // 生成随机 token 并存储在 session 中


            //在session中存储userId
            return ResultFactory.buildSuccessResult(token);
        } catch (UnknownAccountException uae) {
            String message = "没有此用户";
            return ResultFactory.buildFailResult(message);
        } catch (IncorrectCredentialsException ice) {
            String message = "输入密码有误";
            return ResultFactory.buildFailResult(message);
        }
    }

//    @PostMapping("api/register")
//    @ResponseBody
//    public Result register(@RequestBody User user) {
//        String username = user.getUsername();
//        String password = user.getPassword();
//        username = HtmlUtils.htmlEscape(username);
//        user.setUsername(username);
//
//        boolean exist = userService.isExist(username);
//
//        if (exist) {
//            String message = "用户名已被使用";
//            return ResultFactory.buildFailResult(message);
//        }
//
//        // 默认生成 16 位盐
//        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
//        int times = 2;
//        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
//
//        user.setPassword(encodedPassword);
//        userService.add(user);
//
//        return ResultFactory.buildSuccessResult(user);
//    }

    /**
     * 注册：后续GetMapping应该改成PostMapping，现在有bug
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

//        // 默认生成 16 位盐
//        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
//        int times = 2;
//        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
//
//        user.setPassword(encodedPassword);
        userService.add(user);

        return ResultFactory.buildSuccessResult(user);
    }
    @ResponseBody
    @GetMapping("api/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        String message = "成功登出";
        return ResultFactory.buildSuccessResult(message);
    }

    @ResponseBody
    @GetMapping(value = "api/authentication")
    public String authentication(){
        return "身份认证成功";
    }
}

