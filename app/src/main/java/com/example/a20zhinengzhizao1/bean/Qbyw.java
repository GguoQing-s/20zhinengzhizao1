package com.example.a20zhinengzhizao1.bean;

public class Qbyw {
    private String name;

    public Qbyw(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Noyw{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
