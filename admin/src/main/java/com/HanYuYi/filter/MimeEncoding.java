package com.HanYuYi.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MimeEncoding implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        ServletContext context = request.getServletContext();
        String requestURI = req.getRequestURI();
        if (requestURI.contains("woff")) {
            String type = context.getMimeType("a.woff");
            resp.setContentType("application/" + type + ";");
        }
        if (requestURI.contains("ttf")) {
            String type = context.getMimeType("a.ttf");
            resp.setContentType("application/" +type + ";");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
