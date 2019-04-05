package com.itsdl.androidtutorials.utils;

import com.itsdl.androidtutorials.adapters.LessonAdapter;

public class LessonItems {
    private  int idLesson;
    private  int idLessonItem;
    private  String lessonItemName;


    public LessonItems(int idLesson, int idLessonItem, String lessonItemName) {
        this.idLesson = idLesson;
        this.idLessonItem = idLessonItem;
        this.lessonItemName = lessonItemName;
    }

    public int getIdLesson() {
        return idLesson;
    }

    public int getIdLessonItem() {
        return idLessonItem;
    }

    public String getLessonItemName() {
        return lessonItemName;
    }

    public void setIdLesson(int idLesson) {
        this.idLesson = idLesson;
    }

    public void setIdLessonItem(int idLessonItem) {
        this.idLessonItem = idLessonItem;
    }

    public void setLessonItemName(String lessonItemName) {
        this.lessonItemName = lessonItemName;
    }
}
