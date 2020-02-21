package com.example.a20zhinengzhizao1.bean;

public class Wxcjywwx {
    private String clbh,clxh,clwt,bxsj,wxsj,zt;
    private boolean xz;

    public Wxcjywwx(String clbh, String clxh, String clwt, String bxsj, String wxsj, String zt, boolean xz) {
        this.clbh = clbh;
        this.clxh = clxh;
        this.clwt = clwt;
        this.bxsj = bxsj;
        this.wxsj = wxsj;
        this.zt = zt;
        this.xz = xz;
    }

    @Override
    public String toString() {
        return "Wxcjywwx{" +
                "clbh='" + clbh + '\'' +
                ", clxh='" + clxh + '\'' +
                ", clwt='" + clwt + '\'' +
                ", bxsj='" + bxsj + '\'' +
                ", wxsj='" + wxsj + '\'' +
                ", zt='" + zt + '\'' +
                ", xz=" + xz +
                '}';
    }

    public boolean isXz() {
        return xz;
    }

    public void setXz(boolean xz) {
        this.xz = xz;
    }

    public String getClbh() {
        return clbh;
    }

    public void setClbh(String clbh) {
        this.clbh = clbh;
    }

    public String getClxh() {
        return clxh;
    }

    public void setClxh(String clxh) {
        this.clxh = clxh;
    }

    public String getClwt() {
        return clwt;
    }

    public void setClwt(String clwt) {
        this.clwt = clwt;
    }

    public String getBxsj() {
        return bxsj;
    }

    public void setBxsj(String bxsj) {
        this.bxsj = bxsj;
    }

    public String getWxsj() {
        return wxsj;
    }

    public void setWxsj(String wxsj) {
        this.wxsj = wxsj;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }
}
