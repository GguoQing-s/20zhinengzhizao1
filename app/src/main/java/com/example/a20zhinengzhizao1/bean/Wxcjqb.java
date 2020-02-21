package com.example.a20zhinengzhizao1.bean;

import org.litepal.crud.LitePalSupport;

public class Wxcjqb  {
    private String clbh,clxh,clwt,bxsj,wxsj,zt;

    public Wxcjqb(String clbh, String clxh, String clwt, String bxsj, String wxsj, String zt) {
        this.clbh = clbh;
        this.clxh = clxh;
        this.clwt = clwt;
        this.bxsj = bxsj;
        this.wxsj = wxsj;
        this.zt = zt;
    }

    @Override
    public String toString() {
        return "Wxcj{" +
                "clbh='" + clbh + '\'' +
                ", clxh='" + clxh + '\'' +
                ", clwt='" + clwt + '\'' +
                ", bxsj='" + bxsj + '\'' +
                ", wxsj='" + wxsj + '\'' +
                ", zt='" + zt + '\'' +
                '}';
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
