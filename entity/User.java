package entity;

import java.sql.Timestamp;

public class User {
    Integer id;
    String username;
    String password;
    String email;
    Timestamp time;


    //when register, use this constructor
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        time = new Timestamp(System.currentTimeMillis());
    }


    //when getUser, use this constructor
    public User(String username, String email, Timestamp time) {
        this.username = username;
        this.email = email;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getTime() {
        return time;
    }
}
