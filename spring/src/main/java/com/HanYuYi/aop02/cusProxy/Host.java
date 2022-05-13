package com.HanYuYi.aop02.cusProxy;

public class Host implements Rent {
    @Override
    public void rent() {
        System.out.println("房东出租房子了");
    }
}
