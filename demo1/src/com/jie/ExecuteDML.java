package com.jie;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * ExecuteDML
 *
 * @author J
 * @version 1.0
 * @since 2021/3/17 23:27
 */
public class ExecuteDML {
    private String driver;
    private String username;
    private String password;
    private String url;

    public void initParam(String paramFile) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream(paramFile));
        driver = properties.getProperty("driver");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
        url = properties.getProperty("url");
    }

    public int insertData(String sql) throws Exception {
        Class.forName(driver);
        try (
                Connection connection = DriverManager.getConnection(url,username,password);
                Statement statement = connection.createStatement();

        ){
            return statement.executeUpdate(sql);
        }
    }

    public static void main(String[] args) throws Exception {
        ExecuteDML executeDML = new ExecuteDML();
        executeDML.initParam("D:\\sujieee\\JDBCDemo\\demo1\\src\\com\\jie\\mysql.properties");
        int result = executeDML.insertData("insert into jdbc_test(jdbc_name,jdbc_desc) select s.sname,t.tname from student s,teacher t where s.teacher_id=t.id;");
        System.out.println("系统中一共有"+result+"条记录受影响");
    }
}
