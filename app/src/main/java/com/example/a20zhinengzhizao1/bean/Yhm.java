package com.example.a20zhinengzhizao1.bean;

public class Yhm {
    private String user;

    public Yhm(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Yhm{" +
                "user='" + user + '\'' +
                '}';
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
