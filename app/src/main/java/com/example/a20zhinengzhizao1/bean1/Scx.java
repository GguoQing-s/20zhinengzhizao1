package com.example.a20zhinengzhizao1.bean1;

import org.litepal.crud.LitePalSupport;

public class Scx extends LitePalSupport {
    private String cjm,scxm,zt,hj,ts;

    public Scx() {
    }

    @Override
    public String toString() {
        return "Scx{" +
                "cjm='" + cjm + '\'' +
                ", scxm='" + scxm + '\'' +
                ", zt='" + zt + '\'' +
                ", hj='" + hj + '\'' +
                ", ts='" + ts + '\'' +
                '}';
    }

    public String getCjm() {
        return cjm;
    }

    public void setCjm(String cjm) {
        this.cjm = cjm;
    }

    public String getScxm() {
        return scxm;
    }

    public void setScxm(String scxm) {
        this.scxm = scxm;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getHj() {
        return hj;
    }

    public void setHj(String hj) {
        this.hj = hj;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }
}
