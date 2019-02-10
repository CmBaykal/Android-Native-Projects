package com.example.cihatmertbaykal.faf;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class menu extends Fragment {

    public bilgi versiyonalici;
    String [] versiyonlar;
    public menu() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ListView liste = view.findViewById(R.id.liste);

        versiyonlar = getResources().getStringArray(R.array.versiyondizisi);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,versiyonlar);
        liste.setAdapter(adapter);

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                versiyonalici.versiyonAdi(versiyonlar[position]);

            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof bilgi){

            versiyonalici = (bilgi) context;

        }

    }


    public interface bilgi{

        public void versiyonAdi(String vadi);
    }


}
