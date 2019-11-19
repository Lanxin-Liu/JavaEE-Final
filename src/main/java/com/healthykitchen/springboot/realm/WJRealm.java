//package com.healthykitchen.springboot.realm;
//
///**
// * @className: WJRealm
// * @description: 加密
// * @author: anonym_co
// * @date: 15:11 2019/11/17
// * @version: v1.0
// */
//import com.healthykitchen.springboot.pojo.User;
//import com.healthykitchen.springboot.service.UserService;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class WJRealm extends AuthorizingRealm {
//
//    @Autowired
//    private UserService userService;
//
//    // 简单重写获取授权信息方法
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
//        return s;
//    }
//
//    // 获取认证信息，即根据 token 中的用户名从数据库中获取密码、盐等并返回
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        String userName = token.getPrincipal().toString();
//        User user = userService.getByUsername(userName);
//        String passwordInDB = user.getPassword();
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, passwordInDB, getName());
//        return authenticationInfo;
//    }
//}
//
