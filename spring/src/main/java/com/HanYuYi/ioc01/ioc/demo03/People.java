package com.HanYuYi.ioc01.ioc.demo03;

public class People {
    private Dog dog;

    public People(Dog dog) {
        this.dog = dog;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}
