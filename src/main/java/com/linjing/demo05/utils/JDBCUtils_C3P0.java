package com.linjing.demo05.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils_C3P0 {

    private static ComboPooledDataSource dataSource = null;

    static {
        try {
            //创建数据源 工厂模式 -》创建
            //xml配置会自动匹配

            //代码版配置(不建议，比较麻烦)
            //            dataSource = new ComboPooledDataSource();
            //            dataSource.setDriverClass();
            //            dataSource.setJdbcUrl();
            //            dataSource.setUser();
            //            dataSource.setPassword();
            //
            //            dataSource.setMaxPoolSize();
            //            dataSource.setMinPoolSize();


            //配置文件写法
            dataSource = new ComboPooledDataSource("MySQL");



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConnection() throws SQLException {
        //接口方法
        return dataSource.getConnection(); //从数据源中获取连接
    }

    //释放连接
    public static void release(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
