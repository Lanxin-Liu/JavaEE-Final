package com.healthykitchen.springboot.dao;


import com.healthykitchen.springboot.pojo.DailyPlan;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DPDao {
    @Select("select * from DailyPlan where DP_user_id=#{userId} order by DP_date desc")
    List<DailyPlan> getUserDailyPlanById(int userId);

    @Insert("insert into DailyPlan(DP_date,DP_calorie,DP_user_id,DP_recipe_id) values (#{DPDate},#{DPCalorie},#{DPUserId},#{DPRecipeId})")
    void addDailyPlan(DailyPlan dailyPlan);

    @Delete("delete from DailyPlan where DP_id=#{DPId}")
    void deleteDailyPlan(int DPId);

    @Select("select count(DP_id) from DailyPlan")
    int getDPCount();

    @Select("select * from DailyPlan where DP_user_id=#{userId} and locate(#{date}, DP_date) order by DP_date desc")
    List<DailyPlan> getUserDailyPlanByDate(String date, int userId);




}
