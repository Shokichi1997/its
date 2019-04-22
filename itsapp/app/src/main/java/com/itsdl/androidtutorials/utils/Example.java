package com.itsdl.androidtutorials.utils;


public class Example {
    private int id;
    private String name;
    private String icon;

    public Example(int id, String name,String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }
}
