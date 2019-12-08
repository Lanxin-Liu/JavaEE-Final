package com.healthykitchen.springboot.utils;

import java.util.Calendar;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 21:28 2019/11/23
 * @version: v1.0
 */
public class DateUtil {
    public String getTime() {
        String time;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);//获取年份
        int month = cal.get(Calendar.MONTH);//获取月份
        int day = cal.get(Calendar.DATE);//获取日
        int hour = cal.get(Calendar.HOUR);//小时
        int minute = cal.get(Calendar.MINUTE);//分
        int second = cal.get(Calendar.SECOND);//秒
        int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);//一周的第几天
        time = Integer.toString(year)+"-"+ Integer.toString(month)+"-"+Integer.toString(day)+" "+Integer.toString(hour)+"-"+ Integer.toString(minute)+"-"+Integer.toString(second);
        return time;
    }
}
