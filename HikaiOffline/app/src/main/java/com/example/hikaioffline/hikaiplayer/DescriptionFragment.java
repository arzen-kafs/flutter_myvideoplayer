package com.example.hikaioffline.hikaiplayer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

import com.example.hikaioffline.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescriptionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DescriptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DescriptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DescriptionFragment newInstance(String param1, String param2) {
        DescriptionFragment fragment = new DescriptionFragment();
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
        View view= inflater.inflate(R.layout.fragment_description, container, false);
        WebView wview =view.findViewById(R.id.textContent);
        String text;
        text = "<html><body>";
        text+= "<h4>Free online classes for IX to XII MBOSE only</h4>\n" +
                "<b>Hybrid Interactive Knowledge Assimilation Initiative (HIKAI)</b><br><br>\n" +
                "<p align=\"justify\">The Hybrid Interactive Knowledge Assimilation Initiative (HIKAI) being launched by Ramakrishna Mission to provide structured curriculum as per the Board syllabus to all school-going students in Meghalaya. You are aware that the schools are not allowed to function in normal mode by the Government fearing community spread of COVID19.\n" +
                "<br><br>\n" +
                "So, the two centres of the Ramakrishna Mission in Meghalaya viz, the Sohra (Cherrapunjee) centre and the Shillong centre are combining their expertise to provide education through an innovative the Hybrid Interactive Knowledge Assimilation Initiative (HIKAI) from 17 August 2020 for any student in Meghalaya, who wants to use this facility. To begin with we are launching the program for classes IX to XII.";
        text+= "</p></body></html>";
        wview.loadData(text, "text/html", "utf-8");
        return view;

    }
}