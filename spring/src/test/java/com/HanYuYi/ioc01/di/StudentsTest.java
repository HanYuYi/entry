package com.HanYuYi.ioc01.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class StudentsTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("diBeans.xml");
        Students students = (Students) context.getBean("students");
        System.out.println(students);
    }
}