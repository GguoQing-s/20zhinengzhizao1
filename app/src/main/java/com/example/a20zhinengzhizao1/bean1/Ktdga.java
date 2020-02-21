package com.example.a20zhinengzhizao1.bean1;

import org.litepal.crud.LitePalSupport;

public class Ktdga extends LitePalSupport {
    private String bianhao,kt,dg,dushu,ms;

    public Ktdga() {
    }

    @Override
    public String toString() {
        return "Ktdg{" +
                "bianhao='" + bianhao + '\'' +
                ", kt='" + kt + '\'' +
                ", dg='" + dg + '\'' +
                ", dushu='" + dushu + '\'' +
                ", ms='" + ms + '\'' +
                '}';
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getBianhao() {
        return bianhao;
    }

    public void setBianhao(String bianhao) {
        this.bianhao = bianhao;
    }

    public String getKt() {
        return kt;
    }

    public void setKt(String kt) {
        this.kt = kt;
    }

    public String getDg() {
        return dg;
    }

    public void setDg(String dg) {
        this.dg = dg;
    }

    public String getDushu() {
        return dushu;
    }

    public void setDushu(String dushu) {
        this.dushu = dushu;
    }
}
