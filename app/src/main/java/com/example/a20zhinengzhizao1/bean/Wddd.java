package com.example.a20zhinengzhizao1.bean;

public class Wddd {
    private String name,jine,tine,zt;

    public Wddd(String name, String jine, String tine) {
        this.name = name;
        this.jine = jine;
        this.tine = tine;
        this.zt = zt;
    }

    @Override
    public String toString() {
        return "Wddd{" +
                "name='" + name + '\'' +
                ", jine='" + jine + '\'' +
                ", tine='" + tine + '\'' +
                ", zt='" + zt + '\'' +
                '}';
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getJine() {
        return jine;
    }

    public void setJine(String jine) {
        this.jine = jine;
    }

    public String getTine() {
        return tine;
    }

    public void setTine(String tine) {
        this.tine = tine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
