package com.example.a20zhinengzhizao1.bean1;

import org.litepal.crud.LitePalSupport;

public class Tjyl extends LitePalSupport {
    private String gysbh,ylmc,ylbh,jg,path;

    public Tjyl() {
    }


    public String getGysbh() {
        return gysbh;
    }

    public void setGysbh(String gysbh) {
        this.gysbh = gysbh;
    }

    public String getYlmc() {
        return ylmc;
    }

    public void setYlmc(String ylmc) {
        this.ylmc = ylmc;
    }

    public String getYlbh() {
        return ylbh;
    }

    public void setYlbh(String ylbh) {
        this.ylbh = ylbh;
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
