package com.example.hikaioffline.hikaiplayer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.hikaioffline.R;
import com.example.hikaioffline.Room.Repository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChapterLectureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChapterLectureFragment extends Fragment implements RecyclerViewClickInterface{

    //MAIN
    private RecyclerView rvChapterLecture;
    LinearLayoutManager layoutManager;
    ChapterAdapter chapterAdapter;
    RequestQueue queue;
    JsonArrayRequest jsonObjReq;
    String jsonData;
    JSONObject jresponse;
    Repository repository;
    List<String> fileList = new ArrayList<String>();


    //Recycler View
    View v;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChapterLectureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChapterLectureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChapterLectureFragment newInstance(String param1, String param2) {
        ChapterLectureFragment fragment = new ChapterLectureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_chapter_lecture, container, false);
        repository = new Repository(getActivity().getApplication(),getActivity());
        jsonData= repository.generateJSON();
        Log.d("HELLOJSON2",jsonData);
        rvChapterLecture = v.findViewById(R.id.rv_chapter_lecture);
        layoutManager = new LinearLayoutManager(getContext());
        chapterAdapter = new ChapterAdapter(buildItemList(),ChapterLectureFragment.this);
        rvChapterLecture.setAdapter(chapterAdapter);
        rvChapterLecture.setLayoutManager(layoutManager);
        return v;
    }

    private List<Chapter> buildItemList() {

        final List<Chapter> chapterList = new ArrayList<>();

        try {
            JSONArray mainArray = new JSONArray(jsonData);



            for(int i=0;i<mainArray.length();i++){
                JSONObject jobject = mainArray.getJSONObject(i);
                //Parent Recycler View
                //jresponse = jobject.getJSONObject(i);
                String chapterTitle = jobject.getString("title");
                String totalLecture = jobject.getString("items");;
                JSONArray lectureArray=jobject.getJSONArray("lecture");
                Log.d("Lecture",jobject.getJSONArray("lecture").toString());
                //Log.d("chapterNo",chapterNo);
                // Log.d("title",title);
                Chapter chapter = new Chapter((i+1)+" "+chapterTitle,"Total lectures "+totalLecture, buildSubItemList(lectureArray));
                chapterList.add(chapter);

            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }



        return chapterList;
    }

    private List<Lecture> buildSubItemList(JSONArray lectureArray) {
        final List<Lecture> lectureList = new ArrayList<>();

        try {
            for (int i=0; i<lectureArray.length(); i++) {

                JSONObject lectureObject=lectureArray.getJSONObject(i);
                String content = lectureObject.getString("content");
                if(content.equals("video")) {
                    String lectureId = lectureObject.getString("lectureID");
                    String lectureTitle = lectureObject.getString("title");
                    String lectureUrl = lectureObject.getString("file");
                    Lecture lecture = new Lecture(lectureId,lectureTitle, lectureUrl);
                    lectureList.add(lecture);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Log.i("HELLO","HELLO");
        for (int i=0;i<lectureList.size();i++)
        {
            fileList.add(lectureList.get(i).getLectureUrl());
            Log.i("TAG 1", String.valueOf(lectureList.get(i).getLectureId()));
            Log.i("TAG 2",fileList.get(i));
        }
        return lectureList;
    }

    @Override
    public void onItemClick(String name) {
        VideoViewMainFragment f = new VideoViewMainFragment();
        //f.iniExoplayer("http://techslides.com/demos/sample-videos/small.mp4");
        Bundle bundle = new Bundle();
        Log.i("",name);
        bundle.putString("path",name);
        bundle.putLong("seek",0);
        f.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.frame_layout_main,f).commit();
    }

    @Override
    public void onDownloadClick(String name) {

    }

    @Override
    public void onLongItemClick(int position) {

    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getContext().getAssets().open("BEG.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}