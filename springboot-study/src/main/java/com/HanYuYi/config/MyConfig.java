package com.HanYuYi.config;

import ch.qos.logback.core.boolex.Matcher;
import com.HanYuYi.pojo.People;
import com.HanYuYi.pojo.Pet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 这里演示几个基本的装配注解
 */
//@ConditionalOnBean(name = "people") // 注意先后顺序
@Configuration(proxyBeanMethods = false)
@Import({Matcher.class})
public class MyConfig {

    @Bean
    public People people() {
        return new People("张三", 19, pet());
    }

    @Bean("tom")
    public Pet pet() {
        return new Pet("tomCat", "yellow");
    }
}
