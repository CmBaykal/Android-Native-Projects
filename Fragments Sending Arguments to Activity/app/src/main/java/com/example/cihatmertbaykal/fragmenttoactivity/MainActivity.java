package com.example.cihatmertbaykal.fragmenttoactivity;

import android.content.ContentValues;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements renkFragment.renk{

    ConstraintLayout duzen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        duzen = findViewById(R.id.duzen);
    }

    @Override
    public void renkDegis(String renkadi) {

        switch (renkadi){

                case "mavi":
//                    duzen.setBackgroundColor(Color.parseColor("#fff"));
                    duzen.setBackgroundColor(Color.BLUE);
                break;
                case "kırmızı":
                    duzen.setBackgroundColor(Color.RED);
                break;
                case "yeşil":
                    duzen.setBackgroundColor(Color.GREEN);
                break;

        }

    }
}
