package com.HanYuYi.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Filter: 在/filter_page.html路径下到达HttpServlet之前会执行，一般用于处理公共逻辑
 * 如果有多个filter，可以在web.xml种指定顺序
 * 三个生命周期
 */

// 推荐使用配置，不推荐使用注解，因为filter如果有多个，需要指定顺序
// @WebFilter("/*")
public class FilterServlet implements Filter {
    private String encoding = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encoding = filterConfig.getInitParameter("encoding");
        this.encoding = encoding;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(encoding);
        if (encoding != "" && encoding != null) {
            String reqEncoding = servletRequest.getCharacterEncoding();
            String respEncoding = servletResponse.getCharacterEncoding();
            String contentType = servletResponse.getContentType();
            if (reqEncoding == null) {
                servletRequest.setCharacterEncoding(encoding);
            }
            if (respEncoding == null) {
                servletResponse.setCharacterEncoding(encoding);
            }
            if (contentType == null) {
                servletResponse.setContentType("text/html;charset=utf-8");
            }
            // 要继续处理请求，必须调用doFilter，但如果逻辑未完成可以重定向
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            /*HttpServletResponse response = (HttpServletResponse) servletResponse;
            String virPath = servletRequest.getServletContext().getContextPath();
            response.sendRedirect(virPath + "/register.html");*/
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {}
}
