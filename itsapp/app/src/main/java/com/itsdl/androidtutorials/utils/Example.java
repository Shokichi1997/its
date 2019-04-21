package com.itsdl.androidtutorials.utils;

public class Example {
    private String name;
    private String java_code;
    private String xml_code;
    private int icon;

    public Example(String name, String java_code, String xml_code,int icon) {
        this.name = name;
        this.java_code = java_code;
        this.xml_code = xml_code;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }
}
