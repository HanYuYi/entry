package com.HanYuYi.web.httpServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * HttpServlet 继承自GenericServlet，GenericServlet 继承自Servlet
 */
//@WebServlet({"/a", "/aa"})
//@WebServlet({"/a/aa"})
//@WebServlet({"/a/*"})
//@WebServlet({"/*"})
//@WebServlet({"*.do"})
@WebServlet({"/"})
public class HelloServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("service....");
    }
}
