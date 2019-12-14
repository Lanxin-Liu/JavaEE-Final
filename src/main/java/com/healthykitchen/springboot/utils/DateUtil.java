package com.healthykitchen.springboot.utils;

import java.util.Calendar;
import java.util.TimeZone;

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
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        int year = cal.get(Calendar.YEAR);//获取年份
        int month = cal.get(Calendar.MONTH)+1;//获取月份
        int day = cal.get(Calendar.DATE);//获取日
        int hour = cal.get(Calendar.HOUR_OF_DAY);//小时
        int minute = cal.get(Calendar.MINUTE);//分
        int second = cal.get(Calendar.SECOND);//秒
        time = Integer.toString(year)+"-"+ Integer.toString(month)+"-"+Integer.toString(day)+" "+Integer.toString(hour)+":"+ Integer.toString(minute)+":"+Integer.toString(second);
        return time;
    }
}
