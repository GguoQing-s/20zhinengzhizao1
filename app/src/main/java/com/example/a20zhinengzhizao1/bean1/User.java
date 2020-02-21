package com.example.a20zhinengzhizao1.bean1;

import org.litepal.crud.LitePalSupport;

public class User extends LitePalSupport {
    private String username,password,email,jine;

    public User() {
    }

    public User(String username, String password, String email, String jine) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.jine = jine;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", jine='" + jine + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJine() {
        return jine;
    }

    public void setJine(String jine) {
        this.jine = jine;
    }

}
