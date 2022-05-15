package com.HanYuYi.javaConfig;

import com.HanYuYi.ioc01.javaConfig.config.BeanConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class UserTest {

    @Test
    void userTest() {
        // 获取spring上下文，注意：使用javaConfig注册bean使用此实现类获取spring
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        // User getUser = context.getBean("getUser", User.class);
        // 推荐直接使用类的class
        BeanConfig bean = context.getBean(BeanConfig.class);
        System.out.println(bean.getUser());
    }
}