package com.healthykitchen.springboot.dao;


import com.healthykitchen.springboot.pojo.DailyPlan;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DPDao {
    @Select("select * from DailyPlan where DP_user_id=#{userId} order by DP_time desc")
    List<DailyPlan> getUserDailyPlanById(int userId);

    @Insert("insert into DailyPlan(DP_content,DP_time,DP_tag,DP_date,DP_calorie,DP_user_id,DP_image) values (#{DPContent},#{DPTime},#{DPTag},#{DPDate},#{DPCalorie},#{DPUserId},#{DPImage})")
    void addDailyPlan(DailyPlan dailyPlan);

    @Delete("delete from DailyPlan where DP_user_id=#{DPUserId} and DP_id=#{DPId}")
    void deleteDailyPlan(int DPId);

}
