package com.tiburela.TriviasMedicas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tiburela.TriviasMedicas.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Politicas_privacidad#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Politicas_privacidad extends Fragment {


   TextView link_p;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Politicas_privacidad() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_otro.
     */
    // TODO: Rename and change types and number of parameters
    public static Politicas_privacidad newInstance(String param1, String param2) {
        Politicas_privacidad fragment = new Politicas_privacidad();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_otro, container, false);

        // Inflate the layout for this fragment

       //activa el link
        link_p=view.findViewById(R.id.link_p);
        Linkify.addLinks(link_p, Linkify.ALL);

        return view;


    }
}