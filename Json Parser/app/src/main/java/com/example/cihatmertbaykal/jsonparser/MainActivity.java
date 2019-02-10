package com.example.cihatmertbaykal.jsonparser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView liste;

    String adres = "http://web.karabuk.edu.tr/yasinortakci/dokumanlar/web_dokumanlari/iller.json";

//    String [] dizi;

    ArrayList<String> iladlari = new ArrayList<>();
    ArrayList<String> plakalar = new ArrayList<>();
    ArrayList<String> resimler = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        liste = findViewById(R.id.liste);

        StringRequest istek = new StringRequest(StringRequest.Method.GET, adres, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{

                    JSONArray dizi = new JSONArray(response);

                    for(int i=0;i<dizi.length();i++){

                        JSONObject nesne = dizi.getJSONObject(i);

                        JSONArray iller = nesne.getJSONArray("illeri");

                        for(int k=0;k<iller.length();k++){


                            JSONObject il = iller.getJSONObject(k);

                            String iladi = il.getString("il");
                            String plaka = String.valueOf(il.getInt("plaka"));
                            String resim = il.getString("resim");

                            iladlari.add(iladi);
                            plakalar.add(plaka);
                            resimler.add(resim);

                        }


                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,iladlari);
                    liste.setAdapter(adapter);

                    liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            String iladi = iladlari.get(position);
                            String plakakodu = plakalar.get(position);
                            String resimadresi = resimler.get(position);

                            Intent intent = new Intent(MainActivity.this,ilbilgisi.class);
                            intent.putExtra("ad",iladi);
                            intent.putExtra("kod",plakakodu);
                            intent.putExtra("adres",resimadresi);
                            startActivity(intent);


                        }
                    });


                }
                catch (JSONException hata){

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue kuyruk = Volley.newRequestQueue(MainActivity.this);
        kuyruk.add(istek);

    }



}
