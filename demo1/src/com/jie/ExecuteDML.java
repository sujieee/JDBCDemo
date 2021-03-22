package com.jie;

import java.sql.Statement;

/**
 * ExecuteDML
 *
 * @author J
 * @version 1.0
 * @since 2021/3/17 23:27
 */
public class ExecuteDML {
    public static void main(String[] args) throws Exception {
        try (
                Statement statement = ConnUtils.createState()
        ) {
            int result = statement.executeUpdate("insert into jdbc_test(jdbc_name,jdbc_desc) select s.name,t.name from student s,teacher t where s.teacher_id=t.id;");
            System.out.println("系统中一共有" + result + "条记录受影响");
        }
    }
}
