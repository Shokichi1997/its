package com.itsdl.androidtutorials.utils;

public class Answer {
    private int answer_id;
    private String answer_content;
    private int result;


    public Answer(int answer_id, String answer_content, int result) {
        this.answer_id = answer_id;
        this.answer_content = answer_content;
        this.result = result;
    }

    public String getContent() {
        return answer_content;
    }

    public int getAnswer_id() {
        return answer_id;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
