##要学习AOP，先理解代理模式，这一张演示动态代理
动态代理有两个核心的东西：
```java
Proxy
InvocationHandler
```
Proxy： 用于生成代理的类，相当于静态代理章节的Proxy。

InvocationHandler：是一个接口，用于执行代理实例的方法。

他们都是反射技术，存在于JDK反射包下。动态代理顾名思义，就是使用使用这两个反射技术，在动内存中动态生成代理类。