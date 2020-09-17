package com.linjing.demo01;

import java.sql.*;

public class JDBCDemo01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.加载驱动
        //Driver里的静态代码块，会去注册Driver，主动注册的话则注册了两次，没有必要。
        Class.forName("com.mysql.cj.jdbc.Driver"); //固定写法
        //        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        //2.用户信息和url
        //useUnicode=true&characterEncoding=utf8&useSSL=true 安全连接
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false";
        String username = "root";
        String password = "123456789";

        //3.连接成功，数据库对象, connection代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);

        //4.执行sql的对象, Statement
        Statement statement = connection.createStatement();

        //        statement.executeQuery(); //查询操作返回ResultSet
        //        statement.execute(); //执行任何SQL
        //        statement.executeUpdate(); //更新，插入，删除。返回受影响的行数
        //        statement.executeBatch(); //可以放入多sql一起执行

        //5.执行sql, 可能存在结果，查看返回结果集
        String sql = "SELECT * FROM user";
        ResultSet resultSet = statement.executeQuery(sql); //返回的结果集中封装了查询出来的结果。
        while (resultSet.next()) {  //光标
            System.out.println("id=" + resultSet.getObject("id"));
            System.out.println("name=" + resultSet.getObject("name"));
            System.out.println("email=" + resultSet.getObject("email"));
        }

        //6.释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
