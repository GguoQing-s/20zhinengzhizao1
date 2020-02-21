package com.example.a20zhinengzhizao1.bean;

public class Zhaopin {
    private String name,type,name1,yaoqiu,chengshi,xiuli,xinzi,email,time;
    private boolean xz,sc;

    public Zhaopin(String name, String type, String name1, String yaoqiu, String chengshi, String xiuli, String xinzi, String email, String time, boolean xz, boolean sc) {
        this.name = name;
        this.type = type;
        this.name1 = name1;
        this.yaoqiu = yaoqiu;
        this.chengshi = chengshi;
        this.xiuli = xiuli;
        this.xinzi = xinzi;
        this.email = email;
        this.time = time;
        this.xz = xz;
        this.sc = sc;
    }

    @Override
    public String toString() {
        return "Zhaopin{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", name1='" + name1 + '\'' +
                ", yaoqiu='" + yaoqiu + '\'' +
                ", chengshi='" + chengshi + '\'' +
                ", xiuli='" + xiuli + '\'' +
                ", xinzi='" + xinzi + '\'' +
                ", email='" + email + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public boolean isSc() {
        return sc;
    }

    public void setSc(boolean sc) {
        this.sc = sc;
    }

    public boolean isXz() {
        return xz;
    }

    public void setXz(boolean xz) {
        this.xz = xz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getYaoqiu() {
        return yaoqiu;
    }

    public void setYaoqiu(String yaoqiu) {
        this.yaoqiu = yaoqiu;
    }

    public String getChengshi() {
        return chengshi;
    }

    public void setChengshi(String chengshi) {
        this.chengshi = chengshi;
    }

    public String getXiuli() {
        return xiuli;
    }

    public void setXiuli(String xiuli) {
        this.xiuli = xiuli;
    }

    public String getXinzi() {
        return xinzi;
    }

    public void setXinzi(String xinzi) {
        this.xinzi = xinzi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
