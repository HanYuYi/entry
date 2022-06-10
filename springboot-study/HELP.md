# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.0/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#web)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#using.devtools)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)


# 1.读取yaml
### springboot推荐无xml化，全部使用 yaml、yml、properties
所以springboot内置了读取application.yaml、application.yml、application.properties的功能，具体案例参考 HelloYaml

读取 application 有三种方式：

1、```@Value```写在 filed 上

2、通过```@Autowired``` 自动装配到 Environment 对象上

3、通过```@ConfigurationProperties```结合实体类


### JSR303 校验，通过在字段上添加注解来限制字段只能接受的值，如：```@Email```限制只接受邮箱格式，同时需要在类上注解```@Validated```