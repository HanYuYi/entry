package com.example.springbootproject.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * Druid 监控平台配置
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(value = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean<StatViewServlet> statViewServlet = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("loginUsername", "admin");
        paramMap.put("loginPassword", "123456");
        // 允许谁访问
        paramMap.put("allow", "");
        // 禁止谁访问
        // paramMap.put("XXX", "192.168.0.106");

        statViewServlet.setInitParameters(paramMap);
        return statViewServlet;
    }

    /**
     * 配置不进入统计的内容
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean<WebStatFilter> WebStatFilter = new FilterRegistrationBean<>(new WebStatFilter());

        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("exclusions", "*.js,*.css,/druid/*");

        WebStatFilter.setInitParameters(paramMap);
        return WebStatFilter;
    }

}
