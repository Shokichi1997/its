package com.itsdl.androidtutorials.utils;

import java.util.ArrayList;

public class Lesson {
   private int chapter_id;
   private int lesson_id;
   private String lesson_name;
   private ArrayList<LessonItems> lesson_item_list = null;

    public Lesson(int chapter_id, int lesson_id, String lesson_name) {
        this.chapter_id = chapter_id;
        this.lesson_id = lesson_id;
        this.lesson_name = lesson_name;
        this.lesson_item_list = new ArrayList<>();
    }

    public Lesson(int lesson_id, String lesson_name) {
        this.lesson_id = lesson_id;
        this.lesson_name = lesson_name;
    }

    public void setLesson_item_list(ArrayList<LessonItems> lesson_item_list) {
        this.lesson_item_list = lesson_item_list;
    }

    public ArrayList<LessonItems> getLesson_item_list() {
        return lesson_item_list;
    }

    public int getChapter_id() {
        return chapter_id;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }
}
