package com.itsdl.androidtutorials.utils;

public class Lesson {
   private int chapter_id;
   private int lesson_id;
   private String lesson_icon;
   private String lesson_name;

    public Lesson(int chapter_id, int lesson_id, String lesson_icon, String lesson_name) {
        this.chapter_id = chapter_id;
        this.lesson_id = lesson_id;
        this.lesson_icon = lesson_icon;
        this.lesson_name = lesson_name;
    }

    public Lesson(int lesson_id, String lesson_name) {
        this.lesson_id = lesson_id;
        this.lesson_name = lesson_name;
    }

    public int getChapter_id() {
        return chapter_id;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public String getLesson_icon() {
        return lesson_icon;
    }


    public String getLesson_name() {
        return lesson_name;
    }

    public void setChapter_id(int chapter_id) {
        this.chapter_id = chapter_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public void setLesson_icon(String lesson_icon) {
        this.lesson_icon = lesson_icon;
    }


    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }
}
