package com.healthykitchen.springboot.service;

import com.healthykitchen.springboot.dao.FollowDao;
import com.healthykitchen.springboot.pojo.Follow;
import com.healthykitchen.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {
    @Autowired
    FollowDao followDao;

    /**
     * 查看用户关注列表
     */
    public List<User> getuserFollowing(int userId){
        return followDao.getuserFollowingById(userId);
    }

    //查看用户粉丝列表

    /**
     * 查看用户粉丝列表
     * @param userId
     * @return
     */
    public List<User> getuserFollowed(int userId){
        return followDao.getuserFollowedById(userId);
    }

    /**
     * 添加关注记录
     * @param follow
     */
    public void addFollow(Follow follow){
        followDao.userfollow(follow);
    }

    public void updateFollowed(int userId){
        followDao.updateFollowed(userId);
    }

    public void updateFollowing(int userId){
        followDao.updateFollowing(userId);
    }

    public void unfollow(int followedUserId,int followingUserId){
        followDao.unfollow(followedUserId,followingUserId);
    }

    public void updateunFollowing(int userId){
        followDao.updateunFollowing(userId);
    }

    public void updateunFollowed(int userId){
        followDao.updateunFollowed(userId);
    }
}
