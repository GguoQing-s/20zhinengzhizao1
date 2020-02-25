package com.example.a20zhinengzhizao1.bean1;

import org.litepal.crud.LitePalSupport;

public class Application1 extends LitePalSupport {
    private String bh,gsm,username,path,time;

    public Application1() {
    }

    @Override
    public String toString() {
        return "Application1{" +
                "bh='" + bh + '\'' +
                ", gsm='" + gsm + '\'' +
                ", username='" + username + '\'' +
                ", path='" + path + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
