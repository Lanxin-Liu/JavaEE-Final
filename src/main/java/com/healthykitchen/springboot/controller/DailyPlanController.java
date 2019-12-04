package com.healthykitchen.springboot.controller;

import com.healthykitchen.springboot.pojo.DailyPlan;
import com.healthykitchen.springboot.result.Result;
import com.healthykitchen.springboot.result.ResultFactory;
import com.healthykitchen.springboot.service.DailyPlanService;
import com.healthykitchen.springboot.service.TagService;
import com.healthykitchen.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class DailyPlanController {
    @Autowired
    private UserService userService;

    @Autowired
    private DailyPlanService dailyPlanService;

    @Autowired
    private TagService tagService;

    /**
     * 获取用户的DP
     * @param userId
     * @return
     */
    @GetMapping("api/mydailyplan")
    @ResponseBody
    List<DailyPlan> getDailyPlan(int userId){
        List<DailyPlan> dailyPlans;
        dailyPlans=dailyPlanService.getUserDailyPlanById(userId);
        return dailyPlans;
    }

    /**
     * 添加每日计划API
     * @param DPContent
     * @param DPTime
     * @param DPTag
     * @param DPDate
     * @param DPCalorie
     * @param DPImage
     * @param DPUserId
     * @return
     */
    @GetMapping("api/addDP")
    @ResponseBody
    public Result addDailyPlan(String DPContent, String DPTime, String DPTag, String DPDate, int DPCalorie, byte[] DPImage, int DPUserId){


        try {
            DailyPlan dailyPlan = new DailyPlan();

            dailyPlan.setDPContent(DPContent);
            dailyPlan.setDPDate(DPDate);
            dailyPlan.setDPCalorie(DPCalorie);
            dailyPlan.setDPImage(DPImage);
            dailyPlan.setDPUserId(DPUserId);
            dailyPlan.setDPTime(DPTime);
            dailyPlan.setDPTag(DPTag);
            dailyPlanService.addDailyPlan(dailyPlan);

            return ResultFactory.buildSuccessResult(dailyPlan);
        } catch (Exception e){
            return ResultFactory.buildFailResult("插入信息失败！");
        }
    }

    /**
     * 删除每日计划
     * @param dailyPlan
     * @return
     */
//    @GetMapping("api/deleteDP")
//    @ResponseBody
//    public Result deleteDailyPlan(DailyPlan dailyPlan){
//        try {
//            int DPId = dailyPlan.getDPId();
//            dailyPlanService.deleteDailyPlan(DPId);
//            return ResultFactory.buildSuccessResult(dailyPlan);
//        } catch (Exception e)
//        {
//            return  ResultFactory.buildFailResult("删除每日计划失败");
//        }
//
//    }


}
