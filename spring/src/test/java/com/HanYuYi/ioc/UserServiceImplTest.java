package com.HanYuYi.ioc;

import com.HanYuYi.ioc.demo02.UserConsImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class UserServiceImplTest {

    @Test
    void show() {
        // 获取spring上下文
        ApplicationContext context = new ClassPathXmlApplicationContext("iocBeans.xml");
        // 使用在spring中管理的对象，在beans.xml中配置的
        UserServiceImpl helloUser = (UserServiceImpl) context.getBean("helloUser");
        helloUser.show();
        helloUser.getUserDao();

        UserConsImpl conUser = (UserConsImpl) context.getBean("conUser");
        conUser.show();

        NameSpaceCalss namesp1 = context.getBean("namesp1", NameSpaceCalss.class);
        System.out.println(namesp1);
        NameSpaceCalss namesp2 = context.getBean("namesp2", NameSpaceCalss.class);
        System.out.println(namesp2);
    }
}