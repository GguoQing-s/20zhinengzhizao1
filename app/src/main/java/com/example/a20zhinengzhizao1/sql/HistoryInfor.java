package com.example.a20zhinengzhizao1.sql;

import org.litepal.crud.LitePalSupport;

public class HistoryInfor extends LitePalSupport {
    private String Time;
    private String Provider;
    private int Count;
    private double Price;
    private double Total;
    private String Name;
    private String Bz;
    private int surplus;

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getProvider() {
        return Provider;
    }

    public void setProvider(String provider) {
        Provider = provider;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBz() {
        return Bz;
    }

    public void setBz(String bz) {
        Bz = bz;
    }

    public int getSurplus() {
        return surplus;
    }

    public void setSurplus(int surplus) {
        this.surplus = surplus;
    }

    public HistoryInfor() {
    }

    public HistoryInfor(String time, String provider, int count, double price, double total, String name, String bz, int surplus) {
        Time = time;
        Provider = provider;
        Count = count;
        Price = price;
        Total = total;
        Name = name;
        Bz = bz;
        this.surplus = surplus;
    }
}
