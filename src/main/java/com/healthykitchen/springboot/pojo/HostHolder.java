package com.healthykitchen.springboot.pojo;

import org.springframework.stereotype.Component;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 02:58 2019/11/22
 * @version: v1.0
 */
@Component
public class HostHolder {
    //为每个线程提供独立的变量副本
    private static ThreadLocal<User> users = new ThreadLocal<User>();

    public User getUser() {
        return users.get();
    }

    public void setUser(User user) {
        users.set(user);
    }

    public void clear() {
        users.remove();
    }
}
