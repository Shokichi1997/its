package com.itsdl.androidtutorials.utils;

public class ProfileUser {
    private String email;

    private static final ProfileUser ourInstance = new ProfileUser();

    public static ProfileUser getInstance() {
        return ourInstance;
    }

    private ProfileUser() {
    }
}
