package org.abliger.lang.db;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Objects;
import java.util.Properties;

/**
 * @author abliger
 * @date 2020/12/3
 * @description
 */

public class MyConnection {
    private static String fileName = "druid.properties";

    public MyConnection() {
        System.out.println("初始化连接池");
    }

    public static Connection getConnection() {
        Properties properties = new Properties();
        InputStream inputStream = null;
        Connection connection=null;
        try {
            inputStream = Objects.requireNonNull(MyConnection.class
                    .getClassLoader()
                    .getResource(fileName))
                    .openStream();
            properties.load(inputStream);
            final DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            connection = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        MyConnection.fileName = fileName;
    }
}
