package com.jie.demo1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class ExecuteSQL {
    public void executeSQL(String sql) throws Exception {
        try (
                Connection connection = ConnUtils.getConnection();
                Statement statement = connection.createStatement();
        ) {
            boolean hasResultSet = statement.execute(sql);
            if (hasResultSet) {
                try (
                        ResultSet rs = statement.getResultSet()
                ) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                    while (rs.next()) {
                        for (int i = 0; i < columnCount; i++) {
                            System.out.println(rs.getString(i+1)+"\t");
                        }
                        System.out.println("\n");
                    }
                }
            } else {
                System.out.println("该SQL语句影响的记录有"+statement.getUpdateCount()+"条");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ExecuteSQL es = new ExecuteSQL();
        System.out.println("执行删除表的DDL语句");
        es.executeSQL("drop table if exists my_test");
        System.out.println("执行建表的DDL语句");
        es.executeSQL("create table my_test(test_id int auto_increment primary key,test_name varchar(255))");
        System.out.println("执行插入数据的DML语句");
        es.executeSQL("insert into my_test(test_name) select name from student");
        System.out.println("执行查询数据的查询语句");
        es.executeSQL("select * from my_test");
    }
}
