package com.healthykitchen.springboot.controller;

import com.healthykitchen.springboot.pojo.Follow;
import com.healthykitchen.springboot.pojo.User;
import com.healthykitchen.springboot.result.Result;
import com.healthykitchen.springboot.result.ResultFactory;
import com.healthykitchen.springboot.service.FollowService;
import com.healthykitchen.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * @param userId
     * @return
     */
    @GetMapping("/followedlist")
    @ResponseBody
    public List<User> getFollowedList(int userId){
        List<User> users=followService.getuserFollowed(userId);
        return users;
    }

    /**
     * 用户进行关注
     * @param followedUserId【本用户】
     * @param followingUserId【本用户关注的用户】
     * @return
     */
    @GetMapping("/follow")
    @ResponseBody
    public Result follow(@RequestParam(value = "followedUserId",required = true) int followedUserId, @RequestParam(value = "followingUserId",required = true) int followingUserId) {
        //ResultFactory resultFactory=new ResultFactory();
        boolean exist=userService.existById(followingUserId);
        if(exist) {
            Follow follow = new Follow();
            String followTime = "2019-11-12 12:00:00";//获取时间的函数忘记了，先用这个
            follow.setFollowedUserId(followedUserId);
            follow.setFollowingUserId(followingUserId);
            follow.setFollowTime(followTime);
            followService.addFollow(follow);
            followService.updateFollowing(followedUserId);
            followService.updateFollowed(followingUserId);
            return ResultFactory.buildSuccessResult(follow);
        }
        else{
            return ResultFactory.buildFailResult("要关注的用户不存在");
        }
    }

    /**
     * 用户进行取关
     * @param followedUserId
     * @param followingUserId
     * @return
     */
    @GetMapping("/unfollow")
    @ResponseBody
    public Result unfollow(@RequestParam(value = "followedUserId",required = true) int followedUserId, @RequestParam(value = "followingUserId",required = true) int followingUserId) {
        //ResultFactory resultFactory=new ResultFactory();
        boolean exist=userService.existById(followingUserId);
        if(exist) {
            String time="2019-12-11 12:00:12";
            Follow follow=new Follow();
            follow.setFollowedUserId(followedUserId);
            follow.setFollowingUserId(followingUserId);
            followService.unfollow(followedUserId,followingUserId);
            followService.updateunFollowed(followingUserId);
            followService.updateunFollowing(followedUserId);
            return ResultFactory.buildSuccessResult(follow);
        }
        else{
            return ResultFactory.buildFailResult("要取关的用户不存在");
        }
    }
}
