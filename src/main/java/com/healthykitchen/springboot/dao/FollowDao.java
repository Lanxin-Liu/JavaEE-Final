package com.healthykitchen.springboot.dao;

import com.healthykitchen.springboot.pojo.Follow;
import com.healthykitchen.springboot.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowDao {

    /**
     * 查看A的关注列表
     * @param userId
     * @return
     */
    @Select("select * from User_info where user_id in ( select  following_user_id FROM Follow where followed_user_id=#{userId});")
    List<User> getuserFollowingById(int userId);

    /**
     * 查看A的粉丝列表
     */
    @Select("select * from User_info where user_id in ( select  followed_user_id FROM Follow where following_user_id=#{userId});")
    List<User> getuserFollowedById(int userId);

    @Insert("insert into Follow (followed_user_id, following_user_id,follow_time)values(#{followedUserId},#{followingUserId},#{followTime})")
    void userfollow(Follow follow);

    @Delete("DELETE FROM Follow WHERE followed_user_id=#{followedUserId} and following_user_id=#{followingUserId};")
    void unfollow(int followedUserId,int followingUserId);

    @Update("update User_info SET following_num=following_num-1 where user_id=#{userId};")
    void updateunFollowing(int userId);

    @Update("update User_info SET followed_num=followed_num-1 where user_id=#{userId};")
    void updateunFollowed(int userId);

    //给本用户的用户关注数加1
    @Update("update User_info SET following_num=following_num+1 where user_id=#{userId};")
    void updateFollowing(int userId);

    //给被关注用户的用户粉丝数加1
    @Update("update User_info SET followed_num=followed_num+1 where user_id=#{userId};")
    void updateFollowed(int userId);


}
