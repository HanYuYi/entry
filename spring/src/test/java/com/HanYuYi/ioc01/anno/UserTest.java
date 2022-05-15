package com.HanYuYi.anno;

import com.HanYuYi.ioc01.anno.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
    @Test
    void testUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("annoBeans.xml");
        User user = context.getBean("user", User.class);
        user.setAge(26);
        System.out.println(user);
    }
}
