package com.healthykitchen.springboot.service;

import com.healthykitchen.springboot.dao.DPDao;
import com.healthykitchen.springboot.pojo.DailyPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyPlanService {

    @Autowired
    private DPDao dpDao;

    /**
     * 获取用户的DailyPlan
     * @param userId
     * @return
     */
    public List<DailyPlan> getUserDailyPlanById(int userId){
        List<DailyPlan> dps=dpDao.getUserDailyPlanById(userId);
        return  dps;
    }

    public void addDailyPlan(DailyPlan dailyPlan){
        dpDao.addDailyPlan(dailyPlan);
    }

    public void deleteDailyPlan(int DPId){
        dpDao.deleteDailyPlan(DPId);
    }

    public List<DailyPlan> getUserDailyPlanByDate(String date, int userId) {
        List<DailyPlan> dps = dpDao.getUserDailyPlanByDate(date, userId);
        return dps;
    }


}
