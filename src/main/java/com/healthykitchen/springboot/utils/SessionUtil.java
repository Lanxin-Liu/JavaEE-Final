package com.healthykitchen.springboot.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
    public void setSession(HttpServletRequest request,String key,Object content){
        if(content==null)
        {
            request.getSession().removeAttribute(key);
        }
        else {
            request.getSession(true).setAttribute(key,content);
        }

    }
}
