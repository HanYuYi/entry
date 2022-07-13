package com.hanyuyi.springsecuritystudy.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 配置 spring security
 * 安全框架的基本概念：认证、授权
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 认证，通俗的说就是：用户能否登录
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 设置首页可以被所有用户访问
                .antMatchers("/").permitAll()
                // 设置vip角色的用户只能访问 /level1/ 下的资源
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        // 显示开启 没有权限跳转到登录页面，这个是spring security的登录页
        http.formLogin();
    }

    /**
     * 授权，通俗的说就是：用户能否访问
     * @param auth
     * @throws Exception
     *
     * 在 spring security 5.X 密码要加密
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 从数据库查询使用这个
        // auth.jdbcAuthentication()

        // 从内存查询
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                // 设置 zhangsan 用户为 VIP1，对应上面的认证路径，VIP1 只能访问 /level1/**
                .withUser("zhangsan").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1")
                .and()
                .withUser("lisi").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2", "vip3")
                .and()
                .withUser("wangwu").password(new BCryptPasswordEncoder().encode("123456")).roles("vip3");

    }
}
