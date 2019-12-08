package com.healthykitchen.springboot.controller;

import com.healthykitchen.springboot.pojo.DailyPlan;
import com.healthykitchen.springboot.pojo.User;
import com.healthykitchen.springboot.result.Result;
import com.healthykitchen.springboot.result.ResultFactory;
import com.healthykitchen.springboot.service.DailyPlanService;
import com.healthykitchen.springboot.service.TagService;
import com.healthykitchen.springboot.service.UserService;
import com.healthykitchen.springboot.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
     * 获取用户每日计划
     * @param httpSession
     * @return
     */
    @GetMapping("api/mydailyplan")
    @ResponseBody
    List<DailyPlan> getDailyPlan(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        int uId;
        uId = user.getId();
        List<DailyPlan> dailyPlans;
        dailyPlans=dailyPlanService.getUserDailyPlanById(uId);
        return dailyPlans;
    }

    /**
     * 发布今日计划
     * @param dailyPlan
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/api/addDP", method = RequestMethod.POST)
    public Result releaseDP(@RequestParam("DailyPlan") DailyPlan dailyPlan, HttpSession httpSession)
    {
        try{
            User user = (User) httpSession.getAttribute("User");
            DateUtil date = new DateUtil();
            int uId = user.getId();
            dailyPlan.setDPDate(date.getTime());
            dailyPlan.setDPUserId(uId);
            dailyPlanService.addDailyPlan(dailyPlan);
            return  ResultFactory.buildSuccessResult(dailyPlan);
        } catch (Exception e)
        {
            return ResultFactory.buildFailResult("发布每日计划失败！");
        }
    }
//    @GetMapping("api/addDP")
//    @ResponseBody
//    public Result addDailyPlan(String DPContent, String DPTime, String DPTag, String DPDate, int DPCalorie, byte[] DPImage, int DPUserId){
//        try {
//            DailyPlan dailyPlan = new DailyPlan();
//
//            dailyPlan.setDPContent(DPContent);
//            dailyPlan.setDPDate(DPDate);
//            dailyPlan.setDPCalorie(DPCalorie);
//            dailyPlan.setDPImage(DPImage);
//            dailyPlan.setDPUserId(DPUserId);
//            dailyPlan.setDPTime(DPTime);
//            dailyPlan.setDPTag(DPTag);
//            dailyPlanService.addDailyPlan(dailyPlan);
//
//            return ResultFactory.buildSuccessResult(dailyPlan);
//        } catch (Exception e){
//            return ResultFactory.buildFailResult("插入信息失败！");
//        }
//    }

    /**
     * 删除每日计划
     * @param dailyPlan
     * @return
     */
    @GetMapping("api/deleteDP")
    @ResponseBody
    public Result deleteDailyPlan(DailyPlan dailyPlan){
        try {
            int DPId = dailyPlan.getDPId();
            dailyPlanService.deleteDailyPlan(DPId);
            return ResultFactory.buildSuccessResult(dailyPlan);
        } catch (Exception e)
        {
            return  ResultFactory.buildFailResult("删除每日计划失败");
        }

    }


}
