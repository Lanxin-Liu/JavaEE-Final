package com.healthykitchen.springboot.controller;

import com.healthykitchen.springboot.pojo.DPRecipe;
import com.healthykitchen.springboot.pojo.DailyPlan;
import com.healthykitchen.springboot.pojo.Recipe;
import com.healthykitchen.springboot.pojo.User;
import com.healthykitchen.springboot.result.Result;
import com.healthykitchen.springboot.result.ResultFactory;
import com.healthykitchen.springboot.service.DailyPlanService;
import com.healthykitchen.springboot.service.RecipeService;
import com.healthykitchen.springboot.service.TagService;
import com.healthykitchen.springboot.service.UserService;
import com.healthykitchen.springboot.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class DailyPlanController {
    @Autowired
    private UserService userService;

    @Autowired
    private DailyPlanService dailyPlanService;

    @Autowired
    private TagService tagService;

    @Autowired
    private RecipeService recipeService;

    /**
     * 获取用户的DP
     * @param userId
     * @return
     */
    @PostMapping("api/mydailyplan")
    @ResponseBody
    List<DPRecipe> getDailyPlan(@RequestParam(value = "userId")int userId){
        List<DailyPlan> dailyPlans;
        dailyPlans=dailyPlanService.getUserDailyPlanById(userId);
        List<DPRecipe> dpRecipes=new ArrayList<>();
            for (DailyPlan dp : dailyPlans) {
                DPRecipe dpr=new DPRecipe();
                dpr.setDailyPlan(dp);
                dpr.setRecipe(recipeService.getRecipeById(dp.getDPrecipeId()));
                dpRecipes.add(dpr);
            }
        return dpRecipes;
    }

    /**
     * 添加每日计划API
     *
     * @return
    /**
     * 添加每日计划API
     *
     * @return
     */
    @PostMapping("api/addDP")
    @ResponseBody
    public Result addDailyPlan(@RequestParam(value = "userId")int userId,@RequestParam(value = "recipeId")int recipeId){
        try {
            DailyPlan dailyPlan=new DailyPlan();
            dailyPlan.setDPUserId(userId);
            Recipe recipe=recipeService.getRecipeById(recipeId);
            recipe.setRecipeCalorie(recipeService.getRecipeCalorie(recipe));
            DateUtil date=new DateUtil();
            dailyPlan.setDPDate(date.getTime());
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
