package com.jie;

import java.sql.Statement;

/**
 * ExecuteDDL
 *
 * @author J
 * @version 1.0
 * @since 2021/3/17 23:01
 */
public class ExecuteDDL {
    public static void main(String[] args) throws Exception {
        try (
                Statement statement = ConnUtils.createState()
        ) {
            statement.executeUpdate("create table jdbc_test(jdbc_id int auto_increment primary key,jdbc_name varchar(255),jdbc_desc text);");
            System.out.println("建表成功");
        }
    }

}
