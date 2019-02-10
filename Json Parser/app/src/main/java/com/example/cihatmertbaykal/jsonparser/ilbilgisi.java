package com.example.cihatmertbaykal.jsonparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ilbilgisi extends AppCompatActivity {

    ImageView resim;
    TextView ilinadi,plakakodu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilbilgisi);
        resim = findViewById(R.id.resim);
        ilinadi = findViewById(R.id.iladi);
        plakakodu = findViewById(R.id.plakakodu);

        String ad = getIntent().getStringExtra("ad");
        String kod = getIntent().getStringExtra("kod");
        String adres = getIntent().getStringExtra("adres");


        ilinadi.setText(ad);
        plakakodu.setText(kod);

        Picasso.get().load(adres).into(resim);



    }
}
