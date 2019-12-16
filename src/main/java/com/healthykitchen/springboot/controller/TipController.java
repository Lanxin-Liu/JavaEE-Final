package com.healthykitchen.springboot.controller;

import com.healthykitchen.springboot.dao.TipDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TipController {

    @Autowired
    private TipDao tipDao;

    @GetMapping("/api/tips")
    @ResponseBody
    public List<String> getTodayTips(){
        int []arr=gennerateArray(4, 10);
        List<String> tips=new ArrayList<>();
        for(int i=0;i<4;i++){
            System.out.println(i+1);
            tips.add(tipDao.getTips(arr[i]+1));
        }
        return tips;
    }

    public static int[]  gennerateArray(int len,int max){
        int[] arr=new int[len];
        for(int i=0;i<arr.length;i++){
            arr[i]=(int)(Math.random()*max);
        }
        return arr;
    }
}
