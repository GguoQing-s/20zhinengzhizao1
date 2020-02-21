package com.example.a20zhinengzhizao1.bean1;

import org.litepal.crud.LitePalSupport;

public class Automobile extends LitePalSupport {
    private String cjh,scxh,name,xh,sl;

    public Automobile() {
    }

    @Override
    public String toString() {
        return "Automobile{" +
                "cjh='" + cjh + '\'' +
                ", scxh='" + scxh + '\'' +
                ", name='" + name + '\'' +
                ", xh='" + xh + '\'' +
                ", sl='" + sl + '\'' +
                '}';
    }

    public String getCjh() {
        return cjh;
    }

    public void setCjh(String cjh) {
        this.cjh = cjh;
    }

    public String getScxh() {
        return scxh;
    }

    public void setScxh(String scxh) {
        this.scxh = scxh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }
}
