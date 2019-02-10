package com.example.cihatmertbaykal.googlemaps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button bul,maptipi;
    ZoomControls zoomctrl;
    EditText konum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        bul = findViewById(R.id.button);
        maptipi = findViewById(R.id.maptipi);
        zoomctrl = findViewById(R.id.zoomctrl);
        konum = findViewById(R.id.editText);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    if(ActivityCompat.checkSelfPermission(MapsActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED){

        ActivityCompat.requestPermissions(MapsActivity.this,new String [] {Manifest.permission.ACCESS_FINE_LOCATION},1);

    }else{

        mMap.setMyLocationEnabled(true);

    }



        maptipi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL){

                    maptipi.setText("Normal");
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

                }
                else{

                    maptipi.setText("Uydu");
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                }

            }

        });

        zoomctrl.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                mMap.moveCamera(CameraUpdateFactory.zoomOut());
                mMap.animateCamera(CameraUpdateFactory.zoomOut());

            }
        });

        zoomctrl.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMap.moveCamera(CameraUpdateFactory.zoomIn());

            }
        });


        bul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Location konumum = mMap.getMyLocation();

                Double enlem = konumum.getLatitude();
                Double boylam = konumum.getLongitude();


                String yer = konum.getText().toString();

                String adres = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+ enlem + "," + boylam +"&radius=5000&keyword=" + yer + "&key=AIzaSyAtxBkpK9HlvCnFxeengxGy7PpYHipSZL8";

                LatLng koordinat = new LatLng(enlem,boylam);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(koordinat,12));
                mMap.addCircle(new CircleOptions().center(koordinat).radius(5000).strokeColor(Color.RED));

                StringRequest istek = new StringRequest(StringRequest.Method.GET, adres, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{


                            JSONObject obje = new JSONObject(response);
                            JSONArray dizi = obje.getJSONArray("results");

                            for(int i=0;i<dizi.length();i++){

                                JSONObject bulunan = dizi.getJSONObject(i);

                                JSONObject geometry = bulunan.getJSONObject("geometry");

                                JSONObject location = geometry.getJSONObject("location");

                                Double x =  location.getDouble("lat");
                                Double y =  location.getDouble("lng");

                                String konumadi = bulunan.getString("name");

                                LatLng mekankoor = new LatLng(x,y);

                                mMap.addMarker(new MarkerOptions().position(mekankoor).title(konumadi));


                            }




                        }catch (JSONException hata){

                            Toast.makeText(MapsActivity.this, hata.getMessage() , Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                RequestQueue kuyruk = Volley.newRequestQueue(MapsActivity.this);
                kuyruk.add(istek);


//                String yer = konum.getText().toString();
//
//                Geocoder geocoder = new Geocoder(MapsActivity.this);
//
//                try{
//
//                    List<Address> adresler = geocoder.getFromLocationName(yer,1);
//
//                    Address adres = adresler.get(0);
//
//                    LatLng enlemboylam = new LatLng(adres.getLatitude(),adres.getLongitude());
//
//                    mMap.addMarker(new MarkerOptions().position(enlemboylam).title(yer));
//
////                    mMap.moveCamera(CameraUpdateFactory.newLatLng(enlemboylam));
//                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(enlemboylam,10));
//
//
//                }catch (IOException hatA){
//
//                }



            }
        });

        LatLng sydney = new LatLng(-34, 151);

        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney")); //İşaretçi

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
