package util;

import java.sql.*;

public class DButil {

    private static final String URL = "jdbc:mysql://localhost:3306/secondhandplatform";
    private static final String NAME = "root";
    private static final String PASSWORD = "12345";

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeAll(PreparedStatement pstm, Connection conn, ResultSet rs){

        try {
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(pstm!=null){
                pstm.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public   static   int    exeUpdate(String  sql,Object[] obj){
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        int update=0;

        try {
            pstm = conn.prepareStatement(sql);

            for (int i = 0; i <obj.length; i++) {
                pstm.setObject(i+1,obj[i]);
            }
            update = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(pstm,conn,null);
        }
        return  update;

    }

}





