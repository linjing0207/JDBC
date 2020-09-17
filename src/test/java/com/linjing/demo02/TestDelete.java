package com.linjing.demo02;

import com.linjing.demo02.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDelete {
    public static void main(String[] args) {
        //JDBCUtils静态方法块已经做好了mysql驱动加载

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtils.getConnection(); //获取数据库连接
            statement = connection.createStatement();
            String sql = "DELETE FROM user WHERE id = 4";
            int updateRow = statement.executeUpdate(sql);
            if (updateRow > 0) {
                System.out.println("删除成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection, statement, null);
        }

    }
}
