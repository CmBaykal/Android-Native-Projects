package com.example.cihatmertbaykal.faf;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class versiyon extends Fragment {

    String veri;

    public versiyon() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_versiyon, container, false);

        TextView yazi = view.findViewById(R.id.versiyonnumarasi);
        yazi.setText(veri);

        return view;
    }




    public void versiyonal(String vadi){

        veri = vadi;


    }



}
