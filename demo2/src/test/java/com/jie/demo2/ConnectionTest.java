package com.jie.demo2;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ConnectionTest
 * 获取数据库连接的方式
 *
 * @author sujie
 * @version 1.0
 * @since 2021/3/23
 */
public class ConnectionTest {
    //方式1
    @Test
    public void testConnection1() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "sj20010117");
        Connection connection = driver.connect(url, info);
        System.out.println(connection);
    }

    //方式2
    //具有更好的可移植性
    @Test
    public void testConnection2() throws Exception {
        //用反射获取Driver实现类对象
        Class<?> clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "sj20010117");
        Connection connection = driver.connect(url, info);
        System.out.println(connection);
    }

    //方式3 使用DriverManager替代Driver
    @Test
    public void testConnection3() throws Exception {
        Class<?> clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String password = "sj20010117";
        // 注册驱动
        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    //方式4
    @Test
    public void testConnection4() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String password = "sj20010117";
        //mysql的Driver实现类中静态初始化时加载Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    //方式5 将数据库连接需要的4个基本信息声明在配置文件中，通过读取配置文件的方式，获取连接
    @Test
    public void testConnection5() throws Exception {
        InputStream resourceAsStream = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");
        Class.forName(driverClass);
        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println(connection);
    }
}
