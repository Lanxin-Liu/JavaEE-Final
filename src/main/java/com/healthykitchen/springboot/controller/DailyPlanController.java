package com.healthykitchen.springboot.controller;

import com.healthykitchen.springboot.pojo.DailyPlan;
import com.healthykitchen.springboot.pojo.User;
import com.healthykitchen.springboot.result.Result;
import com.healthykitchen.springboot.result.ResultFactory;
import com.healthykitchen.springboot.service.DailyPlanService;
import com.healthykitchen.springboot.service.TagService;
import com.healthykitchen.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
     * 获取用户的DP
     * @param request
     * @return
     */
    @PostMapping("api/mydailyplan")
    @ResponseBody
    List<DailyPlan> getDailyPlan(HttpServletRequest request){
        HttpSession session=request.getSession(true);
        User user=(User)session.getAttribute("user");
        List<DailyPlan> dailyPlans;
        dailyPlans=dailyPlanService.getUserDailyPlanById(user.getUserId());
        return dailyPlans;
    }

    /**
     * 添加每日计划API
     * @param dailyPlan
     * @return
     */
    @PostMapping("api/addDP")
    @ResponseBody
    public Result addDailyPlan(@RequestParam(value = "DailyPlan")DailyPlan dailyPlan,HttpServletRequest request){
        try {
            HttpSession session=request.getSession(true);
            User user=(User)session.getAttribute("user");
            dailyPlan.setDPUserId(user.getUserId());
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
    @PostMapping("api/deleteDP")
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
