package com.example.a20zhinengzhizao1.bean_z;

import org.litepal.crud.LitePalSupport;

/**
 * Create by 张瀛煜 on 2020-02-23 ：）
 */
public class YLYZ  extends LitePalSupport {
    private int id;
    private String name,num;
    private String path;
    private int number;

    public YLYZ() {
    }

    public YLYZ(String name, String num, String path, int number) {
        this.name = name;
        this.num = num;
        this.path = path;
        this.number = number;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
