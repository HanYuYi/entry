package com.HanYuYi.aop02.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 基于 jdk的
 * Proxy
 * InvocationHandler
 * 实现动态代理，这个jdk代理只能用于目标对象实现了接口的类上，如果一个类没有实现接口，请使用cglib代理（就是在测试类中可以看到，setTarget的上一个接口，不是类）
 */
public class ProxyInvocationHandler<T> implements InvocationHandler {

    private T target;

    public void setTarget(T target) {
        this.target = target;
    }

    /**
     * 通过要代理的接口（第二个参数是实现类的接口），生成要代理的实现类
     * @return
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     * 执行代理实现类的方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        seeHouse();
        Object invoke = method.invoke(target, args);
        fare();
        return invoke;
    }

    /**
     * 看房
     */
    public void seeHouse() {
        System.out.println("中介带房客看房");
    }

    /**
     * 签租房合同
     */
    public void fare() {
        System.out.println("签合同");
    }
}
