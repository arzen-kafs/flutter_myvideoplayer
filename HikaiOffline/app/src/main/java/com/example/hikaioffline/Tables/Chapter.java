package com.example.hikaioffline.Tables;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblchapter",indices = {@Index(value = "chapterNo",unique = true)})
public class Chapter {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String chapterNo;

    private String title;

    private String ebook;

    public Chapter(String chapterNo, String title, String ebook) {
        this.chapterNo = chapterNo;
        this.title = title;
        this.ebook = ebook;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getChapterNo() {
        return chapterNo;
    }

    public String getTitle() {
        return title;
    }

    public String getEbook() {
        return ebook;
    }
}
