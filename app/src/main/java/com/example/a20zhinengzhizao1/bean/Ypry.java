package com.example.a20zhinengzhizao1.bean;

public class Ypry {
    private String bianhao,name,gw;

    public Ypry(String bianhao, String name, String gw) {
        this.bianhao = bianhao;
        this.name = name;
        this.gw = gw;
    }

    @Override
    public String toString() {
        return "Ypry{" +
                "bianhao='" + bianhao + '\'' +
                ", name='" + name + '\'' +
                ", gw='" + gw + '\'' +
                '}';
    }

    public String getBianhao() {
        return bianhao;
    }

    public void setBianhao(String bianhao) {
        this.bianhao = bianhao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGw() {
        return gw;
    }

    public void setGw(String gw) {
        this.gw = gw;
    }
}
