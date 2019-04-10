package com.itsdl.androidtutorials.utils;

import java.util.ArrayList;

public class Question {

    private int question_id;
    private String content;
    private int type_qs;
    private ArrayList<Answer> answers = null;

    public Question(int question_id, String content, int type_qs) {
        this.question_id = question_id;
        this.content = content;
        this.type_qs = type_qs;
        this.answers = new ArrayList<>();
    }


    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public String getContent() {
        return content;
    }

    public int getType_qs() {
        return type_qs;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }
}
