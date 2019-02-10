package com.example.cihatmertbaykal.faf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements menu.bilgi{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void versiyonAdi(String vadi) {

        versiyon fragment = new versiyon();
        fragment.versiyonal(vadi);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment2,fragment).commit();


    }
}
