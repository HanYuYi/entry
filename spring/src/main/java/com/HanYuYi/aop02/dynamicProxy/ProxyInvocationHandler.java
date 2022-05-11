package com.HanYuYi.aop02.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 基于
 * Proxy
 * InvocationHandler
 * 实现动态代理
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
        System.out.println("[DEBUG] " + method.getName() + "方法执行了");
        Object invoke = method.invoke(target, args);
        return invoke;
    }
}
