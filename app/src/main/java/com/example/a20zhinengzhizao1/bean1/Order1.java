package com.example.a20zhinengzhizao1.bean1;

import org.litepal.crud.LitePalSupport;

public class Order1 extends LitePalSupport {
    private String name,jine,time;

    public Order1() {
    }

    @Override
    public String toString() {
        return "Order1{" +
                "name='" + name + '\'' +
                ", jine='" + jine + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJine() {
        return jine;
    }

    public void setJine(String jine) {
        this.jine = jine;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
