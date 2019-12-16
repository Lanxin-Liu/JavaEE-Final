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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class FollowController {

    @Autowired
    private FollowService followService;
    @Autowired
    private UserService userService;

    /**
     * 获取关注列表
     *
     * @return
     */
    @GetMapping("api/followinglist")
    @ResponseBody
    public List<User> getFollowingList(@RequestParam(value = "userId")int userId){
        User user=userService.getuserInfoById(userId);
        List<User> users=followService.getuserFollowing(user.getUserId());
        return users;
    }

    /**
     * 获取用户粉丝列表
     * @param httpSession
     * @return
     */
    @GetMapping("api/followedlist")
    @ResponseBody
    public List<User> getFollowedList(@RequestParam(value = "userId")int userId){
        User user=userService.getuserInfoById(userId);
        List<User> users=followService.getuserFollowed(user.getUserId());
        return users;
    }

    /**
     * 用户进行关注
     * @param httpSession
     * @param followingUser【本用户关注的用户】
     * @return
     */
    @PostMapping("api/follow")
    @ResponseBody
    public Result follow(@RequestParam(value = "followingUserId")int followingUserId,@RequestParam(value = "userId")int userId) {
        //ResultFactory resultFactory=new ResultFactory();
        User user=userService.getuserInfoById(userId);
        User followingUser=userService.getuserInfoById(followingUserId);
        //boolean exist=userService.existById(followingUserId);
        try {
            DateUtil dateUtil=new DateUtil();
            Follow follow = new Follow();
            String followTime = dateUtil.getTime();
            follow.setFollowedUserId(user.getUserId());
            follow.setFollowingUserId(followingUser.getUserId());
            follow.setFollowTime(followTime);
            followService.addFollow(follow);
            followService.updateFollowing(user.getUserId());
            followService.updateFollowed(followingUser.getUserId());
            return ResultFactory.buildSuccessResult(follow);
        } catch (Exception e)
        {
            return ResultFactory.buildFailResult("要关注的用户不存在");
        }
    }


    /**
     * 用户进行取关
     * @param followingUserId
     * @param userId
     * @return
     */
    @PostMapping("/unfollow")
    @ResponseBody
    public Result unfollow(@RequestParam(value = "followingUserId")int followingUserId,@RequestParam(value = "userId")int userId) {
        //ResultFactory resultFactory=new ResultFactory();
//        User user=(User)httpSession.getAttribute("User");
        User user=userService.getuserInfoById(userId);
        User followingUser=userService.getuserInfoById(followingUserId);
        try {
            String time="2019-12-11 12:00:12";
            Follow follow=new Follow();
            follow.setFollowedUserId(user.getUserId());
            follow.setFollowingUserId(followingUser.getUserId());
            followService.unfollow(user.getUserId(),followingUser.getUserId());
            followService.updateunFollowed(followingUser.getUserId());
            followService.updateunFollowing(user.getUserId());
            return ResultFactory.buildSuccessResult(follow);
        }
        catch (Exception e){
            return ResultFactory.buildFailResult("要取关的用户不存在");
        }
    }
}
