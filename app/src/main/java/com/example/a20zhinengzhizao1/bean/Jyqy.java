package com.example.a20zhinengzhizao1.bean;

import org.litepal.crud.LitePalSupport;

public class Jyqy extends LitePalSupport {
    private String name,xinghao,changshang,chengshi;
    private int path;

    public Jyqy(String name, String xinghao, String changshang, String chengshi, int path) {
        this.name = name;
        this.xinghao = xinghao;
        this.changshang = changshang;
        this.chengshi = chengshi;
        this.path = path;
    }

    @Override
    public String toString() {
        return "Yllb{" +
                "name='" + name + '\'' +
                ", xinghao='" + xinghao + '\'' +
                ", changshang='" + changshang + '\'' +
                ", chengshi='" + chengshi + '\'' +
                ", path=" + path +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXinghao() {
        return xinghao;
    }

    public void setXinghao(String xinghao) {
        this.xinghao = xinghao;
    }

    public String getChangshang() {
        return changshang;
    }

    public void setChangshang(String changshang) {
        this.changshang = changshang;
    }

    public String getChengshi() {
        return chengshi;
    }

    public void setChengshi(String chengshi) {
        this.chengshi = chengshi;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }
}
