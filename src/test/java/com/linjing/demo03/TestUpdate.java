package com.linjing.demo03;

import com.linjing.demo02.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestUpdate {
    public static void main(String[] args) {
        //JDBCUtils静态方法块已经做好了mysql驱动加载

        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtils.getConnection(); //获取数据库连接
            String sql = "UPDATE user SET name = ?, email= ? WHERE id = ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1, "lulu");
            pst.setString(2, "lulu@gmail.com");
            pst.setInt(3, 1);

            int updateRow = pst.executeUpdate();
            if (updateRow > 0) {
                System.out.println("更新成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection, pst, null);
        }

    }
}
