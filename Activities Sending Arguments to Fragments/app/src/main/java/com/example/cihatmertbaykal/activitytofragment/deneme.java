package com.example.cihatmertbaykal.activitytofragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class deneme extends Fragment {

    public String veri;
    TextView yazi;

    public deneme() {
    }

    public static deneme newInstance(String bilgi) {

        Bundle args = new Bundle();

        args.putString("yazi",bilgi);
        System.out.println(args);

        deneme fragment = new deneme();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments()!=null){

            veri = getArguments().getString("yazi");

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_deneme, container, false);
        yazi = view.findViewById(R.id.yazi);
        yazi.setText(veri);
        return view;
    }


//    public void yazial(String bilgi){
//
//        this.veri = bilgi;
//
//    }



}
