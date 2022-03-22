package com.HanYuYi.web.httpServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

@WebServlet("/register")
public class GetRequestParams extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         req.setCharacterEncoding("utf-8");
        // request分为四部分：请求行，请求头，请求空行，请求体

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


        // 重定向，需要加虚拟目录，可以访问其他服务器资源
        // resp.setStatus(302);
        // resp.setHeader("location", contextPath + "/*.do");
        // 简化重定向，需要加虚拟目录
        // resp.sendRedirect(contextPath + " /*.do");


        // getServletContext 域对象，最大的对象，在任何一个httpServlet都可以共享数据
        ServletContext servletContext = req.getServletContext();
        String img = "a.jpg";
        // 获取mime类型
        String mimeType = servletContext.getMimeType(img);
        System.out.println(mimeType);
        // 获取文件路径
        String realPath = servletContext.getRealPath("/entry");
        File file = new File(realPath);
        System.out.println(file);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        /*Map<String, String[]> parameterMap = req.getParameterMap();
        for (String name : parameterMap.keySet()) {
            System.out.println();
            System.out.print(name + "----");
            for (String val : parameterMap.get(name)) {
                System.out.print(val + ", ");
            }
        }*/

        // 请求转发，不需要加虚拟目录(只能转发到本服务器)，域对象共享数据
        // req.getRequestDispatcher("/aaa").forward(req, resp);
        // req.setAttribute("name", "hahaha");
        // 在/aaa获取共享数据：req.getAttribute("name");
        // req.removeAttribute("name")



        resp.setContentType("text/html;charset=utf-8");
        // response分为四部分：响应行，响应头，响应空行，响应体
        // 设置状态码
        resp.setStatus(200);
        // 设置响应头
        // resp.setHeader("content-type", "text/html;charset=utf-8");
        // 设置响应体，字符输出流
        PrintWriter writer = resp.getWriter();
        String username = req.getParameter("username");
        if (username != null) {
            writer.write("注册成功！欢迎您：" + req.getParameter("username"));
        } else {
            writer.write("注册失败！请重试");
        }
        writer.flush();
        // 设置响应体，字节输出流
        // resp.getOutputStream()
    }
}
