package com.example.cihatmertbaykal.jsoup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView liste;
    String adres = "https://www.universitego.com/2017-universitelerin-guncel-taban-puanlari-ve-basari-siralamalari/";

    ArrayList<String> bolumler = new ArrayList<>();
    ArrayList<String> adresler = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        liste = findViewById(R.id.liste);

        new vericek().execute(adres);

    }



    public class vericek extends AsyncTask<String,Integer,Document>{
        ProgressDialog dialog;
        Document veri;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Yükleniyor...");
            dialog.setTitle("JSOUP DENEME");
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setProgress(0);
            dialog.setMax(100);
            dialog.show();
        }

        @Override
        protected Document doInBackground(String... strings) {

            try{

                 veri = Jsoup.connect(strings[0]).get();


            }catch (IOException hata){

            }


            return veri;
        }


      @Override
       protected void onProgressUpdate(Integer... values) {

            dialog.setProgress(dialog.getProgress() + 1);

      }

        @Override
        protected void onPostExecute(Document document) {
            super.onPostExecute(document);
//
//            Elements pler = document.select("div.reading p");



            Element div = document.getElementById("singleContent");
            System.out.println(div);
//            div.children();
            Elements pler = div.getElementsByTag("p");
            Element p = pler.get(3);
//            Elements alar = p.getElementsByTag("a");
//            p.children();

            for(Element a:p.children()){
               if(!TextUtils.isEmpty(a.text())) {
                   String bolumadi = a.text();
                   String adres = a.attr("href");

                   bolumler.add(bolumadi);
                   adresler.add(adres);
               }
            }

//            for(int i=0;i<p.children().size();i++){
//                Element a = p.children().get(i);
//
//
//            }


            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,bolumler);
            liste.setAdapter(adapter);

            liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String adres = adresler.get(position);

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(adres));
                    startActivity(intent);

                }
            });


            dialog.dismiss();
        }
    }


}
