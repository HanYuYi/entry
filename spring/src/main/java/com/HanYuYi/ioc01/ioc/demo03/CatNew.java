package com.HanYuYi.ioc01.ioc.demo03;

public class CatNew {
    private String catName;

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public void shout() {
        System.out.println("miao miao miao...我是" + catName + "喵星人");
    }
}
