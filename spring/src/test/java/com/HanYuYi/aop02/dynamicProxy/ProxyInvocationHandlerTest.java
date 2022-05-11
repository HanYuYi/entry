package com.HanYuYi.aop02.dynamicProxy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProxyInvocationHandlerTest {

    @Test
    void rent() {
        ProxyInvocationHandler<Host> pih = new ProxyInvocationHandler<>();
        // 设置被代理的实现类
        pih.setTarget(new Host());
        // 获取动态生成的代理类
        Rent proxy = (Rent) pih.getProxy();
        // 执行被代理类的方法
        proxy.rent();
    }

}