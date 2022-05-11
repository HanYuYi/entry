package com.HanYuYi.ioc01.ioc;

import com.HanYuYi.ioc01.ioc.demo02.UserConsImpl;
import com.HanYuYi.ioc01.ioc.demo03.People;
import com.HanYuYi.ioc01.ioc.demo03.PeopleNew;
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

        // 测试构造器注
        UserConsImpl conUser = (UserConsImpl) context.getBean("conUser");
        conUser.show();

        // 测试命名空间
        NameSpaceCalss namesp1 = context.getBean("namesp1", NameSpaceCalss.class);
        System.out.println(namesp1);
        NameSpaceCalss namesp2 = context.getBean("namesp2", NameSpaceCalss.class);
        System.out.println(namesp2);

        // 测试bean作用域
        System.out.println(context.getBean("namesp3") == context.getBean("namesp3"));

        // 测试xml自动装配
        People people = context.getBean("people", People.class);
        people.getDog().shout();

        // 测试注解自动装配
        PeopleNew peopleNew = context.getBean("peopleNew", PeopleNew.class);
        peopleNew.getDog().shout();
    }
}