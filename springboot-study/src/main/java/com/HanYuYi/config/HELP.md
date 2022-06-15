# 1.几个基本的spring注解
### 1、```@Configuration```
配置bean，相当于xml配置bean，在springBoot2中新增了 proxyBeanMethods 属性，
表示组件每次被调用是否重新创建，默认为true。建议在没有其他组件依赖的情况下，设为false。
### 2、```@import```
配置 @Configuration 注解使用，相当于把 import 的类也添加为组件。
### 3、```@Conditional```
条件装配，它有很多派生注解，例如：@ConditionalOnBean(name = "tom") 表示在容器有tom组件时，装配这个组件，
反之，@ConditionalOnMissingBean 表示容器中没有这个组件时子装配这个组件。这个注解也可以用在方法上。
### 4、```@ImportResource```
把用xml配置装配到容器里，例如在使用三方框架的时候，因为框架比较老，使用的上xml配置，我们就可以用 @ImportResource("classpath:XXX.xml")