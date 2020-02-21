package com.example.a20zhinengzhizao1.bean;

public class Ygxx {
    private String name,sex,csrq,tel,scx,zw,email,jtzz;

    public Ygxx(String name, String sex, String csrq, String tel, String scx, String zw, String email, String jtzz) {
        this.name = name;
        this.sex = sex;
        this.csrq = csrq;
        this.tel = tel;
        this.scx = scx;
        this.zw = zw;
        this.email = email;
        this.jtzz = jtzz;
    }

    @Override
    public String toString() {
        return "Ygxx{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", csrq='" + csrq + '\'' +
                ", tel='" + tel + '\'' +
                ", scx='" + scx + '\'' +
                ", zw='" + zw + '\'' +
                ", email='" + email + '\'' +
                ", jtzz='" + jtzz + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getScx() {
        return scx;
    }

    public void setScx(String scx) {
        this.scx = scx;
    }

    public String getZw() {
        return zw;
    }

    public void setZw(String zw) {
        this.zw = zw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJtzz() {
        return jtzz;
    }

    public void setJtzz(String jtzz) {
        this.jtzz = jtzz;
    }
}
