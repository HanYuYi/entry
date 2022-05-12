package com.HanYuYi.aop02.springAop.example01.service;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    @Test
    void userService() {
        ApplicationContext springAop = new ClassPathXmlApplicationContext("springAop01.xml");
        // 动态代理代理的是接口，不是实现类
        UserService userService = springAop.getBean("userService", UserService.class);
        userService.delete();
        System.out.println("======================================");
        userService.select();
    }
}