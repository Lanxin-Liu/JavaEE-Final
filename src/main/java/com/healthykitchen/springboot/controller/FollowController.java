package com.healthykitchen.springboot.controller;

import com.healthykitchen.springboot.pojo.Follow;
import com.healthykitchen.springboot.pojo.User;
import com.healthykitchen.springboot.result.Result;
import com.healthykitchen.springboot.result.ResultFactory;
import com.healthykitchen.springboot.service.FollowService;
import com.healthykitchen.springboot.service.UserService;
import com.healthykitchen.springboot.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.List;

@Controller
public class FollowController {

    @Autowired
    private FollowService followService;
    @Autowired
    private UserService userService;

    /**
     * 获取关注列表
     * @param userId
     * @return
     */
    @GetMapping("/followinglist")
    @ResponseBody
    public List<User> getFollowingList(int userId){
        List<User> users=followService.getuserFollowing(userId);
        return users;
    }

    /**
     * 获取用户粉丝列表
     * @param httpSession
     * @return
     */
    @GetMapping("/followedlist")
    @ResponseBody
    public List<User> getFollowedList(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        int uId;
        uId = user.getId();
        List<User> users=followService.getuserFollowed(uId);
        return users;
    }

    /**
     * 用户进行关注
     * @param httpSession
     * @param fuser
     * @return
     */
    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    public Result follow(HttpSession httpSession, @RequestParam("User") User fuser) {
        //ResultFactory resultFactory=new ResultFactory();
        int followingUserId=fuser.getId();
        boolean exist=userService.existById(followingUserId);
        if(exist) {
            User user = (User) httpSession.getAttribute("user");
            int uId;
            uId = user.getId();
            Follow follow = new Follow();
            DateUtil date=new DateUtil();
            follow.setFollowedUserId(uId);
            follow.setFollowingUserId(followingUserId);
            follow.setFollowTime(date.getTime());
            followService.addFollow(follow);
            followService.updateFollowing(uId);
            followService.updateFollowed(followingUserId);
            return ResultFactory.buildSuccessResult(follow);
        }
        else{
            return ResultFactory.buildFailResult("要关注的用户不存在");
        }
    }

    /**
     * 用户进行取关
     * @param httpSession
     * @param unfuser
     * @return
     */
    @RequestMapping(value = "/unfollow", method = RequestMethod.POST)
    public Result unfollow(HttpSession httpSession, @RequestParam("User") User unfuser) {
        //ResultFactory resultFactory=new ResultFactory();
        int followingUserId=unfuser.getId();
        User user = (User) httpSession.getAttribute("user");
        int uId;
        uId = user.getId();
        boolean exist=userService.existById(followingUserId);
        if(exist) {
            DateUtil date=new DateUtil();
            Follow follow=new Follow();
            follow.setFollowTime(date.getTime());
            follow.setFollowedUserId(uId);
            follow.setFollowingUserId(followingUserId);
            followService.unfollow(uId,followingUserId);
            followService.updateunFollowed(followingUserId);
            followService.updateunFollowing(uId);
            return ResultFactory.buildSuccessResult(follow);
        }
        else{
            return ResultFactory.buildFailResult("要取关的用户不存在");
        }
    }
}
