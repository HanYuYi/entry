package com.HanYuYi.controller;

import com.HanYuYi.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/yaml")
public class HelloYaml {

    @Value("${uname}")
    private String name;
    @Value("${person.age}")
    private int age;
    @Value("${person.hobby[0]}")
    private String hobby;
    @Value("${msg1}")
    private String msg1;
    @Value("${msg2}")
    private String msg2;

    @Autowired
    private Environment env;

    @Autowired
    private Person person;

    @RequestMapping("/getValue")
    public String getValue() {
        return name;
    }

    @RequestMapping("/getValueObjFiled")
    public int getValueObjFiled() {
        return age;
    }

    @RequestMapping("/getValueObjList")
    public String getValueObjList() {
        return hobby;
    }

    @RequestMapping("/getMsg")
    public String getMsg() {
        return msg1 + ", " + msg2;
    }

    @RequestMapping("/getEnvironment")
    public String getEnvironment() {
        return env.getProperty("person.name") + ", " + env.getProperty("person.hobby[1]");
    }

    @RequestMapping("/getConfiguration")
    public Person getConfiguration() {
        return person;
    }
}
