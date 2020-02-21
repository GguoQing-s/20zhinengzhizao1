package com.example.a20zhinengzhizao1.bean;

public class Cunuser {
    private String username;

    public Cunuser(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Cunuser{" +
                "username='" + username + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
