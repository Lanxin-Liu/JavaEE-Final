package com.healthykitchen.springboot.controller;

import com.healthykitchen.springboot.dao.DPDao;
import com.healthykitchen.springboot.dao.RecipeDAO;
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

    @Autowired
    private DPDao dpDao;

    @Autowired
    private RecipeDAO recipeDAO;

    /**
     * 获取用户的DP
     * @return
     */
    @PostMapping("api/mydailyplan")
    @ResponseBody
    List<DPRecipe> getDailyPlan(@RequestParam(value = "userId")int userId){
        List<DailyPlan> dailyPlans;
        dailyPlans=dailyPlanService.getUserDailyPlanById(userId);
        List<DPRecipe> dpRecipes=new ArrayList<>();
//        for (DPRecipe dpr:dpRecipes) {
//            Recipe recipe=new Recipe();
//            dpr.setDailyPlan(dailyPlans.);
//            recipe=recipeService.getRecipeById();
//        }
        /**
         * 把dp和菜谱整合到一起。
         */
        for (DailyPlan dp:dailyPlans){
            DPRecipe dpr=new DPRecipe();
            dpr.setDailyPlan(dp);
            Recipe recipe = new Recipe();
            recipe=recipeService.getRecipeById(dp.getDPRecipeId());
            dpr.setRecipe(recipe);
            dpRecipes.add(dpr);
        }
        return dpRecipes;
    }

    /**
     * 今天的今日计划
     */
    @PostMapping("api/todayplan")
    @ResponseBody
    public List<DPRecipe> showTodayPlan(@RequestParam String date, @RequestParam int userId) {
        List<DailyPlan> dp = dailyPlanService.getUserDailyPlanByDate(date, userId);
        List<DPRecipe> dpRecipes=new ArrayList<>();
        /**
         * 把dp和菜谱整合到一起。
         */
        for (DailyPlan d: dp){
            DPRecipe dpr=new DPRecipe();
            dpr.setDailyPlan(d);
            Recipe recipe = recipeService.getRecipeById(d.getDPRecipeId());
            dpr.setRecipe(recipe);
            dpRecipes.add(dpr);
        }
        return dpRecipes;
    }

    /**
     * 添加每日计划API
     *
     * @return
     */
    @PostMapping("api/addDP")
    @ResponseBody
    public Result addDailyPlan(@RequestParam(value = "userId")int userId,@RequestParam(value = "recipeId")int recipeId){
        try {
            int dpID=dpDao.getDPCount();

            DateUtil date=new DateUtil();
            DailyPlan dailyPlan=new DailyPlan();

            dailyPlan.setDPId(dpID);
            dailyPlan.setDPUserId(userId);
            dailyPlan.setDPRecipeId(recipeId);

            Recipe recipe=recipeService.getRecipeById(recipeId);

            recipe.setCalorie(recipeService.getRecipeCalorie(recipe));

            //System.out.println("该菜谱卡路里为"+recipe.getRecipeCalorie());
            System.out.println("该菜谱卡路里为"+recipe.getCalorie());
            recipeDAO.updateRecipeCalorie(recipe);

            dailyPlan.setDPDate(date.getTime());
            dailyPlan.setDPCalorie(recipe.getCalorie());

            dailyPlanService.addDailyPlan(dailyPlan);
            return ResultFactory.buildSuccessResult(dailyPlan);
        } catch (Exception e){
            return ResultFactory.buildFailResult("插入信息失败！");
        }
    }

    /**
     * 删除每日计划
     * @return
     */
    @PostMapping("api/deleteDP")
    @ResponseBody
    public Result deleteDailyPlan(@RequestParam(value = "DPId")int DPId){
        try {
            dailyPlanService.deleteDailyPlan(DPId);
            return ResultFactory.buildSuccessResult(DPId);
        } catch (Exception e)
        {
            return  ResultFactory.buildFailResult("删除每日计划失败");
        }

    }


}
