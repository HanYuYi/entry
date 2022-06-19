### 静态文件
* 静态文件可以放到 /static、/public、/resources、/META-INF/resources这四个目录下，且可以直接访问。
* 如果要给静态文件加前缀访问，可以配置：spring.mvc.static-path-pattern=/resources/**，resources就是前缀。
* 也可以将静态文件放到自定义目录下，不过需要配置自定义目录的位置才能访问，比如：spring.resources.static-locations=classpath:/haha/，访问的时候也要加上配置的路径/haha。
* webjar：是一个存放三方包的仓库，可以引入坐标依赖后，直接使用。
