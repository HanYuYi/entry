package com.HanYuYi.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 监听器Listener，跟Servlet、Filter同级
 * 它会在整个Web应用程序初始化完成及关闭后获得通知
 * 通常我们可以把初始化数据库连接池、清理关闭资源的等工作放到这里
 */
@WebListener
public class AppListener implements ServletContextListener {

    private Connection databaseStart = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // TODO 待解决
         new DatabaseInit().start();
         new DatabaseInit().start();
        System.out.println("数据库连接成功...");
        databaseStart = new DatabaseInit().start();
        System.out.println(databaseStart);
        ServletContext globalServletContext = sce.getServletContext();
        globalServletContext.setAttribute("database", databaseStart);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            databaseStart.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ServletContext globalServletContext = sce.getServletContext();
        globalServletContext.removeAttribute("database");
    }
}
