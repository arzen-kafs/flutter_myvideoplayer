package com.example.hikaioffline.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hikaioffline.Tables.Chapter;
import com.example.hikaioffline.Tables.Lecture;


@Database(entities = {Chapter.class, Lecture.class},version = 1)
public abstract class VideoDatabase extends RoomDatabase {
    public static VideoDatabase instance;
    public abstract MyDAO myDAO();

    public static synchronized VideoDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    VideoDatabase.class,"MyDatabase").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
