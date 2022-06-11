package com.HanYuYi.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "person")
@Data
public class Person {

    private String name;
    private String flowerName;
    private int age;
    private String[] hobby;
}
