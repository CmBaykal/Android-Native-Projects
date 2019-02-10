package com.example.cihatmertbaykal.activitytofragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    Button deneme1;
    EditText yazi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deneme1 = findViewById(R.id.deneme1);
        yazi = findViewById(R.id.yazi);

        deneme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                deneme fragment1 = deneme.newInstance(yazi.getText().toString());


//                fragment1.yazial(yazi.getText().toString());

//                FragmentManager manager = getSupportFragmentManager();
//                FragmentTransaction transaction = manager.beginTransaction();
//// 2               transaction.add(R.id.kutu,fragment1);
//                transaction.replace(R.id.kutu,fragment1);
//                transaction.commit();

                getSupportFragmentManager().beginTransaction().replace(R.id.kutu,fragment1).commit();



            }
        });

    }
}
