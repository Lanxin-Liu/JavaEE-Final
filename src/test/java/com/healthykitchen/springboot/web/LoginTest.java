//package com.healthykitchen.springboot.web;
//
//import com.healthykitchen.springboot.controller.LoginController;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.hamcrest.Matchers.equalTo;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
///**
// * @className:
// * @description:
// * @author: anonym_co
// * @date: 21:01 2019/11/15
// * @version: v1.0
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class LoginTest {
//
//    private MockMvc mvc;
//
//    @Before
//    public void setUp() {
//        mvc = MockMvcBuilders.standaloneSetup(new LoginController()).build();
//    }
//
//    @Test
//    public void testLoginController() throws Exception {
//        RequestBuilder request;
//        request = post("/api/login").
//                contentType(MediaType.APPLICATION_JSON).
//                content("{\"username\":coke,\"password\":\"123}");
//        mvc.perform(request)
//                .andExpect(content().string(equalTo("成功")));
//    }
//}
