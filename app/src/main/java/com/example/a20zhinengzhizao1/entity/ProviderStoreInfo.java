package com.example.a20zhinengzhizao1.entity;

public class ProviderStoreInfo {
    private int Number;
    private String ProviderName;
    private String Address;
    private String YlName;



    public ProviderStoreInfo() {
    }

    public ProviderStoreInfo(int number, String providerName, String address, String ylName) {
        ProviderName = providerName;
        Address = address;
        YlName = ylName;
        Number=number;
    }

    public String getProviderName() {
        return ProviderName;
    }

    public void setProviderName(String providerName) {
        ProviderName = providerName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getYlName() {
        return YlName;
    }

    public void setYlName(String ylName) {
        YlName = ylName;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }
}
