package com.HanYuYi.web.listener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 建立数据库连接
 */
public class DatabaseInit {

    /**
     * 获取数据库配置文件的值
     * @return
     */
    private Map<String, String> loadConfig() {
        HashMap<String, String> configMap = new HashMap<>();
        Properties properties = new Properties();
        try {
            InputStream fileInputStream = this.getClass().getClassLoader().getResourceAsStream("database.properties");
            properties.load(fileInputStream);
            configMap.put("url", properties.getProperty("DATABASE_URL"));
            configMap.put("username", properties.getProperty("DATABASE_USERNAME"));
            configMap.put("password", properties.getProperty("DATABASE_PASSWORD"));
            System.out.println("数据库配置文件获取成功...");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configMap;
    }

    /**
     * 建立数据库连接
     * @return
     */
    public Connection start() {
        Connection connection = null;
        Map<String, String> configMap = loadConfig();

        try {
            // 建立连接
            connection = DriverManager.getConnection(
                    configMap.get("url"),
                    configMap.get("username"),
                    configMap.get("password")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
