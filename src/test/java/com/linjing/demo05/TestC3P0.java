package com.linjing.demo05;

import com.linjing.demo05.utils.JDBCUtils_C3P0;
import com.linjing.demo05.utils.JDBCUtils_DBCP;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestC3P0 {
    public static void main(String[] args) {
        //JDBCUtils静态方法块已经做好了mysql驱动加载

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = JDBCUtils_C3P0.getConnection(); //获取数据库连接，原来是自己实现的，现在用别人实现的
            statement = conn.createStatement();
            String sql = "INSERT INTO user (id, name, email) VALUES (4, 'kris', 'kris@gmail.com')";
            int updateRow = statement.executeUpdate(sql);
            if (updateRow > 0) {
                System.out.println("插入成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils_DBCP.release(conn, statement, null);
        }

    }
}
