package com.HanYuYi.service.demo01;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class UserServiceImplTest {

    @Test
    void show() {
        // 获取spring上下文
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // 使用在spring中管理的对象，在beans.xml中配置的
        UserServiceImpl helloUser = (UserServiceImpl) context.getBean("helloUser");
        helloUser.show();
        helloUser.getUserDao();
    }
}