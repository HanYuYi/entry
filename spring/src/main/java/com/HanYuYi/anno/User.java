package com.HanYuYi.anno;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * 使用注解的方式配置spring，@Component注解有四个衍生注解：
 * @Repository 在dao层使用
 * @Service 在service逻辑层使用
 * @Controller 在controller控制层使用
 *
 * @Value可以用在字段和setter上
 *
 * 市面上一般上xml和注解搭配使用
 */
@Component
@Scope("prototype")
public class User {

    @Value("张大漂亮")
    private String name;

    private int age;

    public String getName() {
        return name;
    }

    @Value("24")
    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
