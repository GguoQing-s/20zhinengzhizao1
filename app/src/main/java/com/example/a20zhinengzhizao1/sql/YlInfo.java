package com.example.a20zhinengzhizao1.sql;

import org.litepal.crud.LitePalSupport;

public class YlInfo extends LitePalSupport {
    private int ProviderNumber;
    private int Ylnumber;
    private String YlName;
    private byte[] YlPhoto;
    private double Price;

    public YlInfo(int providerNumber, int ylnumber, String ylName, byte[] ylPhoto, double price) {
        ProviderNumber = providerNumber;
        Ylnumber = ylnumber;
        YlName = ylName;
        YlPhoto = ylPhoto;
        Price = price;
    }

    public YlInfo() {
    }

    public int getProviderNumber() {
        return ProviderNumber;
    }

    public void setProviderNumber(int providerNumber) {
        ProviderNumber = providerNumber;
    }

    public int getYlnumber() {
        return Ylnumber;
    }

    public void setYlnumber(int ylnumber) {
        Ylnumber = ylnumber;
    }

    public String getYlName() {
        return YlName;
    }

    public void setYlName(String ylName) {
        YlName = ylName;
    }

    public byte[] getYlPhoto() {
        return YlPhoto;
    }

    public void setYlPhoto(byte[] ylPhoto) {
        YlPhoto = ylPhoto;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}
