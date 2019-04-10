package com.itsdl.androidtutorials.utils;

import java.util.Date;

public class User {
    private int studenCode;
    private String studentName;
    private Date birtDay;
    private int gender;
    private String email;

    public User(int studenCode, String studentName, Date birtDay, int gender, String email) {
        this.studenCode = studenCode;
        this.studentName = studentName;
        this.birtDay = birtDay;
        this.gender = gender;
        this.email = email;
    }

    public int getStudenCode() {
        return studenCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public Date getBirtDay() {
        return birtDay;
    }

    public int getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }


    //set

    public void setStudenCode(int studenCode) {
        this.studenCode = studenCode;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setBirtDay(Date birtDay) {
        this.birtDay = birtDay;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
