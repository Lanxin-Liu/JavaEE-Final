package com.healthykitchen.springboot.dao;

import com.healthykitchen.springboot.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;


/**
 * @className: UserDAO
 * @description:
 * @author: anonym_co
 * @date: 13:13 2019/11/13
 * @version: v1.0
 */
@Repository
public interface UserDAO {

    @Select("select user_name,password from User_info")
    List<User> getUserlist();

    @Select("select user_id,user_name,password,image,intro,gender,followed_num,following_num from User_info where user_id=#{userId}")
    User getuserInfoById(int userId);

    @Select("select * from User_info where user_name = #{userName}")
    List<User> findByUsername(String userName);

    @Insert("insert into User_info (user_name, password)values(#{username},#{password})")
    void addUser(User user);

    @Select("select user_id from User_info where user_name=#{username}")
    int getuserIdByUsername(String username);

    @Update("UPDATE User_info  SET intro = #{intro}   WHERE user_id=#{userId};")
    void updateUserIntro(int userId,String intro);

    //查看A的关注列表
//    @Select("SELECT following_user_id FROM Follow where followed_user_id=1;")
//    int[] getuserFollowing(int userId);


}
