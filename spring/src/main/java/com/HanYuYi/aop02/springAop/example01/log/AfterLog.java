package com.HanYuYi.aop02.springAop.example01.log;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * 第一种方式，使用实现接口的方式
 * 实现 AfterReturningAdvice 接口，在方法执行后执行
 */
public class AfterLog implements AfterReturningAdvice {

    /**
     * @param returnValue 方法返回值
     * @param method
     * @param args
     * @param target
     * @throws Throwable
     */
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println(method.getName() + "方法的返回值为：" + returnValue);
    }
}
