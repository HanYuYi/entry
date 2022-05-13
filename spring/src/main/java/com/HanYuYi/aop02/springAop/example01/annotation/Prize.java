package com.HanYuYi.aop02.springAop.example01.annotation;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 用户新增后，有一次抽奖机会，示例演示注解配置spring AOP
 */

// 将这个类标记为切面
@Aspect
@Component
public class Prize {


    // 用户新增前，说白了就是 insert 执行前
    @Before("execution(* com.HanYuYi.aop02.springAop.example01.service.UserServiceImpl.insert(..))")
    public void tipLuckDraw() {
        System.out.println("注册成功后有大奖哦");
    }

    // 用户新增成功返回后，就是 insert 执行完返回的时候
    @AfterReturning("execution(* com.HanYuYi.aop02.springAop.example01.service.UserServiceImpl.insert(..))")
    public void jackpot() {
        System.out.println("您的奖品为抵用券500元！");
    }

    /**
     * 环绕增强，在前后都会执行，如果定义了镀铬增强的时机，会由近到远执行
     */
    @Around("execution(* com.HanYuYi.aop02.springAop.example01.service.UserServiceImpl.insert(..))")
    public void around(ProceedingJoinPoint pj) throws Throwable {
        System.out.println("环绕前");

        // 执行真实角色的方法
        Object proceed = pj.proceed();

        System.out.println("环绕后");
    }
}
