# AOP：面向切面编程，跟代理模式类似，横向扩展其他功能
## 在不修改原有代码的基础上，增加其它功能，本章演示spring的AOP，分别有三个示例。
1、以用户的增删改查，切入打印日志，这个示例使用的是实现 ```org.springframework.aop```包下的接口，每一个接口是一个切入时间点。

2、以用户查询后缓存为例，这个增强是使用过自定义切面的方式实现，以上两种都是基于xml配置实现的。

3、以抽奖为例，在新增用户后，用户有一次抽奖机会，所以从这里切入，这里主要体现了使用注解切入。

PS：切入点可以理解生命周期。