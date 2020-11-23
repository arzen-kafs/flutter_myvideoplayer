package com.example.hikaioffline.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.hikaioffline.Tables.Chapter;
import com.example.hikaioffline.Tables.Lecture;

import java.util.List;

@Dao
public interface  MyDAO {
        @Insert
        void insertChapter(Chapter chapter);
        @Insert
        void insertLecture(Lecture lecture);
        //External Database
        //From Web integration to sync SQLite
        @Query("Select * from tblchapter")
        LiveData<List<Chapter>> getAllChapter();
        //From Web integration  to sync SQLite
        @Query("Select * from tbllecture ")
        LiveData<List<Lecture>> getAllLecture();

        //Internal Database
        //From SQLiteJSON
        @Query("Select * from tblchapter")
        List<Chapter> getAllChapterJson();

        @Query("Select * from tbllecture ")
        List<Lecture> getAllLectureJson();

        @Query("Delete From tblchapter")
        void deleteAllChapter();

}
