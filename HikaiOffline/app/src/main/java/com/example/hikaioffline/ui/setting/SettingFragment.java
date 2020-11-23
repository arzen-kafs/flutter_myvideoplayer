package com.example.hikaioffline.ui.setting;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hikaioffline.R;
import com.example.hikaioffline.login.SharedPrefManager;

public class SettingFragment extends Fragment {
    private TextView txturl;
    private EditText url;
    private Button save;



    private SettingViewModel mViewModel;

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(SettingViewModel.class);
        View root= inflater.inflate(R.layout.fragment_setting, container, false);
        txturl=root.findViewById(R.id.txt_url);
        url=root.findViewById(R.id.etURL);
        save=root.findViewById(R.id.bSave);

        url.setText(SharedPrefManager.getInstance(getContext()).getUrl());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serverUrl=url.getText().toString();
                SharedPrefManager.getInstance(getContext()).setUrl(serverUrl);
            }
        });

        return root;
    }



}