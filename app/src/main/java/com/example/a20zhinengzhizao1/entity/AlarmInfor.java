package com.example.a20zhinengzhizao1.entity;

public class AlarmInfor {
    private String Name;
    private String Xh;
    private  int count;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getXh() {
        return Xh;
    }

    public void setXh(String xh) {
        Xh = xh;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public AlarmInfor() {
    }

    public AlarmInfor(String name, String xh, int count) {
        Name = name;
        Xh = xh;
        this.count = count;
    }
}
