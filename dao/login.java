package dao;

import entity.User;

import java.sql.*;
import java.util.Scanner;
import static util.DButil.*;


public class login {

    //check whether the username is in the database, if in return true
    public static boolean check_username(User user) throws SQLException{
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "select username from user_info where username = ?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,user.getUsername());
            rs = pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (rs.next()){
            rs.close();
            pstm.close();
            conn.close();
            return true;
        }
        else{
            rs.close();
            pstm.close();
            conn.close();
            return false;
        }
    }

    //check whether the password is in the database, if in return ture
    public static boolean check_passwrod(User user) throws SQLException{
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "select password from user_info where password = ?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,user.getPassword());
            rs = pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (rs.next()){
            rs.close();
            pstm.close();
            conn.close();
            return true;
        }
        else{
            rs.close();
            pstm.close();
            conn.close();
            return false;
        }
    }


    //register a new user, if success return 0, if username already used return 409, if causing error return -1
    public static int register(User user) throws SQLException{

        //check no duplicate username
        if (check_username(user)){
            System.out.println("username has been used");
            return 409;
        }

        try {
            String sql = "insert into user_info(username,password,email,time) values(?,?,?,?)";
            Object[] obj = {user.getUsername(), user.getPassword(), user.getEmail(), user.getTime()};
            exeUpdate(sql, obj);
            System.out.println("Register successfully!");
            return 0;
        } catch (Throwable e){
            return -1;
        }

    }
//-----------------------------------------------------------------------------------------------------
    //login function, return the user's user_id
    public static int logging() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = scanner.next();
        System.out.println("Enter your password: ");
        String password = scanner.next();

        while(!check_username(username) || !check_passwrod(password)){
            System.out.println("Wrong username or password!");
            System.out.println("Renter your username: ");
            username = scanner.next();
            System.out.println("Renter your password: ");
            password = scanner.next();
        }

        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "select user_id from login_info where username = ?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,username);
            rs = pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Login success!");
        rs.next();
        int id = rs.getInt(1);
        rs.close();
        pstm.close();
        conn.close();
        return id;
    }
}
