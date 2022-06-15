# 1.读取配置文件
### springboot推荐无xml化，全部使用 yaml、yml、properties
所以springboot内置了读取application.yaml、application.yml、application.properties的功能，具体案例参考 HelloYaml

首先将类注册到容器中，然后读取 application 有三种方式：

1、```@Value```写在 filed 上

2、通过```@Autowired``` 自动装配到 Environment 对象上

3、通过```@ConfigurationProperties```结合实体类，注册到容器结合注册注解，如```@Component``` 或 ```@EnbleConfigurationProperties```


# 2.JSR303 校验
### 通过在字段上添加注解来限制字段只能接受的值，如：```@Email```限制只接受邮箱格式，同时需要在类上注解```@Validated```


# 3.profile
### 不同的环境配置不同的文件，比如开发环境配置文件名为：application-dev.yml，以-XXXX区分环境，再以 ```spring: profiles: actibe: XXXX```激活配置


# 4.配置文件存放位置
### 有四个地方可以存放配置文件
1、在根目录下新建一个config文件夹存放

2、在根目录下直接存放

3、在classpath下(resources)新建一个config文件夹存放

4、在classpath下(resources)存放，默认的