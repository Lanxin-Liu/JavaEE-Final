package com.healthykitchen.springboot.service;

import com.healthykitchen.springboot.dao.UserDAO;
import com.healthykitchen.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: UserService
 * @description: 用户登录/用户注册/用户关注(未实现)/收藏菜谱
 * @author: anonym_co
 * @date: 14:05 2019/11/13
 * @version: v1.0
 */

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public User getUserNameandPassword(String username,String password){
        return userDAO.getByNameAndPasswd(username,password);
    }

    public boolean isExist(String username) {
        List<User> users = getByUsername(username);
        return null!=users;
    }

    public boolean existById(int id){
        User user=new User();
        user=userDAO.getuserInfoById(id);
        return null!=user;
    }
    public List<User> getByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public void add(User user) {
        userDAO.addUser(user);
    }


    /**
     * 在session中获取到登录的username，然后通过username得到userID,用于获取用户的所有信息。
     * @param username
     * @return
     */
    public int getuserIdByUsername(String username){
        int userId = userDAO.getuserIdByUsername(username);
        return userId;
    }

    public User getuserInfoById(int userId){
        User user=userDAO.getuserInfoById(userId);
        return user;
    }

    public void updateUserInfo(User user){
        userDAO.updateUserInfo(user);
    }

//    public List<User> getuserFollowing(int userId){
//        int[] followingList = userDAO.getuserFollowing(userId);
//        List<User> users = null;
//        int n = followingList.length;
//        for (int i=0;i<n;i++){
//            User user= new User();
//            user=getuserInfoById(followingList[i]);
//            users.add(user);
//        }
//        return users;
//    }

}
