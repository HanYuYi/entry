package com.HanYuYi.web.httpServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * HttpServlet 继承自GenericServlet，GenericServlet 继承自Servlet
 */
//@WebServlet({"/a", "/aa"})
//@WebServlet({"/a/aa"})
//@WebServlet({"/a/*"})
//@WebServlet({"/*"})
@WebServlet({"*.do"})
//@WebServlet({"/"})
public class HelloServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("service....");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         super.doGet(req, resp);

        System.out.println("zzzzz");
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.write("<h1>Hello, world!</h1>");
        pw.flush();
        System.out.println("QQQQQQ");
    }
}
