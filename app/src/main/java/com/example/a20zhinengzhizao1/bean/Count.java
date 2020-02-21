package com.example.a20zhinengzhizao1.bean;

public class Count {
    private String name,xh,sl;

    public Count(String name, String xh, String sl) {
        this.name = name;
        this.xh = xh;
        this.sl = sl;
    }

    @Override
    public String toString() {
        return "Count{" +
                "name='" + name + '\'' +
                ", xh='" + xh + '\'' +
                ", sl='" + sl + '\'' +
                '}';
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
