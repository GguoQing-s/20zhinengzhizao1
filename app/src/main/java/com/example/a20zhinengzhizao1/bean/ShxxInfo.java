package com.example.a20zhinengzhizao1.bean;

public class ShxxInfo {
    private String bh;
    private String com_address;
    private String gw;
    private boolean check;

    public ShxxInfo(String bh, String com_address, String gw, boolean check) {
        this.bh = bh;
        this.com_address = com_address;
        this.gw = gw;
        this.check = check;
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

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
