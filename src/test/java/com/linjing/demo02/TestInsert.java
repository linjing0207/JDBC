package com.linjing.demo02;

import com.linjing.demo02.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsert {
    public static void main(String[] args) {
        //JDBCUtils静态方法块已经做好了mysql驱动加载

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtils.getConnection(); //获取数据库连接
            statement = connection.createStatement();
            String sql = "INSERT INTO user (id, name, email) VALUES (5, 'kris', 'kris@gmail.com')";
            int updateRow = statement.executeUpdate(sql);
            if (updateRow > 0) {
                System.out.println("插入成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection, statement, null);
        }

    }
}
