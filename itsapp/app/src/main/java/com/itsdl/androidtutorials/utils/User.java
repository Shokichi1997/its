package com.itsdl.androidtutorials.utils;

import android.provider.ContactsContract;

import java.util.Date;

public class User {
    private int user_id;
    private String full_name;
    private String email;
    private int role;
    private String student_code;
    private String avatar;
    private Date date_create;

    //HAM TAO
    public User(int user_id, String full_name, String email, int role, String student_code, String avatar, Date date_create) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.email = email;
        this.role = role;
        this.student_code = student_code;
        this.avatar = avatar;
        this.date_create = date_create;
    }

    public  User(){};

   //PHUONG THUC GET
    public int getUser_id() {
        return user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getEmail() {
        return email;
    }

    public int getRole() {
        return role;
    }

    public String getStudent_code() {
        return student_code;
    }

    public String getAvatar() {
        return avatar;
    }

    public Date getDate_create() {
        return date_create;
    }
    //PHUONG THUC SET

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setStudent_code(String student_code) {
        this.student_code = student_code;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setDate_create(Date date_create) {
        this.date_create = date_create;
    }
}
