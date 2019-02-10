package com.example.cihatmertbaykal.izinalma;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText numara;
    Button ara;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ara = findViewById(R.id.ara);
        numara = findViewById(R.id.numara);

        ara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED){  //getApplicationContext()

                ActivityCompat.requestPermissions(MainActivity.this,new String [] {Manifest.permission.CALL_PHONE},1);

               }else{
                   Toast.makeText(MainActivity.this, "İzin zaten alınmış", Toast.LENGTH_SHORT).show();
                   String telno = numara.getText().toString();

                   Intent intent = new Intent(Intent.ACTION_CALL);
                   intent.setData(Uri.parse("tel:"+telno));
                   startActivity(intent);


               }



            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "İzin verildi", Toast.LENGTH_SHORT).show();
                String telno = numara.getText().toString();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+telno));
                startActivity(intent);

            }else{
                Toast.makeText(this, "izin verilmedi", Toast.LENGTH_SHORT).show();
            }


        }

    }

}
