package com.example.a20zhinengzhizao1.bean;

public class Noyw {
    private String name;

    public Noyw(String name) {
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
