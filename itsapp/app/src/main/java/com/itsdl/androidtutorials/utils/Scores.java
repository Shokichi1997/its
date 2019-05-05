package com.itsdl.androidtutorials.utils;

public class Scores {
    int lesson_id;
    String lesson_name;
    double score;
    int user_id;
    public Scores() {
    }

    public Scores(int lesson_id, String lesson_name, double score,int user_id) {
        this.lesson_id = lesson_id;
        this.lesson_name = lesson_name;
        this.score = score;
        this.user_id=user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    //getter
    public int getLesson_id() {
        return lesson_id;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public double getScore() {
        return score;
    }
    //seteer

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
