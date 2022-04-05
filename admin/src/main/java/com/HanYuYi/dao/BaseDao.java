package com.HanYuYi.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 操作数据库的公共类
 */
public class BaseDao {
    private static String driver;
    private static String url;
    private static String name;
    private static String password;

    // 类加载的时候就会执行静态代码块
    static {
        Properties properties = new Properties();
        InputStream resource = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(resource);
            driver = properties.getProperty("DRIVER");
            url = properties.getProperty("DATABASE_URL");
            driver = properties.getProperty("DATABASE_USERNAME");
            driver = properties.getProperty("DATABASE_PASSWORD");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, name, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
