package com.HanYuYi;

import ch.qos.logback.core.boolex.Matcher;
import com.HanYuYi.pojo.People;
import com.HanYuYi.pojo.Pet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootStudyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringbootStudyApplication.class, args);

        // 根据名称获取被注册到容器的所有组件
        /*String[] beanNames = run.getBeanDefinitionNames();
        for (String bean : beanNames) {
            System.out.println(bean);
        }*/

        // 测试 proxyBeanMethods
        People people = run.getBean("people", People.class);
        System.out.println("proxyBeanMethods 默认为 true 时：");
        System.out.println(people.getPetName() == run.getBean("tom", Pet.class));


        // 测试 @Import
        String[] beanNamesForType = run.getBeanNamesForType(Matcher.class);
        for (String bean : beanNamesForType) {
            System.out.println(bean);
        }

    }

}
