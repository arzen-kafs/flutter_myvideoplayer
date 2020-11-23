package com.example.hikaioffline.Tables;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Chapter.class,
        parentColumns = "chapterNo",
        childColumns = "ChapterNo",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE),tableName = "tbllecture")
public class Lecture {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String ChapterNo;
    private String lectureId;
    private String content;
    private String title;
    private String file;
    private String description;

    public Lecture(String ChapterNo, String lectureId, String content, String title, String file, String description) {
        this.ChapterNo = ChapterNo;
        this.lectureId = lectureId;
        this.content = content;
        this.title = title;
        this.file = file;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getChapterNo() {
        return ChapterNo;
    }

    public String getLectureId() {
        return lectureId;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getFile() {
        return file;
    }

    public String getDescription() {
        return description;
    }
}
