package com.example.a20zhinengzhizao1.bean1;

import org.litepal.crud.LitePalSupport;

public class Jbxxsql extends LitePalSupport {
    private String name,sex,zy,xx,email,tel,crjg,jg,xl,gzjl,jyxx,hj,path,user;

    public Jbxxsql() {
    }

    @Override
    public String toString() {
        return "Jbxxsql{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", zy='" + zy + '\'' +
                ", xx='" + xx + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", crjg='" + crjg + '\'' +
                ", jg='" + jg + '\'' +
                ", xl='" + xl + '\'' +
                ", gzjl='" + gzjl + '\'' +
                ", jyxx='" + jyxx + '\'' +
                ", hj='" + hj + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    public String getXx() {
        return xx;
    }

    public void setXx(String xx) {
        this.xx = xx;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCrjg() {
        return crjg;
    }

    public void setCrjg(String crjg) {
        this.crjg = crjg;
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public String getXl() {
        return xl;
    }

    public void setXl(String xl) {
        this.xl = xl;
    }

    public String getGzjl() {
        return gzjl;
    }

    public void setGzjl(String gzjl) {
        this.gzjl = gzjl;
    }

    public String getJyxx() {
        return jyxx;
    }

    public void setJyxx(String jyxx) {
        this.jyxx = jyxx;
    }

    public String getHj() {
        return hj;
    }

    public void setHj(String hj) {
        this.hj = hj;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
