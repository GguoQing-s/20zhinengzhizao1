package com.example.a20zhinengzhizao1.sql;

import org.litepal.crud.LitePalSupport;

public class YlInfor  extends LitePalSupport {
    private String Name;
    private String Location;
    private  int Bh;
    private String Xh;
    private int Count;
    private byte[] Photo;

    public YlInfor() {
    }

    public YlInfor(String name, String location, int bh, String xh, int count, byte[] photo) {
        Name = name;
        Location = location;
        Bh = bh;
        Xh = xh;
        Count = count;
        Photo = photo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getBh() {
        return Bh;
    }

    public void setBh(int bh) {
        Bh = bh;
    }

    public String getXh() {
        return Xh;
    }

    public void setXh(String xh) {
        Xh = xh;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public byte[] getPhoto() {
        return Photo;
    }

    public void setPhoto(byte[] photo) {
        Photo = photo;
    }
}
