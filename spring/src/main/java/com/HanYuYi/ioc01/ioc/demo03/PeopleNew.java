package com.HanYuYi.ioc01.ioc.demo03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 注解自动装配
 */
public class PeopleNew {

    @Autowired // 可以注解在字段上，也可以注解在setter上
    // @Autowired(required = false) // 指定这个字段可以为null
    @Qualifier(value = "dogNew") // 当有多个bean时，可以这样指定要注入的bean
    private DogNew dog;

    // @Resource(name = "catNew") // javaEE6 新增的自动装配注解
    private CatNew cat;

    public void setDog(/*@Nullable 指定可以为null*/ DogNew dog) {
        this.dog = dog;
    }

    public DogNew getDog() {
        return dog;
    }
}

