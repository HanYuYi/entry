package com.HanYuYi.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class StudentsTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("diBeans.xml");
        Students students = (Students) context.getBean("students");
        System.out.println(students);
    }
}