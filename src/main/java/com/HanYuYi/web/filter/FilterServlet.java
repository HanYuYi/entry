package com.HanYuYi.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter: 在/user/*路径下到达HttpServlet之前会执行，一般用于处理公共逻辑
 * 如果有多个filter，可以在web.xml种指定顺序
 */
@WebFilter("/user/*")
public class FilterServlet implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        // 要继续处理请求，必须调用doFilter，但如果逻辑未完成可以重定向
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (false) {
            response.sendRedirect("/");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
