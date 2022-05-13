package com.HanYuYi.aop02.cusProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HostProxyHandler<T> {
    // 被代理的真实角色
    private T target;

    public void setTarget(T target) {
        this.target = target;
    }

    /**
     * 使用反射调用真实角色的方法
     * @param methodName
     * @return
     */
    public boolean handler(String methodName) {
        Class<?> aClass = target.getClass();
        Method method = null;
        try {
            method = aClass.getMethod(methodName);
            seeHouse();
            method.invoke(target);
            fare();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
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
