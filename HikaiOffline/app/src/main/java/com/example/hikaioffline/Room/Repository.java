package com.example.hikaioffline.Room;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;


import com.example.hikaioffline.Tables.Chapter;
import com.example.hikaioffline.Tables.Lecture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Repository {
    MyDAO myDAO;

    LiveData<List<Chapter>> allChapters;
    LiveData<List<Lecture>> allLecture;
    public  static Activity activity;
//   static List<Chapter> allChapterJson;
//   static List<Lecture> allLectureJson;




    public Repository (Application application, Activity activity){
        VideoDatabase database=VideoDatabase.getInstance(application);
        myDAO=database.myDAO();
        allChapters=myDAO.getAllChapter();
        allLecture=myDAO.getAllLecture();
        this.activity=activity;
       //allChapterJson=myDAO.getAllChapterJson();
        //allLectureJson=myDAO.getAllLectureJson();
    }

    public void insertChapter(Chapter chapter){
        new InsertChapterAsyncTask(myDAO).execute(chapter);
    }

    public void insertLecture(Lecture lecture){
        new InsertLectureAsyncTask(myDAO).execute(lecture);
    }

    public void deleteAllChapter(){
        new DeleteAllChapterAsyncTask(myDAO).execute();
    }

    public String generateJSON()
    {
        String result=null;
        GenerateJsonAsyncTask task = new GenerateJsonAsyncTask(myDAO);
        try {
           result= task.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("RES",result);
        return result;

    }

    public LiveData<List<Chapter>> getAllChapters(){
        return allChapters;
    }

    public LiveData<List<Lecture>> getAllLecture(){
        return allLecture;
    }

//    public List<Chapter> getAllChapterJson(){
//        return allChapterJson;
//    }

    public static class InsertChapterAsyncTask extends AsyncTask<Chapter,Void,Void>{
            private MyDAO myDAO;
            private ProgressDialog progress;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress=new ProgressDialog(Repository.activity);
            progress.setMessage("Downloading Content");
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progress.setIndeterminate(true);
            progress.setProgress(0);
            progress.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progress.dismiss();
        }

        private InsertChapterAsyncTask(MyDAO myDAO){
                this.myDAO=myDAO;
            }
            @Override
            protected Void doInBackground(Chapter... chapters) {
               myDAO.insertChapter(chapters[0]);
                return null;
            }
    }

    public static class InsertLectureAsyncTask extends AsyncTask<Lecture,Void,Void>{
        private MyDAO myDAO;

        private InsertLectureAsyncTask(MyDAO myDAO){
            this.myDAO=myDAO;
        }
        @Override
        protected Void doInBackground(Lecture... lectures) {
            myDAO.insertLecture(lectures[0]);
            return null;
        }
    }

    public static class GenerateJsonAsyncTask extends AsyncTask<Void,Void,String>{
        private MyDAO myDAO;
        public String value;
        //Chapter Array
        JSONArray c_ar = new JSONArray();
        public String  getValue(){
            return value;
        }


        private GenerateJsonAsyncTask(MyDAO myDAO){
            this.myDAO=myDAO;
        }

        @Override
        protected String doInBackground(Void ...voids) {

            List<Chapter> c_list = myDAO.getAllChapterJson();
            List<Lecture> l_list=myDAO.getAllLectureJson();

            try {
                for (int i=0;i<c_list.size();i++)
                {
                    Log.d("HELLO", c_list.get(i).getChapterNo());
                    Log.d("HELLO", c_list.get(i).getTitle());
                    //Chapter Object
                    JSONObject c_obj = new JSONObject();
                    c_obj.put("chapterNo", c_list.get(i).getChapterNo());
                    c_obj.put("title",  c_list.get(i).getTitle());
                    c_obj.put("ebook",  c_list.get(i).getEbook());

                    JSONArray l_ar = new JSONArray();
                    for(int j=0;j<l_list.size();j++){
                        if(c_list.get(i).getChapterNo().equals(l_list.get(j).getChapterNo())){
                            //Lecture Object
                            JSONObject l_obj = new JSONObject();
                            Log.d("HELLO from lecture",l_list.get(j).getTitle());
                            l_obj.put("lectureID",l_list.get(j).getLectureId());
                            l_obj.put("content",l_list.get(j).getContent());
                            l_obj.put("title",l_list.get(j).getTitle());
                            String url="/storage/emulated/0/Android/data/com.example.hikaioffline/files/Videos"+l_list.get(j).getFile().substring(46);
                            l_obj.put("file",url);
                            //Lecture Array

                            l_ar.put(l_obj);

                        }
                    }
                    c_obj.put("lecture",l_ar);
                    c_obj.put("items",  String.valueOf(l_ar.length()));
                    c_ar.put(c_obj);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("HELLOJOSN",c_ar.toString());
            return c_ar.toString();
        }
        @Override
        protected void onPostExecute(String result) {
            // Call activity method with results
            super.onPostExecute(result);
            value=result;

        }

    }

    //Deleting all Chapters

    private static class DeleteAllChapterAsyncTask extends AsyncTask<Void,Void,Void>{
        private MyDAO myDAO;
        private DeleteAllChapterAsyncTask(MyDAO myDAO){
            this.myDAO=myDAO;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            myDAO.deleteAllChapter();
            return null;
        }
    }





}
