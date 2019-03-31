package com.itsdl.androidtutorials.utils;

public class Lesson {
   private int idChapTer;
   private int idLesson;
   private int iconLesson;
   private boolean isLock;
   private String lessonName;

    public Lesson(int idChapTer, int idLesson, int iconLesson, boolean isLock, String lessonName) {
        this.idChapTer = idChapTer;
        this.idLesson = idLesson;
        this.iconLesson = iconLesson;
        this.isLock = isLock;
        this.lessonName = lessonName;
    }

    public int getIdChapTer() {
        return idChapTer;
    }

    public int getIdLesson() {
        return idLesson;
    }

    public int getIconLesson() {
        return iconLesson;
    }

    public boolean isLock() {
        return isLock;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setIdChapTer(int idChapTer) {
        this.idChapTer = idChapTer;
    }

    public void setIdLesson(int idLesson) {
        this.idLesson = idLesson;
    }

    public void setIconLesson(int iconLesson) {
        this.iconLesson = iconLesson;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }
}
