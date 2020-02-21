package com.example.a20zhinengzhizao1.bean;

public class Jbxx1 {
    private String name,sex,zy,school,emial,tel,shengqi,jg,xl,jl,yx,hj,image;

    public Jbxx1(String name, String sex, String zy, String school, String emial, String tel, String shengqi, String jg, String xl, String jl, String yx, String hj, String image) {
        this.name = name;
        this.sex = sex;
        this.zy = zy;
        this.school = school;
        this.emial = emial;
        this.tel = tel;
        this.shengqi = shengqi;
        this.jg = jg;
        this.xl = xl;
        this.jl = jl;
        this.yx = yx;
        this.hj = hj;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Jbxx1{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", zy='" + zy + '\'' +
                ", school='" + school + '\'' +
                ", emial='" + emial + '\'' +
                ", tel='" + tel + '\'' +
                ", shengqi='" + shengqi + '\'' +
                ", jg='" + jg + '\'' +
                ", xl='" + xl + '\'' +
                ", jl='" + jl + '\'' +
                ", yx='" + yx + '\'' +
                ", hj='" + hj + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEmial() {
        return emial;
    }

    public void setEmial(String emial) {
        this.emial = emial;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getShengqi() {
        return shengqi;
    }

    public void setShengqi(String shengqi) {
        this.shengqi = shengqi;
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

    public String getJl() {
        return jl;
    }

    public void setJl(String jl) {
        this.jl = jl;
    }

    public String getYx() {
        return yx;
    }

    public void setYx(String yx) {
        this.yx = yx;
    }

    public String getHj() {
        return hj;
    }

    public void setHj(String hj) {
        this.hj = hj;
    }

}
