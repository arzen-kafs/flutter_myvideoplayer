package com.example.hikaioffline.Room;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hikaioffline.R;
import com.example.hikaioffline.Tables.Chapter;
import com.example.hikaioffline.Tables.Lecture;
import com.example.hikaioffline.login.SharedPrefManager;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MyViewModel extends AndroidViewModel implements ViewModelStoreOwner {
    private Repository repository;
    private LiveData<List<Chapter>> allChapters;
    private LiveData<List<Lecture>> allLecture;
    SharedPrefManager sharedPrefManager;




    public MyViewModel(@NonNull Application application, Activity activity) {
        super(application);
        repository=new Repository(application,activity);
        allChapters=repository.getAllChapters();
        allLecture=repository.getAllLecture();
    }
    public void getVolleyDetails(String standard, String stream) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplication());
        String url=SharedPrefManager.getInstance(getApplication()).getUrl();
        String path="";
        if(stream.equals("NA")) {
            path = url+"ClassRoom/cache/CLA" + standard.toUpperCase() + ".cache";
        }else {
            path = url+"ClassRoom/cache/CLA" + standard.toUpperCase()+stream+".cache";
        }


        //Toast.makeText(getApplication(),path, Toast.LENGTH_LONG).show();
        Log.d("TAG",path);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, path,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i=0;i<response.length();i++){
                            try {
                                //Toast.makeText(getApplication(), "Inside Volley", Toast.LENGTH_SHORT).show();

                                JSONObject obj=response.getJSONObject(i);
                                String chapterno=obj.getString("chapterNo");
                                String title=obj.getString("title");
                                String ebook=obj.getString("ebook");
                                Chapter chapter=new Chapter(chapterno,title,ebook);
                                //Toast.makeText(getApplication(), "chapter", Toast.LENGTH_SHORT).show();
                                insertChapter(chapter);
                                JSONArray lecture_ar = obj.getJSONArray("lecture");
                                for(int j=0;j<lecture_ar.length();j++)
                                {
                                    JSONObject lecture_obj = lecture_ar.getJSONObject(j);
                                    if(lecture_obj.getString("content").equals("video"))
                                    {
                                        String lecture_id = lecture_obj.getString("lectureId");
                                        String content=lecture_obj.getString("content");
                                        String lTitle=lecture_obj.getString("title");
                                        String file=lecture_obj.getString("file");
                                        String desc=lecture_obj.getString("description");
                                        Lecture lecture1=new Lecture(chapterno,lecture_id,content,lTitle,file,desc);
                                        insertLecture(lecture1);
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication(), "Error Loading Please Load Again", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonArrayRequest);


    }

    public void insertChapter(final Chapter chapter){
        //Toast.makeText(getApplication(), "Inserted Chapter", Toast.LENGTH_SHORT).show();
        repository.insertChapter(chapter);
    }

    public void insertLecture(final Lecture lecture){
        //Toast.makeText(getApplication(), "Inserted Lecture", Toast.LENGTH_SHORT).show();
        repository.insertLecture(lecture);
    }

    public LiveData<List<Chapter>> getAllChapters(){
        return allChapters;
    }

    public LiveData<List<Lecture>> getAllLecture(){
        return allLecture;
    }


    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return null;
    }
}
