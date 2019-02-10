package com.example.cihatmertbaykal.fragmenttoactivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;


public class renkFragment extends Fragment {

    public renk renkdegisitirici;


    public renkFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_renk, container, false);
        RadioGroup grup = view.findViewById(R.id.grup);

        grup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch(checkedId){

                        case R.id.rmavi:
                            renkdegisitirici.renkDegis("mavi");
                        break;

                        case R.id.rkirmizi:
                            renkdegisitirici.renkDegis("kırmızı");
                        break;
                        case R.id.ryesil:
                            renkdegisitirici.renkDegis("yeşil");
                        break;



                }

            }
        });

        return view;
    }





    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof renk){

            renkdegisitirici = (renk) context;

        }


    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//
//    }


    public interface renk{

        public void renkDegis(String renkadi);

    }


}
