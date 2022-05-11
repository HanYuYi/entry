package com.HanYuYi.ioc01.javaConfig.config;

import com.HanYuYi.ioc01.javaConfig.user.User;
import org.springframework.context.annotation.*;

/**
 * 演示使用纯java代码将用spring管理bean
 * @Configuration 声明是一个javaConfig
 * @ComponentScan 扫描包
 * @Import 跟xml里的import一样
 */
@Import(BeanConfig1.class)
@Configuration
@ComponentScan("com.HanYuYi.ioc01.javaConfig.user")
@Scope("prototype")
public class BeanConfig {

    @Bean
    public User getUser() {
        return new User();
    }
}
