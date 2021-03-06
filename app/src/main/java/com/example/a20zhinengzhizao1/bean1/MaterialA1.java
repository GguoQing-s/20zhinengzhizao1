package com.example.a20zhinengzhizao1.bean1;

import org.litepal.crud.LitePalSupport;

public class MaterialA1 extends LitePalSupport {
    private String name,xinghao,changshang,chengshi,path,kcl,wz;

    public MaterialA1() {
    }


    @Override
    public String toString() {
        return "MaterialA{" +
                "name='" + name + '\'' +
                ", xinghao='" + xinghao + '\'' +
                ", changshang='" + changshang + '\'' +
                ", chengshi='" + chengshi + '\'' +
                ", path='" + path + '\'' +
                ", kcl='" + kcl + '\'' +
                ", wz='" + wz + '\'' +
                '}';
    }

    public String getKcl() {
        return kcl;
    }

    public void setKcl(String kcl) {
        this.kcl = kcl;
    }

    public String getWz() {
        return wz;
    }

    public void setWz(String wz) {
        this.wz = wz;
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
