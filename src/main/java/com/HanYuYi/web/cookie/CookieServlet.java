package com.HanYuYi.web.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cook")
public class CookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie1 = new Cookie("msg", "hello");
        Cookie cookie2 = new Cookie("state", "come");
        // 将cookie发送到浏览器
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);
        // 设置cookie
        // cookie1.setValue("world");
        // 获取浏览器发送回来的cookie
        Cookie[] cookies = req.getCookies();
        for (Cookie c : cookies) {
            System.out.println(c.getName());
            System.out.println(c.getValue());
        }
        // 设置过期时间，默认为-1， cookie持久化10秒，0：删除cookie
        cookie1.setMaxAge(10);

        // 设置同一个tomcat服务器下的所有项目都能共享cookie
        cookie1.setPath("/");
        // 设置在多个tomcat服务器下一级域名相同的项目可以共享cookie
        // cookie1.setDomain(".baidu");
    }
}
