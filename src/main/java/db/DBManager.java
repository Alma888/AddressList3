package db;

import java.sql.*;

public class DBManager {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//加载驱动
            String url = "jdbc:mysql://localhost:3306/xiangmu1?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";//设置要连接的数据库
            String username = "root";//要连接数据库的用户名
            String password = "";//要连接数据库的密码
            connection = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConnection(){
        return connection;
    }

    //释放资源
    public static void close(Connection conn, PreparedStatement ps, ResultSet resultSet){
        try{
            if (resultSet!=null){
                resultSet.close();
            }
            ps.close();
            conn.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
