package com.itsdl.androidtutorials.utils;

public class ChapterLesson {
    private static final int NO_IMAGE_PROViDED = -1;
    private int iconChapter = NO_IMAGE_PROViDED;
    private String nameChapter;
    private boolean isLock;
    private int idChapter;

    public ChapterLesson(int iconChapter, String nameChapter, boolean isLock, int idChapter) {
        this.iconChapter = iconChapter;
        this.nameChapter = nameChapter;
        this.isLock = isLock;
        this.idChapter = idChapter;
    }

    public int getIconChapter() {
        return iconChapter;
    }

    public void setIconChapter(int iconChapter) {
        this.iconChapter = iconChapter;
    }

    public String getNameChapter() {
        return nameChapter;
    }

    public void setNameChapter(String nameChapter) {
        this.nameChapter = nameChapter;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    public int getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(int idChapter) {
        this.idChapter = idChapter;
    }

    public boolean hasImage(){
        return iconChapter != NO_IMAGE_PROViDED;
    }
}
