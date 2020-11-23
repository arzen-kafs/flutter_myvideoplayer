package com.example.hikaioffline.hikaiplayer;

public class Lecture {
    private String lectureId;
    private String lectureTitle;
    private String lectureUrl;

    public Lecture(String lectureId,String lectureTitle, String lectureUrl) {
        this.lectureId = lectureId;
        this.lectureTitle = lectureTitle;
        this.lectureUrl = lectureUrl;
    }

    public String getLectureTitle() {
        return lectureTitle;
    }

    public void setLectureTitle(String lectureTitle) {
        this.lectureTitle = lectureTitle;
    }

    public String getLectureUrl() {
        return lectureUrl;
    }

    public void setLectureUrl(String lectureUrl) {
        this.lectureUrl = lectureUrl;
    }

    public String getLectureId() {
        return lectureId;
    }

    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }
}