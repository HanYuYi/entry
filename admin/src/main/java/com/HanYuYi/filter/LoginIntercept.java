package com.HanYuYi.filter;


import com.HanYuYi.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录过滤
 */
public class LoginIntercept implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI().replaceAll(req.getContextPath(), "");
        Object attr = req.getSession().getAttribute(Constants.USER_SESSION);

        HttpServletResponse resp = (HttpServletResponse) response;
        // 未登录
        if (attr == null) {
            if ((!uri.equals("/index.jsp")) && (!uri.equals("/"))) {
                resp.sendRedirect(req.getContextPath() + "/");
            }
        } else {
            if (uri.equals("/index.jsp") || uri.equals("/")) {
                resp.sendRedirect(req.getContextPath() + "/auth/home.jsp");
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
