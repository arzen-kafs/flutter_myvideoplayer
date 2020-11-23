package com.example.hikaioffline.ui.classroom;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hikaioffline.R;
import com.example.hikaioffline.Room.MyViewModel;
import com.example.hikaioffline.Room.Repository;
import com.example.hikaioffline.hikaiplayer.Player;
import com.example.hikaioffline.login.SharedPrefManager;
import com.example.hikaioffline.login.User;
import com.google.android.material.snackbar.Snackbar;

public class ClassroomFragment extends Fragment  {
    TextView tvUserName, tvStandard;
    Repository repository;
    User user;
    CardView english,science,social,maths,hindi,health,computer,khasi,environment,language;
    private ClassroomViewModel homeViewModel;
    MyViewModel viewModel;
    int pStatus = 0;
    private Handler handler = new Handler();
    Button btnClassroom,btnLoad;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(ClassroomViewModel.class);
        View root = inflater.inflate(R.layout.fragment_classroom, container, false);
        viewModel=new MyViewModel((Application) getActivity().getApplicationContext(),getActivity());
        btnClassroom=root.findViewById(R.id.btnClassroom);
        tvUserName = root.findViewById(R.id.tv_username);
        tvStandard = root.findViewById(R.id.tv_standard);
        repository= new Repository(getActivity().getApplication(),getActivity());



        user = SharedPrefManager.getInstance(getContext()).getUser();

        tvUserName.setText("User Name: "+user.getName());
        tvStandard.setText("Class: "+user.getStandard()+"(Stream: "+user.getStream()+")");

        btnClassroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent card1=new Intent(getContext(), Player.class);
                startActivity(card1);
            }
        });

        btnLoad=root.findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnline(getContext())) {
                    repository.deleteAllChapter();
                    viewModel.getVolleyDetails(user.getStandard(), user.getStream());
                    Snackbar snackbar = Snackbar
                            .make(container, "Data Loaded Successfully", Snackbar.LENGTH_LONG);
                    snackbar.setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    }).show();
                }
                else {
                    Snackbar snackbar = Snackbar
                            .make(container, "No Internet Connection", Snackbar.LENGTH_LONG);
                    snackbar.setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    }).show();
                }
            }
        });

        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular);



        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    public static boolean isOnline(Context context) {
        boolean result = false;
        if (context != null) {
            final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                if (networkInfo != null) {
                    result = networkInfo.isConnected();
                }
            }
        }
        return result;
    }
}