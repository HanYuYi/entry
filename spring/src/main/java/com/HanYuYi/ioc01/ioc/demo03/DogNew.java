package com.HanYuYi.ioc01.ioc.demo03;

public class DogNew {
    private String dogName;

    public DogNew(String dogName) {
        this.dogName = dogName;
    }

    public void shout() {
        System.out.println("wang wang wang...我是" + dogName);
    }
}
