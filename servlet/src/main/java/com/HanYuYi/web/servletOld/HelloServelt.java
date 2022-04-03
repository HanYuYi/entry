package com.HanYuYi.web.servletOld;

import javax.servlet.*;
import java.io.IOException;

/**
 * 3.0之前需要手动去web.xml配置映射路径
 */
public class HelloServelt implements Servlet {

    /**
     * Servlet被创建时
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    /**
     * 获取ServletConfig对象
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * Servlet被访问时
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("hello Servlet!");
    }

    /**
     * 获取Servlet的信息，如版本、作者等
     * @return
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * Servlet被正常关闭时
     */
    @Override
    public void destroy() {

    }
}
