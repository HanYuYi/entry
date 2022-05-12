package com.HanYuYi.aop02.springAop.example01.log;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 第一种方式，使用实现接口的方式
 * 实现 MethodBeforeAdvice 接口，在方法执行前执行
 */
public class Log implements MethodBeforeAdvice {

    /**
     *
     * @param method 要执行的目标对象的方法，相当于在这个方法在被代理的方法执行前会被执行
     * @param args 方法参数
     * @param target 要添加的目目标对象
     * @throws Throwable
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getName() + "类的" + method.getName() + "方法执行了" + (args.length > 0 ? "，方法参数为：" + args.toString() : ""));
    }
}
