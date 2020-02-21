package com.example.a20zhinengzhizao1.bean1;

import org.litepal.crud.LitePalSupport;

public class MaterialA extends LitePalSupport {
    private String name,xinghao,changshang,chengshi;
    private String path;

    public MaterialA() {
    }


    @Override
    public String toString() {
        return "Material{" +
                "name='" + name + '\'' +
                ", xinghao='" + xinghao + '\'' +
                ", path=" + path +
                '}';
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

    public String getXinghao() {
        return xinghao;
    }

    public void setXinghao(String xinghao) {
        this.xinghao = xinghao;
    }
}
