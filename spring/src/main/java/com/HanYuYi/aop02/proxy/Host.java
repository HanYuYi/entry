package com.HanYuYi.aop02.proxy;

/**
 * 房东出租房子，要实现出租房子的接口
 */
public class Host implements Rent {
    @Override
    public void rent() {
        System.out.println("房东出租房子");
    }
}
