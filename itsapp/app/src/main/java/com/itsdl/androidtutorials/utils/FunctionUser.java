package com.itsdl.androidtutorials.utils;

public class FunctionUser {
    private static final int NO_IMAGE_PROViDED = -1;
    private int function_id;
    private String function_name;
    private boolean role;
    private int imageResourceId = NO_IMAGE_PROViDED;



    public FunctionUser(int function_id, String function_name, boolean role, int imageResourceId) {
        this.function_id = function_id;
        this.function_name = function_name;
        this.role = role;
        this.imageResourceId = imageResourceId;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getFunction_id() {
        return function_id;
    }

    public String getFunction_name() {
        return function_name;
    }

    public boolean isRole() {
        return role;
    }

    public void setFunction_id(int function_id) {
        this.function_id = function_id;
    }

    public void setFunction_name(String function_name) {
        this.function_name = function_name;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public boolean hasImage(){
        return imageResourceId != NO_IMAGE_PROViDED;
    }
}
