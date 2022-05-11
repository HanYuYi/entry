package com.HanYuYi.aop02.dynamicProxy;

/**
 * 实现出租房子的接口
 */
public class Host implements Rent {
    @Override
    public void rent() {
        System.out.println("房东出租房子了");
    }
}
