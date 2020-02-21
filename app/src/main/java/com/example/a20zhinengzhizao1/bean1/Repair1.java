package com.example.a20zhinengzhizao1.bean1;

import org.litepal.crud.LitePalSupport;

public class Repair1 extends LitePalSupport {
    private String clbh,clxh,clwt,bxsj,wxsj,zt;

    public Repair1() {
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
