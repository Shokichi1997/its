package com.itsdl.androidtutorials.utils;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ProfileUser {
    @SerializedName("student_code")
    public String student_code;
    @SerializedName("full_name")
    public String full_name;
    @SerializedName("picture")
    public String picture;
    @SerializedName("email")
    public String email;
    @SerializedName("date_create")
    public Date date_create;
    @SerializedName("gender")
    public int gender;
    @SerializedName("user_id")
    public int user_id;
    @SerializedName("role")
    public int role;
    public String getStudent_code() {
        return student_code;
    }

    
    public String getPicture() {
        return picture;
    }

    public String getEmail() {
        return email;
    }

    public Date getDate_create() {
        return date_create;
    }

    public int getGender() {
        return gender;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setStudent_code(String student_code) {
        this.student_code = student_code;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDate_create(Date date_create) {
        this.date_create = date_create;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getFull_name() {
        return full_name;
    }
    public int getRole() {
        return role;
    }
     public void setRole(int role) {
        this.role = role;
    }


        private static ProfileUser myObj;
         /**
         * Create private constructor
         */
        private ProfileUser(){

        }
        /**
         * Create a static method to get instance.
         */
        public static ProfileUser getInstance(){
            if(myObj == null){
                myObj = new ProfileUser();
            }
            return myObj;
        }

        public void getSomeThing(){
            // do something here
            System.out.println("I am here....");
        }
        
}
