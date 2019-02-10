package com.example.cihatmertbaykal.mapsodev;

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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap harita;
    Button bul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        bul = findViewById(R.id.btn);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        harita = googleMap;
        if(ActivityCompat.checkSelfPermission(MapsActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(MapsActivity.this,new String [] {Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        else{
            harita.setMyLocationEnabled(true);
            Toast.makeText(this, "Konumunuz Aktif", Toast.LENGTH_SHORT).show();
        }

        bul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(harita.isMyLocationEnabled()){
                    Location konumum = harita.getMyLocation();
                    LatLng koordinat = new LatLng(konumum.getLatitude(),konumum.getLongitude());
                    harita.animateCamera(CameraUpdateFactory.newLatLngZoom(koordinat,10));
                    harita.addCircle(new CircleOptions().center(koordinat).radius(5000).strokeColor(Color.RED));
                    System.out.println(koordinat);
                    String Url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.2207977,32.6496473&radius=5000&keyword=hastane&key=AIzaSyAU_g8d55odcaZrh_KClAD80oqj1iqTIhU";
                    final StringRequest istek = new StringRequest(StringRequest.Method.GET, Url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{


                                JSONObject obje = new JSONObject(response);
                                JSONArray dizi = obje.getJSONArray("results");
                                for (int i=0;i<dizi.length();i++){
                                    JSONObject hastane = dizi.getJSONObject(i);
                                    JSONObject geo = hastane.getJSONObject("geometry");
                                    JSONObject location = geo.getJSONObject("location");
                                    LatLng xy = new LatLng(location.getDouble("lat"),location.getDouble("lng"));
                                    String ad = hastane.getString("name");
                                    harita.addMarker(new MarkerOptions().position(xy).title(ad));
                                }



                            }catch (JSONException hata){

                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });


                    RequestQueue kuyruk = Volley.newRequestQueue(MapsActivity.this);
                    kuyruk.add(istek);
                }


            }
        });


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        harita.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        harita.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
