package com.healthykitchen.springboot.dao;

import com.healthykitchen.springboot.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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

    @Select("select * from User_info where user_name = #{username}")
    User findByUsername(String username);

    @Insert("insert into User_info (user_name, password)values(#{username},#{password})")
    void addUser(User user);

}
