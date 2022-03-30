package com.HanYuYi.ioc;

/**
 * 演示命名空间的类
 */
public class NameSpaceCalss {

    private String name;
    private int age;

    public NameSpaceCalss() {}

    public NameSpaceCalss(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "NameSpaceCalss{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
