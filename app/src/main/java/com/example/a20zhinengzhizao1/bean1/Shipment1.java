package com.example.a20zhinengzhizao1.bean1;

import org.litepal.crud.LitePalSupport;

public class Shipment1 extends LitePalSupport {
    private String name,xh,shuliang,time,chr,jsr,qx,path,scx;

    public Shipment1() {
    }


    @Override
    public String toString() {
        return "Shipment{" +
                "name='" + name + '\'' +
                ", xh='" + xh + '\'' +
                ", shuliang='" + shuliang + '\'' +
                ", time='" + time + '\'' +
                ", chr='" + chr + '\'' +
                ", jsr='" + jsr + '\'' +
                ", qx='" + qx + '\'' +
                '}';
    }

    public String getScx() {
        return scx;
    }

    public void setScx(String scx) {
        this.scx = scx;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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


    public String getShuliang() {
        return shuliang;
    }

    public void setShuliang(String shuliang) {
        this.shuliang = shuliang;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getChr() {
        return chr;
    }

    public void setChr(String chr) {
        this.chr = chr;
    }

    public String getJsr() {
        return jsr;
    }

    public void setJsr(String jsr) {
        this.jsr = jsr;
    }

    public String getQx() {
        return qx;
    }

    public void setQx(String qx) {
        this.qx = qx;
    }
}
