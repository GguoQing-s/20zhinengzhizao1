package com.example.a20zhinengzhizao1.bean;

public class WdjlInfo {
    private String name;
    private String bz;
    private String time;
    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public WdjlInfo(String name, String bz, String time, String path) {
        this.name = name;
        this.bz = bz;
        this.time = time;
        this.path=path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
