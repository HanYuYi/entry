package com.HanYuYi.web.httpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

//@WebServlet("/demo")
@WebServlet("/register")
public class GetRequestParams extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        req.setCharacterEncoding("utf-8");
        // request分为三部分：请求行，请求头，请求体

        // 获取虚拟目录
        String contextPath = req.getContextPath();
        System.out.println(contextPath);
        // 获取Servlet路径
        String servletPath = req.getServletPath();
        System.out.println(servletPath);
        // 获取请求URI
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        // 获取请求头
        String referer = req.getHeader("referer");
        System.out.println(referer);
        String userAgent = req.getHeader("user-agent");
        System.out.println(userAgent );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        // 获取请求参数，get，post通用
        // getParameter
        // getParameterValues 一般用于获取复选框的值
        // getParameterNames
        // getParameterMap

        /*String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username+"-----"+password);*/

        /*String[] usernames = req.getParameterValues("username");
        for (String s : usernames) {
            System.out.println(s);
        }*/

        /*Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            System.out.println();
            String name = parameterNames.nextElement();
            System.out.print(name + "----" + req.getParameter(name));
            System.out.println();
        }*/

        Map<String, String[]> parameterMap = req.getParameterMap();
        for (String name : parameterMap.keySet()) {
            System.out.println();
            System.out.print(name);
            for (String val : parameterMap.get(name)) {
                System.out.print("---"+val);
            }
        }

        // 请求转发，域对象共享数据
        req.getRequestDispatcher("/aaa").forward(req, resp);
        req.setAttribute("name", "hahaha");
        // 在/aaa请求获取共享数据：req.getAttribute("name");
        // req.removeAttribute("name")
    }
}
