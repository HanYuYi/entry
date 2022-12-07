package com.example.springbootproject.controller;

import java.lang.annotation.*;
import java.lang.reflect.*;

public class DemoByReflection {

    public static void main(String[] args) {
        InfoReflection info = new InfoReflection();
        Class<? extends InfoReflection> infoReflection = info.getClass();

        // Field
        Field[] fieldS = infoReflection.getDeclaredFields();
        Field currentFiled = null;
        for (Field field : fieldS) {
            currentFiled = field;

            try {
                if (Modifier.isPrivate(currentFiled.getModifiers())) {
                    currentFiled.setAccessible(true);
                }
                if ("age".equals(currentFiled.getName())) {
                    try {
                        currentFiled.set(info, 25);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(currentFiled.get(info));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println(currentFiled.getType());
            System.out.println(currentFiled.getModifiers());
            System.out.println(currentFiled.getName());
            System.out.println("------------------------");

        }

        // method
        try {
            Method method = infoReflection.getDeclaredMethod("postHandler", String.class);
            System.out.println(method.getReturnType());
            System.out.println(method.getModifiers());
            System.out.println(method.getDeclaringClass());
            System.out.println(method.getParameterTypes());
            System.out.println(method.getAnnotation(Report.class).value());
            System.out.println(method.getAnnotation(Report.class).scope());

            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println(parameter.getAnnotation(PathPer.class).value());
            }

            method.invoke(info, "lisi456");

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Report {
    String value() default "";
    String scope() default "method";
}

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@interface PathPer {
    String value() default "";
}

class InfoReflection {

    public String name = "Hope";

    private int age = 20;

    int gender = 0;

    @Report(value = "/login", scope = "mm")
    public void postHandler(@PathPer("zhangsan123") String username) {
        System.out.println("Hello " + username +", login succeeded!......");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }
}
