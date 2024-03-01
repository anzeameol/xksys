package xkpackage.utils;

import com.sun.deploy.panel.IProperty;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class JDBCUtil {
    private static Properties properties = new Properties();
    static {
        try {
            InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataSource getDataSourse(){
        try {
            return BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//    private static String driver = "com.mysql.jdbc.Driver";
//    private static String url = "jdbc:mysql://localhost:3306/xk?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
//    private static String username = "root";
//    private static String password = "13579adgjl";
//    public static Connection getConnection() {
//        /**
//         * 创建连接对象
//         */
//        Connection conn=null;
//        try {
//            conn=(Connection) DriverManager.getConnection(url,username,password);
//        }catch(Exception ex) {
//            ex.printStackTrace();
//        }
//        return conn;
//    }
//    /**
//     * 关闭相关流
//     */
//    public static void close(ResultSet rs,Statement st,Connection conn) {
//        try {
//            if(rs!=null) {
//                rs.close();
//            }
//            if(st!=null) {
//                st.close();
//            }
//            if(conn!=null) {
//                conn.close();
//            }
//        }catch(SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void closePst(ResultSet rs,PreparedStatement pst,Connection conn) {
//        try {
//            if(rs!=null) {
//                rs.close();
//            }
//            if(pst!=null) {
//                pst.close();
//            }
//            if(conn!=null) {
//                conn.close();
//            }
//        }catch(SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
