package com.example.a20zhinengzhizao1.bean;

public class HistoryInfo {
    private String bh;
    private String com_address;
    private String gw;
    private String name;
    private String  time;

    public HistoryInfo(String bh, String com_address, String gw, String name, String time) {
        this.bh = bh;
        this.com_address = com_address;
        this.gw = gw;
        this.name = name;
        this.time = time;
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getCom_address() {
        return com_address;
    }

    public void setCom_address(String com_address) {
        this.com_address = com_address;
    }

    public String getGw() {
        return gw;
    }

    public void setGw(String gw) {
        this.gw = gw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
